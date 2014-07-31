package controllers

import model.Champion
import play.api.libs.json.Json
import play.api.mvc._

object Api extends Controller {
  def vayne = Action {
    //val vayne = new Champion("vayne", List("tumble","condemn"))
    //Ok(Json.toJson(vayne))
    Ok("some")
  }
}
