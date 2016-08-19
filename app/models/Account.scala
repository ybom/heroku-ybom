package models

import play.api._

case class Account(id: Int, name: String, title: String)

object Account {
  import play.api.libs.json._

  def getAll = List(
    Account(10, "Al", "Software Engineer"),
    Account(11, "Darren", "Senior Graphic Designer"),
    Account(12, "Rich", "Software Architect")
  )

  def save(account: Account): Unit = {
    None
  }

  implicit object AccountFormat extends Format[Account] {
    // from JSON string to a Note object (de-serializing from JSON)
    def reads(json: JsValue): JsResult[Account] = {
      val id = (json \ "id").as[Int]
      val name = (json \ "name").as[String]
      val title = (json \ "title").as[String]
      JsSuccess(Account(id,name,title))
    }
    
  // convert from Account object to JSON (serializing to JSON)
    def writes(a: Account): JsValue = {
      // JsObject requires Seq[(String, play.api.libs.json.JsValue)]
      val accountAsList = Seq("id"    -> JsNumber(a.id),
                              "name"  -> JsString(a.name),
                              "title" -> JsString(a.title))
      JsObject(accountAsList) 
    }
  }
}
