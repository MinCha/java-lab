package controllers

import org.springframework.stereotype
import play.api.mvc._

@stereotype.Controller
class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
