package controllers

import model.{Champion, ChampionRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype
import play.api.mvc._

@stereotype.Controller
class Jpa(@Autowired repo: ChampionRepository) extends Controller {
  def this() = this(null)

  def vayne = Action {
    val vayne = new Champion("vayne", "tumble")
    var id = repo.save(vayne)
    val result = repo.findOne(1)
    Ok(result.toString)
  }

  def vayneskill = Action {
    val vayne = new Champion("vayne", "tumble")
    var id = repo.save(vayne)
    val result = repo.findBySkill("tumble")
    Ok(result.toString)
  }
}
