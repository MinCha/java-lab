package doctor.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scala.collection.JavaConversions._

@Service
class MessageService(
                    @Autowired joinChannelRepository: JoinChannelRepository,
                    @Autowired channelRepository: ChannelRepository,
                    @Autowired messageRepository: MessageRepository) {

  @Transactional
  def readUnread(userId: Int, channelId: Int) = {
    val joinChannel = ensure("joinChannel") {joinChannelRepository.findByUserIdAndChannelId(userId, channelId)}
    val messages = messageRepository.findByChannelIdAndIdGreaterThan(channelId, joinChannel.lastReadMessageId)
    if (messages.isEmpty == false) joinChannel.readUntil(messages.last)
    messages
  }

  @Transactional
  def add(channelId: Int, userId: Int, text: String) = {
    val c = ensure("channel " + channelId) {channelRepository.findOne(channelId)}
    val last = toOption(messageRepository.findTop1ByChannelIdOrderByIdDesc(channelId))
    c.addMessage(userId, text, last)
  }

  private def toOption[A](any: A) = if (any == null) None else Some(any)

  private def ensure[T](alias: Any)(any: T): T = {
    require(any != null, s"""'$alias' does not exist.""")
    any
  }

  def this() = this(null, null, null)
}
