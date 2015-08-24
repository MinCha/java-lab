package doctor.interfaces.view

import doctor.domain.{JoinChannel, Message}
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonIgnoreProperties, JsonInclude, JsonPropertyOrder}

import scala.collection.JavaConverters._

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, setterVisibility =
  Visibility.NONE)
trait View

class PlainListView(list: List[Any]) extends View {
  def getItems = list.asJava
}

class ListView(hasNext: Boolean, list: List[Any]) extends View {
  def getHasNext = hasNext

  def getSize = list.size

  def getItems = list.asJava
}

class ChannelMessageListView(channelId: Int, hasNext: Boolean, list: List[Any]) extends ListView(hasNext, list) {
  def getChannelId = channelId
}

class MessageDetailView(m: Message) extends View {
  def getId = m.id

  def getUserId = m.userId

  def getText = m.text

  def getType = m.messageType.toString
}

class JoinedChannelView(c: JoinChannel) extends View {
  def getChannelId = c.channel.id

  def getChannelName = c.channel.name

  def getUnreadCount = c.unreadCount

  def getLastReadMessageId = c.lastReadMessageId
}
