package doctor.domain

import org.springframework.data.repository.CrudRepository

trait JoinChannelRepository extends CrudRepository[JoinChannel, Integer] {
  def findByUserIdAndChannelId(userId: Long, channelId: Int): JoinChannel
}
