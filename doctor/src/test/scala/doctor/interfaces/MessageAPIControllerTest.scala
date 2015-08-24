package doctor.interfaces

import doctor.ServerIntegrationTest
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner])
class MessageAPIControllerTest extends ServerIntegrationTest {
  val Log = LoggerFactory.getLogger(classOf[MessageAPIControllerTest])
  val restTemplate = new TestRestTemplate

  @Test
  def canFindMessagesGreaterThanId() {
    val result = restTemplate.getForEntity(baseUrl + "/messages?channelId=1&userId=1", classOf[String])

    Log.info(result.getBody)
    assert(result.getStatusCode == HttpStatus.OK)
  }

  @Test
  def canAddMessage() {
    val channel = aPersistedSomeChannel()

    val result = restTemplate.postForEntity(
      baseUrl + "/message",
      postParameter(List(
        "channelId" -> channel.id.toString,
        "userId" -> "1",
        "text" -> "hello")), classOf[String])

    assert(result.getBody.length > 0)
    assert(result.getStatusCode == HttpStatus.OK)
  }

  @Test
  def canDisplayUnread() {
    restTemplate.getForEntity(baseUrl + "/messages?channelId=1&userId=1", classOf[String])
    val result = restTemplate.getForEntity(baseUrl + "/channel/my?userId=1", classOf[String])

    assert(result.getBody.length > 0)
    assert(result.getStatusCode == HttpStatus.OK)
    assert(result.getBody.contains(""""unreadCount":0"""))
    assert(result.getBody.contains(""""unreadCount":4""") == false)
  }
}
