package doctor.domain

import javax.persistence._

import doctor.domain.shared.Domain


@Entity(name = "join_channel")
class JoinChannel(pUser: User, pChannel: Channel) extends Domain {
  @Id
  @GeneratedValue
  var id: Int = _

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  val user = pUser

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = " channel_id")
  val channel = pChannel

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "last_read_message_id")
  var lastReadMessage: Message = null

  def unreadCount = {
    if (hasMessage == false) 0
    else if (lastReadMessage == null) channel.lastMessageSequence
    else channel.lastMessageSequence - lastReadMessage.sequence
  }

  def hasMessage = channel.messages.isEmpty == false

  def lastReadMessageId = {
    if (lastReadMessage == null) 0 else lastReadMessage.id
  }

  def readUntil(m: Message) = {
    lastReadMessage = m
    m
  }

  def this() = this(null, null)

}
