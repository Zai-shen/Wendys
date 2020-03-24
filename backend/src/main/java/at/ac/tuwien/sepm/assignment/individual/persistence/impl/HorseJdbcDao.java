package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

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

    //US-0
    @Override
    public Horse findOneById(Long id) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Get horse with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Horse> horses = null;

        try {
            horses = jdbcTemplate.query(sql, new Object[]{id}, this::mapRow);
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement " + sql, e);
            throw new PersistenceException("Error while executing SQL statement " + sql, e);
        }

        if (horses.isEmpty()) {
            LOGGER.error("Could not find horse with id {}", id);
            throw new NotFoundException("Could not find horse with id " + id);
        } else {
            LOGGER.debug("Returning horse with id {}", id);
        }

        return horses.get(0);
    }

    //US-1
    @Override
    public Horse saveHorse(Horse newHorse) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Saving new {}", newHorse.toString());

        String sql = "INSERT INTO " + TABLE_NAME + " (NAME, DESCRIPTION, RATING, BIRTH_DAY, BREED, IMAGE, OWNER_ID, CREATED_AT, UPDATED_AT)" +
            " VALUES (:name, :description, :rating, :birth_day, :breed, :image, :owner_id, :created_at, :updated_at)";
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
            msps.addValue("owner_id", newHorse.getOwnerId());
            msps.addValue("created_at", timestamp);
            msps.addValue("updated_at", timestamp);

            namedParameterJdbcTemplate.update(sql, msps, keyHolder);
            LOGGER.debug("Created new horse with id {}", keyHolder.getKey());
            return findOneById((Long) keyHolder.getKey());
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement " + sql, e);
            throw new PersistenceException("Error while executing SQL statement " + sql, e);
        }
    }

    //US-3
    @Override
    public Horse putOneById(Long id, Horse updateHorse) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Put horse with id {}", id);
        String sql = "UPDATE " + TABLE_NAME +
            " SET NAME = :name, DESCRIPTION = :description, RATING = :rating, BIRTH_DAY = :birth_day," +
            " BREED = :breed, IMAGE = :image, OWNER_ID = :owner_id, UPDATED_AT = :updated_at" +
            " WHERE id = :id";
        LocalDateTime now = LocalDateTime.now();

        int affected = 0;
        try {
            MapSqlParameterSource msps = new MapSqlParameterSource();
            msps.addValue("name", updateHorse.getName());
            msps.addValue("description", updateHorse.getDescription());
            msps.addValue("rating", updateHorse.getRating());
            msps.addValue("birth_day", Timestamp.valueOf(updateHorse.getBirthDay()));
            msps.addValue("breed", updateHorse.getBreed());
            msps.addValue("image", updateHorse.getImageURI());
            msps.addValue("owner_id", updateHorse.getOwnerId());
            msps.addValue("updated_at", Timestamp.valueOf(now));
            msps.addValue("id", id);

            affected = namedParameterJdbcTemplate.update(sql, msps);
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement" + sql, e);
            throw new PersistenceException("Error while executing SQL statement" + sql, e);
        }
        LOGGER.debug("Updated " + affected + "horse(s).");

        if (affected == 0) {
            LOGGER.error("Problem while finding horse for updating with id {}", id);
            throw new NotFoundException("Error while finding horse for updating with id " + id);
        } else {
            return findOneById(id);
        }
    }

    //US-4
    @Override
    public void deleteOneById(Long id) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Deleting horse with id {}", id);
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = :id";

        int affected = 0;
        try {
            MapSqlParameterSource msps = new MapSqlParameterSource();
            msps.addValue("id", id);

            affected = namedParameterJdbcTemplate.update(sql, msps);
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement" + sql, e);
            throw new PersistenceException("Error while executing SQL statement" + sql, e);
        }
        LOGGER.debug("Deleted " + affected + "horse(s).");

        if (affected == 0) {
            LOGGER.error("Problem while finding horse for deleting with id {}", id);
            throw new NotFoundException("Could not find horse with id {}" + id);
        }
    }

    //US-5
    @Override
    public List<Horse> findAllFiltered(Horse searchHorse) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Get all horses filtered by {}", searchHorse.toString());
        String sql = "SELECT * FROM " + TABLE_NAME;
        List<Horse> searchHorseList;
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
        LOGGER.debug("flags: " + nameFlag + descriptionFlag + ratingFlag + birthDayFlag + breedFlag);

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
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement" + sql, e);
            throw new PersistenceException("Error while executing SQL statement" + sql, e);
        }
        LOGGER.debug("Returning " + searchHorseList.size() + " found horses " + searchHorseList.toString());

        if (!searchHorseList.isEmpty()) {
            return searchHorseList;
        } else {
            LOGGER.error("Error while executing SQL statement" + sql + " - no owners found");
            throw new NotFoundException("Error while executing SQL statement" + sql + " - no owners found");
        }
    }


    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setOwnerId(resultSet.getLong("owner_id"));
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
