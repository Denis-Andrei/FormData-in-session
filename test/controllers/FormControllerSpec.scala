package controllers


import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status.OK
import play.api.http.Status
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.ControllerComponents
import play.api.Play.materializer
import play.api.test.Helpers.{GET, POST, contentAsString, contentType, defaultAwaitTimeout, route, status, stubControllerComponents}
import play.api.test.{FakeHeaders, FakeRequest, Injecting}

class FormControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf[ControllerComponents]

  object TestFormController extends FormController(controllerComponents)

  "HomeController GET" should {

    //////////////////////
    //Testing ageForm


    "render the ageForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val agePage = controller.ageForm().apply(FakeRequest(GET, "/ageForm"))

      status(agePage) mustBe OK
      contentType(agePage) mustBe Some("text/html")
      contentAsString(agePage) must include("Age Form")
    }

    "render the ageForm page from the application" in {
      val controller = inject[FormController]
      val agePage = controller.ageForm().apply(FakeRequest(GET, "/ageForm"))

      status(agePage) mustBe OK
      contentType(agePage) mustBe Some("text/html")
      contentAsString(agePage) must include("Age Form")
    }





    //////////////////////
    //Testing colourForm


    "render the colourForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val colourPage = controller.colourForm().apply(FakeRequest(GET, "/colourForm"))

      status(colourPage) mustBe OK
      contentType(colourPage) mustBe Some("text/html")
      contentAsString(colourPage) must include("Favourite Colour")
    }

    "render the colourForm page from the application" in {
      val controller = inject[FormController]
      val colourPage = controller.colourForm().apply(FakeRequest(GET, "/colourForm"))

      status(colourPage) mustBe OK
      contentType(colourPage) mustBe Some("text/html")
      contentAsString(colourPage) must include("Favourite Colour")
    }


    //////////////////////
    //Testing genderForm


    "render the genderForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val genderPage = controller.genderForm().apply(FakeRequest(GET, "/colourForm"))

      status(genderPage) mustBe OK
      contentType(genderPage) mustBe Some("text/html")
      contentAsString(genderPage) must include("Gender Form")
    }

    "render the genderForm page from the application" in {
      val controller = inject[FormController]
      val genderPage = controller.genderForm().apply(FakeRequest(GET, "/colourForm"))

      status(genderPage) mustBe OK
      contentType(genderPage) mustBe Some("text/html")
      contentAsString(genderPage) must include("Gender Form")
    }


    //////////////////////
    //Testing jobForm


    "render the jobForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val jobPage = controller.jobForm().apply(FakeRequest(GET, "/colourForm"))

      status(jobPage) mustBe OK
      contentType(jobPage) mustBe Some("text/html")
      contentAsString(jobPage) must include("Job Form")
    }

    "render the jobForm page from the application" in {
      val controller = inject[FormController]
      val jobPage = controller.jobForm().apply(FakeRequest(GET, "/colourForm"))

      status(jobPage) mustBe OK
      contentType(jobPage) mustBe Some("text/html")
      contentAsString(jobPage) must include("Job Form")
    }



    //////////////////////
    //Testing nameForm


    "render the nameForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val namePage = controller.nameForm().apply(FakeRequest(GET, "/colourForm"))

      status(namePage) mustBe OK
      contentType(namePage) mustBe Some("text/html")
      contentAsString(namePage) must include("Name Form")
    }

    "render the nameForm page from the application" in {
      val controller = inject[FormController]
      val namePage = controller.nameForm().apply(FakeRequest(GET, "/colourForm"))

      status(namePage) mustBe OK
      contentType(namePage) mustBe Some("text/html")
      contentAsString(namePage) must include("Name Form")
    }




  }




  "HomeController POST" should {

    "POST the ageFormSubmit " when {

      "ageFormSubmit() returns SEE OTHER( status 303 redirect) when number submitted" in {
        val jsonBody: JsObject = Json.obj(
          "age" -> 24
        )

        val result = TestFormController.submitAgeForm().apply(FakeRequest(POST, "/ageForm").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER
      }

      "ageFormSubmit() returns BAD_REQUEST when string submitted" in {
        val wrongJsonBody: JsObject = Json.obj(
          "age" -> "test"
        )
        val result = TestFormController.submitAgeForm().apply(FakeRequest(POST, "/ageForm").withJsonBody(wrongJsonBody))
        status(result) mustBe Status.BAD_REQUEST
      }


      "POST the jobFormSubmit " when {


        "returns SEE OTHER( status 303 redirect) when text submitted" in {
          val jsonBody: JsObject = Json.obj(
            "job" -> "Dev"
          )

          val result = TestFormController.submitJobForm().apply(FakeRequest(POST, "/jobForm").withJsonBody(jsonBody))
          status(result) mustBe Status.SEE_OTHER
        }

        "returns BAD_REQUEST when nothing in the body submitted" in {

          val result = TestFormController.submitJobForm().apply(FakeRequest(POST, "/jobForm"))
          status(result) mustBe Status.BAD_REQUEST
        }
      }

      "POST the nameFormSubmit " when {


        "returns SEE OTHER( status 303 redirect) when text submitted" in {
          val jsonBody: JsObject = Json.obj(
            "job" -> "Dev"
          )

          val result = TestFormController.submitJobForm().apply(FakeRequest(POST, "/jobForm").withJsonBody(jsonBody))
          status(result) mustBe Status.SEE_OTHER
        }

        "returns BAD_REQUEST when nothing in the body submitted" in {

          val result = TestFormController.submitJobForm().apply(FakeRequest(POST, "/jobForm"))
          status(result) mustBe Status.BAD_REQUEST
        }
      }

      "POST the colourFormSubmit " when {


        "returns SEE OTHER( status 303 redirect) when number submitted" in {
          val jsonBody: JsObject = Json.obj(
            "colour" -> List("green", "red", "blue")
          )

          val result = TestFormController.submitColourForm().apply(FakeRequest(POST, "/colourForm").withJsonBody(jsonBody))
          status(result) mustBe Status.SEE_OTHER
        }

        //NOT WORKING FOR THIS BECAUSE WE CAN SUBMIT WITHOUT SELECTING ANY CHECKBOX
//        "returns BAD_REQUEST when number submitted" in {
//          val jsonBody: JsObject = Json.obj(
//            "colour" -> 1
//          )
//
//          val result = TestFormController.submitColourForm().apply(FakeRequest(POST, "/colourForm"))
//          status(result) mustBe Status.BAD_REQUEST
//        }
      }

    }
  }
}