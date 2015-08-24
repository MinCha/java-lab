package doctor

import org.scalatest.junit.JUnitSuite
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.web.WebAppConfiguration

@SpringApplicationConfiguration(classes = Array(classOf[DoctorConfig]))
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest(Array("server.port:0"))
abstract class ServerIntegrationTest extends JUnitSuite with IntegrationFixture {
  @Value("${local.server.port}")
  var port: Int = _

  def baseUrl = "http://localhost:" + port
}
