package repository;

import model.Queens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcQueensRepository implements QueensRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Queens queen) {
        return jdbcTemplate.update("INSERT INTO queens (coord, value) VALUES(?,?,?)",
                queen.getCoord(), queen.getValue());
    }

    @Override
    public int update(Queens queen) {
        return jdbcTemplate.update("UPDATE queens SET coord=?, value=? WHERE id=?",
                queen.getCoord(), queen.getValue(), queen.getId());
    }

    @Override
    public Queens findById(Long id) {
        try {
            Queens queen = jdbcTemplate.queryForObject("SELECT * FROM queens WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Queens.class), id);
            return queen;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM queens WHERE id=?", id);
    }

    @Override
    public List<Queens> findAll() {
        return jdbcTemplate.query("SELECT * from queens", BeanPropertyRowMapper.newInstance(Queens.class));
    }

    @Override
    public List<Queens> findByCoordinates(String coord) {
        return jdbcTemplate.query("SELECT * from queens WHERE coord=?",
                BeanPropertyRowMapper.newInstance(Queens.class), coord);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from queens");
    }
}
