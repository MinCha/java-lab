package doctor.interfaces

import doctor.ServerIntegrationTest
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner])
class ChannelControllerTest extends ServerIntegrationTest {
  val Log = LoggerFactory.getLogger(classOf[ChannelControllerTest])
  val restTemplate = new TestRestTemplate

  @Test
  def canFindChannelMessages() {
    val result = restTemplate.getForEntity(baseUrl + "/channel?channelId=1", classOf[String])

    Log.info(result.getBody)
    assert(result.getStatusCode == HttpStatus.OK)
  }

  @Test
  def canFindChannels() {
    val result = restTemplate.getForEntity(baseUrl + "/channel/my?userId=1", classOf[String])
    Log.info(result.getBody)
    assert(result.getStatusCode == HttpStatus.OK)
  }
}
