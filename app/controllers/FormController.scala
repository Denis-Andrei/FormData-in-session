package controllers

import play.api.data.Form
import play.api.data.Forms.{list, mapping, nonEmptyText, number, text}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import javax.inject.Inject

class FormController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def nameForm() = Action { implicit  request =>

    Ok(views.html.nameform(NameForm.form.fill(NameFormModel())))

  }

  def submitNameForm() = Action { implicit request =>
    println("BIND: " +NameForm.form.bindFromRequest())
    NameForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.nameform(formWithErrors)),
      success => Redirect(controllers.routes.FormController.ageForm()).withSession(request.session + ("name" -> s"${success.name}"))
    )
  }

  def ageForm() = Action { implicit  request: Request[AnyContent] =>
    Ok(views.html.ageform(AgeForm.form.fill(AgeFormModel(100))))
  }

  def submitAgeForm() = Action { implicit request =>
    AgeForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.ageform(formWithErrors)),
      success => Redirect(controllers.routes.FormController.genderForm()).withSession(request.session + ("age" -> s"${success.age}"))
    )
  }


  def genderForm() = Action { implicit  request =>
    Ok(views.html.genderform( GenderForm.form.fill(GenderFormModel(""))))
  }

  def submitGenderForm() = Action { implicit  request =>

    GenderForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.genderform(formWithErrors)),
      success => {
        Redirect(controllers.routes.FormController.jobForm()).withSession(request.session + ("gender" -> s"${success.gender}") )
      }
    )

  }

  def jobForm() = Action { implicit  request =>
    Ok(views.html.jobform(JobForm.form.fill(JobFormModel(""))))
  }

  def submitJobForm() = Action { implicit  request =>
    JobForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.jobform(formWithErrors)),
      success => {
        Redirect(controllers.routes.FormController.colourForm()).withSession(request.session + ("job" -> s"${success.job}") )
      }
    )
  }


  def colourForm() = Action { implicit  request =>
    Ok(views.html.colourform(ColourForm.form.fill(ColourFormModel())))
  }

  def submitColourForm() = Action { implicit  request =>
    ColourForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.colourform(formWithErrors)),
      success => {
        println("success: " + success)
        Redirect(controllers.routes.HomeController.summary()).withSession(request.session + ("colour" -> s"${success.colours}") )
      }
    )
  }

}


case class AgeFormModel(age: Int)
case class ColourFormModel(colours: List[String] = List())

case class GenderFormModel(gender: String)
case class JobFormModel(job: String)
case class NameFormModel(name: String= "")

object AgeForm {
  val form: Form[AgeFormModel] = Form(
    mapping(
      "age" -> number
    )(AgeFormModel.apply)(AgeFormModel.unapply)
  )
}

object ColourForm {
  val form: Form[ColourFormModel] = Form(
    mapping(
      "colour" -> list(text)
    )(ColourFormModel.apply)(ColourFormModel.unapply)
  )
}

object GenderForm {
  val form: Form[GenderFormModel] = Form(
    mapping(
      "gender" -> text
    )(GenderFormModel.apply)(GenderFormModel.unapply)
  )
}

object JobForm {
  val form: Form[JobFormModel] = Form(
    mapping(
      "job" -> text
    )(JobFormModel.apply)(JobFormModel.unapply)
  )
}

object NameForm {
  val form: Form[NameFormModel] = Form(
    mapping(
      "name" -> text
    )(NameFormModel.apply)(NameFormModel.unapply)
  )
}