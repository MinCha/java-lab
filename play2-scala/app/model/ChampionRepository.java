package model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Vayne on 2014. 8. 4..
 */
public interface ChampionRepository extends CrudRepository<Champion, Integer> {
    public List<Champion> findBySkill(String skill);
}
