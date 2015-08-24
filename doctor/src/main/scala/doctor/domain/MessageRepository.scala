package doctor.domain

import org.springframework.data.repository.CrudRepository

trait MessageRepository extends CrudRepository[Message, Integer] {
  def findByChannelIdOrderByIdAsc(channelId: Int): java.util.List[Message]

  def findTop1ByChannelIdOrderByIdDesc(channelId: Int): Message

  def findByChannelIdAndIdGreaterThan(channelId: Int, lastId: Int): java.util.List[Message]
}
