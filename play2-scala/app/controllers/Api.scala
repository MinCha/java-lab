package controllers

import model.Champion
import org.springframework.stereotype
import play.api.mvc._

@stereotype.Controller
class Api extends Controller {

  def vayne = Action {
    val vayne = new Champion("vayne", "time of")
    Ok(vayne.toString)
  }
}
