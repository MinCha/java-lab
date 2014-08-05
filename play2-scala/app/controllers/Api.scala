package controllers

import org.springframework.stereotype
import play.api.mvc._

@stereotype.Controller
class Api extends Controller {

  def vayne = Action {
    //val vayne = new Champion("vayne", List("tumble","condemn"))
    //Ok(Json.toJson(vayne))
    Ok("some")
  }
}
