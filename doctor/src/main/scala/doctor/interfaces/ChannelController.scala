package doctor.interfaces

import doctor.domain.{ChannelRepository, UserRepository}
import doctor.interfaces.view.{ChannelView, MessageDetailView, PlainListView}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import scala.collection.JavaConversions._

@Controller
class ChannelController(@Autowired channelRepository: ChannelRepository,
                        @Autowired userRepository: UserRepository) extends BaseController {

  @RequestMapping(Array("/channel"))
  def findChannelEntry(channelId: Int, model: Model) = {
    val c = ensure("channel") {channelRepository.findOne(channelId)}
    model.addAttribute("c", new ChannelView(c))
    "channel/index"
  }

  def this() = this(null, null)
}