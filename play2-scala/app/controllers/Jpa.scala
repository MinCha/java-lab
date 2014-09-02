package controllers

import model.{Champion, ChampionRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype
import play.api.mvc._

import scala.util.Random

@stereotype.Controller
class Jpa(@Autowired repo: ChampionRepository) extends Controller {
  def this() = this(null)

  def vayne = Action {
    var vayne = new Champion("vayne", "tumble")
    vayne.addWeapon(new Random().nextInt(100).toString);
    vayne = repo.save(vayne)
    val result = repo.findOne(vayne.getId - 1)
    Ok(result.toString)
  }
}
