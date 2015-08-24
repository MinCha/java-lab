package doctor.interfaces

import doctor.domain._
import doctor.interfaces.view.{JoinedChannelView, ListView}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

import scala.collection.JavaConversions._

@RestController
class ChannelAPIController(@Autowired channelRepository: ChannelRepository,
                           @Autowired joinChannelRepository: JoinChannelRepository,
                           @Autowired userRepository: UserRepository) extends BaseController {
  @RequestMapping(Array("/channel/my"))
  def findChannelList(userId: Long) = {
    val u = ensure("user") {userRepository.findOne(userId)}
    new ListView(false, u.joinedChannels.map(new JoinedChannelView(_)).toList)
  }

  @RequestMapping(value = Array("/channel/join"), method = Array(RequestMethod.POST))
  def joinToChannel(userId: Long, channelId: Int) = {
    val u = ensure("user") {userRepository.findOne(userId)}
    val c = ensure("channel") {channelRepository.findOne(channelId)}
    val joinChannel = new JoinChannel(u, c)
    new JoinedChannelView(joinChannelRepository.save(joinChannel))
  }

  @RequestMapping(value = Array("/channel"), method = Array(RequestMethod.POST))
  def createChannel(name: String, userId: Long) = {
    val u = ensure("user") {userRepository.findOne(userId)}
    val c = channelRepository.save(new Channel(name))
    val joinChannel = new JoinChannel(u, c)
    new JoinedChannelView(joinChannelRepository.save(joinChannel))
  }

  def this() = this(null, null, null)
}
