package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerJdbcDao implements OwnerDao {

    private static final String TABLE_NAME = "Owner";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OwnerJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //US-0
    @Override
    public Owner findOneById(Long id) throws NotFoundException{
        LOGGER.trace("Get owner with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Owner> owners = jdbcTemplate.query(sql, new Object[]{id}, this::mapRow);

        if (owners.isEmpty()) throw new NotFoundException("Could not find owner with id " + id);

        return owners.get(0);
    }

    //US-6
    @Override
    public Owner saveOwner(Owner newOwner) throws PersistenceException {
        LOGGER.info("Persistence: Saving new {}", newOwner.toString());

        String sql = "INSERT INTO " + TABLE_NAME + " (NAME, CREATED_AT, UPDATED_AT)" +
            " VALUES (:name, :created_at, :updated_at)";
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            MapSqlParameterSource msps = new MapSqlParameterSource();

            msps.addValue("name", newOwner.getName());
            msps.addValue("created_at", timestamp);
            msps.addValue("updated_at", timestamp);

            namedParameterJdbcTemplate.update(sql, msps, keyHolder);
            LOGGER.info("Created new owner with id: {}", keyHolder.getKey());
            return findOneById((Long) keyHolder.getKey());
        } catch (Exception e) {// (SQLException e) {
            LOGGER.error("Persistence: Problem while executing SQL INSERT INTO statement", e);
            throw new PersistenceException("Could not post newOwner", e);
        }
    }

    //US-7
    @Override
    public Owner putOneById(Long id, Owner updateOwner) throws PersistenceException, NotFoundException {
        LOGGER.info("Persistence: Put owner with id {}", id);
        String sql = "UPDATE " + TABLE_NAME +
            " SET NAME = :name, UPDATED_AT = :updated_at" +
            " WHERE id = :id";
        LocalDateTime now = LocalDateTime.now();

        try {
            MapSqlParameterSource msps = new MapSqlParameterSource();
            msps.addValue("name", updateOwner.getName());
            msps.addValue("updated_at", Timestamp.valueOf(now));
            msps.addValue("id", id);

            int affected = namedParameterJdbcTemplate.update(sql, msps);
            if (affected == 0) {
                LOGGER.error("Problem while finding owner for updating with id {}", id);
                throw new NotFoundException("Could not find owner with id {}" + id);
            } else {
                return findOneById(id);
            }
        } catch (Exception e) {
            LOGGER.error("Problem while executing SQL PUT statement for owner with id " + id, e);
            throw new PersistenceException("Could not update owner with id " + id, e);
        }
    }

    //US-8
    @Override
    public void deleteOneById(Long id) throws PersistenceException, NotFoundException {
        LOGGER.info("Persistence: Deleting owner with id {}", id);
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = :id";

        if (!findOneById(id).getOwnedHorses().isEmpty()) {
            LOGGER.error("Problem while executing SQL DELETE statement for owner with id {} - existing ownership of horses", id);
            throw new PersistenceException("Could not delete owner with id " + id + " - existing ownership of horses");
        } else {
            try {
                MapSqlParameterSource msps = new MapSqlParameterSource();
                msps.addValue("id", id);

                int affected = namedParameterJdbcTemplate.update(sql, msps);
                if (affected == 0) {
                    LOGGER.error("Problem while finding owner for deleting with id {}", id);
                    throw new NotFoundException("Could not find owner with id {}" + id);
                }
            } catch (Exception e) {
                LOGGER.error("Problem while executing SQL DELETE statement for owner with id " + id, e);
                throw new PersistenceException("Could not delete owner with id " + id, e);
            }
        }
    }

    //US-9
    @Override
    public List<Owner> findAllFiltered(Owner searchOwner) throws PersistenceException, NotFoundException {
        LOGGER.info("Persistence: Get all owners filtered by: {}", searchOwner.toString());
        List<Owner> searchOwnerList;
        boolean nameFlag = false;

        if (searchOwner.getName() != null) {
            nameFlag = true;
        }
        LOGGER.debug("flag:" + nameFlag);

        String sql = "SELECT * FROM " + TABLE_NAME;
        MapSqlParameterSource msps = new MapSqlParameterSource();
        try {
            if (nameFlag) {
                sql += " WHERE UPPER(name) LIKE UPPER(:name)";
                msps.addValue("name", '%' + searchOwner.getName() + '%');
            }
            searchOwnerList = namedParameterJdbcTemplate.query(sql, msps, this::mapRow);
            LOGGER.debug(searchOwnerList.toString());
        } catch (Exception e) {
            LOGGER.error("Persistence: Problem while executing SQL SELECT * FROM (WHERE) statement", e);
            throw new PersistenceException("Could not find any owners", e);
        }

        if (!searchOwnerList.isEmpty()) {
            return searchOwnerList;
        } else {
            LOGGER.error("Persistence: Problem while executing SQL SELECT * FROM (WHERE) statement for reading all filtered owners");
            throw new NotFoundException("Could not find any owners");
        }
    }

    //US-10
    private List<Horse> findOwnedHorses(Long id) throws NotFoundException {
        LOGGER.info("Persistence: Get all horses for owner with id: {}", id);
        List<Horse> searchHorseList;

        String sql = "SELECT * FROM horse WHERE OWNER_ID = :owner_id";
        MapSqlParameterSource msps = new MapSqlParameterSource();

        msps.addValue("owner_id", id);
        searchHorseList = namedParameterJdbcTemplate.query(sql, msps, this::mapRowHorse);
        LOGGER.debug(searchHorseList.toString());

        if (searchHorseList.isEmpty()) {
            LOGGER.debug("One of the owners doesn't own a horse");
        }
        return searchHorseList;
    }

    private Owner mapRow(ResultSet resultSet, int i) throws SQLException {
        final Owner owner = new Owner();
        owner.setId(resultSet.getLong("id"));
        owner.setName(resultSet.getString("name"));
        owner.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        owner.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        owner.setOwnedHorses(findOwnedHorses(owner.getId()));
        return owner;
    }

    //Needed for search of horses with ownerId
    private Horse mapRowHorse(ResultSet resultSet, int i) throws SQLException {
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
