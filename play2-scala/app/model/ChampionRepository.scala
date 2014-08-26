package model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Vayne on 2014. 8. 4..
 */
trait ChampionRepository extends CrudRepository[Champion, Integer] {
  def findByName(name: String) : Champion

  def findBySkill(skill: String) : List[Champion]
}
