import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class BasicSpec extends Specification {

  "Basic" should {

    "hello" in new WithApplication() {
      val result = route(FakeRequest(GET, "/hello")).get

      contentAsString(result) must contain ("Hello")
    }

    "hello number" in new WithApplication() {
      val result = route(FakeRequest(GET, "/hellonum/1130")).get

      contentAsString(result) must contain ("1130")
    }
  }
}
