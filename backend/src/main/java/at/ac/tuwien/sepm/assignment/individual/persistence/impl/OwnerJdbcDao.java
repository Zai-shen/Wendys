package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.OwnershipException;
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
import org.springframework.dao.DataAccessException;
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
    public Owner findOneById(Long id) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Get owner with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Owner> owners = null;
        try {
            owners = jdbcTemplate.query(sql, new Object[]{id}, this::mapRow);
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL Statement " + sql);
            throw new PersistenceException("Error while executing SQL Statement " + sql, e);
        }

        if (owners.isEmpty()) {
            LOGGER.error("Could not find owner with id {}", id);
            throw new NotFoundException("Could not find owner with id " + id);
        } else {
            LOGGER.debug("Returning owner with id {}", id);
        }

        return owners.get(0);
    }

    //US-6
    @Override
    public Owner saveOwner(Owner newOwner) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Saving new {}", newOwner.toString());

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

            LOGGER.debug("Created new owner with id {}", keyHolder.getKey());
            return findOneById((Long) keyHolder.getKey());
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement " + sql, e);
            throw new PersistenceException("Error while executing SQL statement " + sql, e);
        }
    }

    //US-7
    @Override
    public Owner putOneById(Long id, Owner updateOwner) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Put owner with id {}", id);
        String sql = "UPDATE " + TABLE_NAME +
            " SET NAME = :name, UPDATED_AT = :updated_at" +
            " WHERE id = :id";
        LocalDateTime now = LocalDateTime.now();

        int affected = 0;
        try {
            MapSqlParameterSource msps = new MapSqlParameterSource();
            msps.addValue("name", updateOwner.getName());
            msps.addValue("updated_at", Timestamp.valueOf(now));
            msps.addValue("id", id);

            affected = namedParameterJdbcTemplate.update(sql, msps);
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement" + sql, e);
            throw new PersistenceException("Error while executing SQL statement" + sql, e);
        }
        LOGGER.debug("Updated " + affected + "owner(s).");

        if (affected == 0) {
            LOGGER.error("Error while finding owner for updating with id {}", id);
            throw new NotFoundException("Error while finding owner for updating with id" + id);
        } else {
            return findOneById(id);
        }
    }

    //US-8
    @Override
    public void deleteOneById(Long id) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Deleting owner with id {}", id);
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = :id";

        int affected = 0;
        if (!findOneById(id).getOwnedHorses().isEmpty()) {
            LOGGER.error("Error while executing SQL statement "+sql+" for owner with id {} - existing ownership of horses", id);
            throw new OwnershipException("Could not delete owner with id " + id + " - existing ownership of horses");
        } else {
            try {
                MapSqlParameterSource msps = new MapSqlParameterSource();
                msps.addValue("id", id);

                affected = namedParameterJdbcTemplate.update(sql, msps);

            } catch (DataAccessException e) {
                LOGGER.error("Error while executing SQL statement" + sql, e);
                throw new PersistenceException("Error while executing SQL statement" + sql, e);
            }
        }
        LOGGER.debug("Deleted " + affected + "owner(s).");

        if (affected == 0) {
            LOGGER.error("Error while finding owner for deleting with id {}", id);
            throw new NotFoundException("Could not find owner with id {}" + id);
        }
    }

    //US-9
    @Override
    public List<Owner> findAllFiltered(Owner searchOwner) throws PersistenceException, NotFoundException {
        LOGGER.trace("Persistence: Get all owners filtered by {}", searchOwner.toString());
        String sql = "SELECT * FROM " + TABLE_NAME;
        List<Owner> searchOwnerList;
        boolean nameFlag = false;

        if (searchOwner.getName() != null) {
            nameFlag = true;
        }
        LOGGER.debug("nameFlag: " + nameFlag);

        MapSqlParameterSource msps = new MapSqlParameterSource();
        try {
            if (nameFlag) {
                sql += " WHERE UPPER(name) LIKE UPPER(:name)";
                msps.addValue("name", '%' + searchOwner.getName() + '%');
            }
            searchOwnerList = namedParameterJdbcTemplate.query(sql, msps, this::mapRow);
        } catch (DataAccessException e) {
            LOGGER.error("Error while executing SQL statement" + sql, e);
            throw new PersistenceException("Error while executing SQL statement" + sql, e);
        }
        LOGGER.debug("Returning " + searchOwnerList.size() + " found owners " + searchOwnerList.toString());

        if (!searchOwnerList.isEmpty()) {
            return searchOwnerList;
        } else {
            LOGGER.error("Error while executing SQL statement" + sql + " - no owners found");
            throw new NotFoundException("Error while executing SQL statement" + sql + " - no owners found");
        }
    }

    //US-10
    private List<Horse> findOwnedHorses(Long id) throws NotFoundException {
        LOGGER.trace("Persistence: Get all horses for owner with id {}", id);
        List<Horse> searchHorseList = null;

        String sql = "SELECT * FROM horse WHERE OWNER_ID = :owner_id";
        try {
            MapSqlParameterSource msps = new MapSqlParameterSource();

            msps.addValue("owner_id", id);
            searchHorseList = namedParameterJdbcTemplate.query(sql, msps, this::mapRowHorse);
        }catch (DataAccessException e){
            LOGGER.error("Error while executing SQL statement" + sql);
            throw new NotFoundException("Error while executing SQL statement" + sql);
        }
        String debugMessage = "Returning " + searchHorseList.size() + " horses " + searchHorseList.toString();
        if (searchHorseList.isEmpty()) {
            debugMessage += " - owner with id " + id + " doesn't own any horses";
        }
        LOGGER.debug(debugMessage);
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
