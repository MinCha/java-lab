package controllers

import model.ChampionRepository
import org.springframework.stereotype
import play.api.mvc._

@stereotype.Controller
class Basic extends Controller {

  def hello = Action {
    Ok("Hello")
  }

  def helloreq = Action { request =>
    Ok("Hello Request : " + request)
  }

  def hellonum(num: Long) = Action {
    Ok("Hello " + num)
  }
}
