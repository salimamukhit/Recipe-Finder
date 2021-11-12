package controllers

import play.mvc.Result
import play.mvc.Results.ok


class HomeController {
  def index: Result = ok(views.html.index.render())
}
