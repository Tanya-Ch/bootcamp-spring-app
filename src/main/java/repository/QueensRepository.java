package repository;

import model.Queens;

import java.util.List;

public interface QueensRepository  {

        int save(Queens queen);

        int update(Queens queen);

        Queens findById(Long id);

        int deleteById(Long id);

        List<Queens> findAll();

        List<Queens> findByCoordinates(String coord);

        int deleteAll();

}
