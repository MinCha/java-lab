package doctor.interfaces

import doctor.ServerIntegrationTest
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner])
class ChannelAPIControllerTest extends ServerIntegrationTest {
  val Log = LoggerFactory.getLogger(classOf[ChannelAPIControllerTest])
  val restTemplate = new TestRestTemplate

  @Test
  def canCreateChannel() {
    val result = restTemplate.postForEntity(
      baseUrl + "/channel",
      postParameter(List(
        "name" -> "new channel",
        "userId" -> "1")), classOf[String])

    Log.info(result.getBody)

    assert(result.getBody.length > 0)
    assert(result.getStatusCode == HttpStatus.OK)
  }

  @Test
  def canJoinToChannel() {
    val result = restTemplate.postForEntity(
      baseUrl + "/channel/join",
      postParameter(List(
        "channelId" -> "1",
        "userId" -> "2")), classOf[String])

    Log.info(result.getBody)

    assert(result.getBody.length > 0)
    assert(result.getStatusCode == HttpStatus.OK)
  }
}
