package controllers

import play.api.mvc._

object Basic extends Controller {

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
