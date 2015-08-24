package doctor.domain

import javax.persistence._

import doctor.domain.shared.Domain


@Entity
class Message(pChannel: Channel,
              pUserId: Int,
              pText: String,
              pLastMessageSequence: Int,
              pMessageType: MessageType = MessageType.PLAIN) extends Domain {
  @Id
  @GeneratedValue
  var id: Int = _

  @ManyToOne
  @JoinColumn(name = "channel_id")
  val channel = pChannel

  val sequence = pLastMessageSequence + 1

  val userId = pUserId

  val text = pText

  def channelId = channel.id

  @Enumerated(EnumType.STRING)
  val messageType = pMessageType

  def this() = this(null, 0, "", 0)
}
