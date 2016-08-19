package controllers

import models.Account

import javax.inject._

import play.api.mvc._
import play.api.libs.json._

@Singleton
class Application @Inject() extends Controller {
  val plainhtml = true

  def listAccounts = Action { request =>
    val accounts = Account.getAll
    if (plainhtml){
      Ok(views.html.accounts(accounts))
    } else {
      Ok(Json.toJson(accounts))
    }

  }

  def saveAccount = Action(BodyParsers.parse.json) { request =>
    val accountResult = request.body.validate[Account]
    accountResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      account => {
        Account.save(account)
        Ok(Json.obj("status" ->"OK", "message" -> ("Account '"+account.name+"' saved.") ))
      }
    )
  }

}
