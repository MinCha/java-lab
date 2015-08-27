package doctor.interfaces

import doctor.ServerIntegrationTest
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner])
class DoctorAPIControllerTest extends ServerIntegrationTest {
  val Log = LoggerFactory.getLogger(classOf[DoctorAPIControllerTest])
  val restTemplate = new TestRestTemplate

  @Test
  def canMeetDoctor() {
    val result = restTemplate.getForEntity(baseUrl + "/doctor/Icearrows", classOf[String])

    Log.info(result.getBody)
    assert(result.getStatusCode == HttpStatus.OK)
  }
}
