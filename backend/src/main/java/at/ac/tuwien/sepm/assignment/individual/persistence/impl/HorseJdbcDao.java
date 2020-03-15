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
        List<Horse> horses = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);

        if (horses.isEmpty()) throw new NotFoundException("Could not find horse with id " + id);

        return horses.get(0);
    }

    @Override
    public Horse saveOne(HorseDto horseDto) throws PersistenceException {
        LOGGER.info("Persistence: Saving new horse" + horseDto.toString());

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

        String sqlInsert = "INSERT INTO horse (NAME, DESCRIPTION, RATING, BIRTH_DAY, CREATED_AT, UPDATED_AT)" +
            " VALUES (:name, :description, :rating, :birth_day, :created_at, :updated_at)";

        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {

            MapSqlParameterSource msps = new MapSqlParameterSource();
            MapSqlParameterSource abc = new MapSqlParameterSource();

            msps.addValue("name", horseDto.getName());
            msps.addValue("description", horseDto.getDescription());
            msps.addValue("rating", horseDto.getRating());
            msps.addValue("birth_day", Timestamp.valueOf(horseDto.getBirthDay()));
            msps.addValue("created_at", timestamp);
            msps.addValue("updated_at", timestamp);

            namedParameterJdbcTemplate.update(sqlInsert, msps,keyHolder);

            LOGGER.info("New horse id: "+ keyHolder.getKey());

            return findOneById((Long)keyHolder.getKey());
/*
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("name", horse.getName());
            parameterMap.put("description", horse.getDescription());
            parameterMap.put("rating", horse.getRating());
            parameterMap.put("birth_day", horse.getBirthDay());
            parameterMap.put("created_at", timestamp);
            parameterMap.put("updated_at", timestamp);

            namedParameterJdbcTemplate.update(sqlInsert, parameterMap);
*/
        } catch(Exception e){// (SQLException e) {
            LOGGER.error("Persistence: Problem while executing SQL INSERT INTO statement", e);
            throw new PersistenceException("Could not post horse", e);
        }
    }

    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setName(resultSet.getString("name"));
        horse.setDescription(resultSet.getString("description"));
        horse.setRating(resultSet.getInt("rating"));
        horse.setBirthDay(resultSet.getTimestamp("birth_day").toLocalDateTime());
        horse.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        horse.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return horse;
    }

}
