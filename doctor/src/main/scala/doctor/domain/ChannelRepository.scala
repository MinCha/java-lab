package doctor.domain

import org.springframework.data.repository.CrudRepository

trait ChannelRepository extends CrudRepository[Channel, Integer]
