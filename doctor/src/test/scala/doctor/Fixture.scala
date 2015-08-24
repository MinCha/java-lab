package doctor

import doctor.domain._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.LinkedMultiValueMap

trait Fixture {
  var lastMessageSequence = 0

  def aSomeChannel() = new Channel("Some Channel")

  def aSomeUser() = new User("SomeId", "SomePw")

  def initLastMessageSequence = lastMessageSequence = 0

  def aSomeLastMessage(c: Channel): Option[Message] = {
    lastMessageSequence = lastMessageSequence + 1
    aSomeLastMessage(c, lastMessageSequence)
  }

  def aSomeLastMessage(c: Channel, s: Int): Option[Message] = {
    Some(new Message(c, 1, "hello", s - 1))
  }
}

trait IntegrationFixture extends Fixture {
  @Autowired
  val channelRepository: ChannelRepository = null
  @Autowired
  val userRepository: UserRepository = null

  def aPersistedSomeChannel() = channelRepository.save(aSomeChannel())

  def aPersistedSomeUser() = userRepository.save(aSomeUser())

  def postParameter(param: List[(String, String)]) = {
    val map = new LinkedMultiValueMap[String, String]()
    param.foreach { x => map.add(x._1, x._2) }
    map
  }
}
