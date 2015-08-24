package doctor.domain

import java.lang.Long

import org.springframework.data.repository.CrudRepository

trait UserRepository extends CrudRepository[User, Long] {
  def findByUsername(username: String): User
}