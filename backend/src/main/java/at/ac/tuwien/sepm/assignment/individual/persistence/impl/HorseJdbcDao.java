package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HorseJdbcDao implements IHorseDao {

    private static final String TABLE_NAME = "Horse";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public HorseJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Horse findOneById(Long id) {
        LOGGER.trace("Get horse with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Horse> horses = jdbcTemplate.query(sql, new Object[]{id}, this::mapRow);

        if (horses.isEmpty()) throw new NotFoundException("Could not find horse with id " + id);

        return horses.get(0);
    }

    @Override
    public Horse saveHorse(Horse newHorse) throws PersistenceException {
        LOGGER.info("Persistence: Saving new " + newHorse.toString());

        //TODO
        ///final String UPDATE_QUERY = "update employee set age = :age where id = :id";
        ///final String DELETE_QUERY = "delete from employee where id = :id";
        /*
        *         SqlParameterSource namedParameters = new MapSqlParameterSource("id", empId);
        int status = namedJdbcTemplate.update(DELETE_QUERY, namedParameters);
        if(status != 0){
            System.out.println("Employee data deleted for ID " + empId);
        }else{
            System.out.println("No Employee found with ID " + empId);
        }
        * */

        String sqlInsert = "INSERT INTO horse (NAME, DESCRIPTION, RATING, BIRTH_DAY, BREED, IMAGE, CREATED_AT, UPDATED_AT)" +
            " VALUES (:name, :description, :rating, :birth_day, :breed, :image, :created_at, :updated_at)";

        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            MapSqlParameterSource msps = new MapSqlParameterSource();

            msps.addValue("name", newHorse.getName());
            msps.addValue("description", newHorse.getDescription());
            msps.addValue("rating", newHorse.getRating());
            msps.addValue("birth_day", Timestamp.valueOf(newHorse.getBirthDay()));
            msps.addValue("breed", newHorse.getBreed());
            msps.addValue("image", newHorse.getImageURI());
            msps.addValue("created_at", timestamp);
            msps.addValue("updated_at", timestamp);

            namedParameterJdbcTemplate.update(sqlInsert, msps, keyHolder);
            LOGGER.info("Created new horse with id: " + keyHolder.getKey());
            return findOneById((Long) keyHolder.getKey());
        } catch (Exception e) {// (SQLException e) {
            LOGGER.error("Persistence: Problem while executing SQL INSERT INTO statement", e);
            throw new PersistenceException("Could not post newHorse", e);
        }
    }

    @Override
    public List<Horse> findAllFiltered(Horse searchHorse) throws PersistenceException, NotFoundException {
        LOGGER.info("Persistence: Get all horses filtered by: " + searchHorse.toString());
        List<Horse> searchHorseList = new ArrayList<>();
        boolean nameFlag = false;
        boolean descriptionFlag = false;
        boolean ratingFlag = false;
        boolean birthDayFlag = false;
        boolean breedFlag = false;

        if (searchHorse.getName() != null) {
            nameFlag = true;
        }
        if (searchHorse.getDescription() != null) {
            descriptionFlag = true;
        }
        if (searchHorse.getRating() != null) {
            ratingFlag = true;
        }
        if (searchHorse.getBirthDay() != null) {
            birthDayFlag = true;
        }
        if (searchHorse.getBreed() != null) {
            breedFlag = true;
        }
        LOGGER.debug("flags:" + nameFlag + descriptionFlag + ratingFlag + birthDayFlag + breedFlag);

        String sql = "SELECT * FROM Horse";
        MapSqlParameterSource msps = new MapSqlParameterSource();
        try {
            if (nameFlag || descriptionFlag || ratingFlag || birthDayFlag || breedFlag) {
                sql += " WHERE";
                if (nameFlag) {
                    sql += " UPPER(name) LIKE UPPER(:name) AND";
                    msps.addValue("name", '%' + searchHorse.getName() + '%');
                }
                if (descriptionFlag) {
                    sql += " UPPER(description) LIKE UPPER(:description) AND";
                    msps.addValue("description", '%' + searchHorse.getDescription() + '%');
                }
                if (ratingFlag) {
                    sql += " rating LIKE :rating AND";
                    msps.addValue("rating", searchHorse.getRating());
                }
                if (birthDayFlag) {
                    sql += " birth_day <= :birth_day AND";
                    msps.addValue("birth_day", Timestamp.valueOf(searchHorse.getBirthDay()));
                }
                if (breedFlag) {
                    sql += " breed LIKE :breed AND";
                    msps.addValue("breed", searchHorse.getBreed());
                }

                if (sql.endsWith(" AND")) {
                    sql = sql.substring(0, sql.length() - 4);
                }
            }
            searchHorseList = namedParameterJdbcTemplate.query(sql, msps, this::mapRow);
            LOGGER.debug(searchHorseList.toString());
        } catch (Exception e) {
            LOGGER.error("Persistence: Problem while executing SQL SELECT * FROM (WHERE) statement", e);
            throw new PersistenceException("Could not find any horses", e);
        }
        if (!searchHorseList.isEmpty()) {
            return searchHorseList;
        } else {
            LOGGER.error("Persistence: Problem while executing SQL SELECT * FROM (WHERE) statement for reading all filtered horses");
            throw new NotFoundException("Could not find any horses");
        }
    }


    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setName(resultSet.getString("name"));
        horse.setDescription(resultSet.getString("description"));
        horse.setRating(resultSet.getInt("rating"));
        horse.setBirthDay(resultSet.getTimestamp("birth_day").toLocalDateTime());
        horse.setBreed(resultSet.getString("breed"));
        horse.setImageURI(resultSet.getString("image"));
        horse.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        horse.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return horse;
    }

}
