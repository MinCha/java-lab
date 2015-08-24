package doctor.interfaces

import doctor.domain._
import doctor.interfaces.view.{ChannelMessageListView, MessageDetailView}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

import scala.collection.JavaConversions._

@RestController
class MessageAPIController(@Autowired messageService: MessageService,
                           @Autowired joinChannelRepository: JoinChannelRepository,
                           @Autowired messageRepository: MessageRepository) extends BaseController {

  @RequestMapping(Array("/messages"))
  def findMessagesGreaterThanId(userId: Int, channelId: Int) = {
    val messages = messageService.readUnread(userId, channelId)
    new ChannelMessageListView(channelId, false, messages.map(new MessageDetailView(_)).toList)
  }

  @RequestMapping(value = Array("/message"), method = Array(RequestMethod.POST))
  def add(channelId: Int, userId: Int, text: String) = {
    new MessageDetailView(messageService.add(channelId, userId, text))
  }

  def this() = this(null, null, null)
}
