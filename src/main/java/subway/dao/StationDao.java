package subway.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import subway.domain.Station;

@Repository
public class StationDao {

    private final JdbcTemplate jdbcTemplate;

    public StationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Station insert(Station station) {
        String sql = "INSERT INTO station(name) VALUES (?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, station.getName());
            return ps;
        }, keyHolder);
        long stationId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return new Station(stationId, station.getName());
    }

    public Optional<Station> findById(long stationId) {
        String sql = "SELECT * FROM station WHERE id = ?";
        BeanPropertyRowMapper<Station> mapper = BeanPropertyRowMapper.newInstance(Station.class);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper, stationId));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Station> findByName(Station station) {
        String sql = "SELECT * FROM station WHERE name = ?";
        BeanPropertyRowMapper<Station> mapper = BeanPropertyRowMapper.newInstance(Station.class);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper, station.getName()));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Station> findAll() {
        String sql = "SELECT * FROM station";
        BeanPropertyRowMapper<Station> mapper = BeanPropertyRowMapper.newInstance(Station.class);
        return jdbcTemplate.query(sql, mapper);
    }
}
