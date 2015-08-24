package doctor.domain

import java.util.{ArrayList, List}
import javax.persistence._


import doctor.domain.shared.Domain

import scala.collection.JavaConversions._

@Entity
class Channel(pName: String) extends Domain {
  @Id
  @GeneratedValue
  var id: Int = _

  val name = pName

  @OneToMany(mappedBy = "channel", cascade = Array(CascadeType.PERSIST, CascadeType.REMOVE), fetch = FetchType.LAZY)
  val messages: List[Message] = new ArrayList[Message]

  def addMessage(userId: Int, text: String, lastMessage: Option[Message]) = {
    val lastSequence = if (lastMessage.isEmpty) 0 else lastMessage.get.sequence
    val m = new Message(this, userId, text, lastSequence)
    messages.add(m)
    m
  }

  def lastMessage = {
    if (messages.isEmpty) None
    else Some(messages.last)
  }

  def lastMessageSequence = lastMessage.get.sequence

  def this() = this("")
}
