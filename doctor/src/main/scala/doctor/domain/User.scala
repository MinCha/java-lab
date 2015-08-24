package doctor.domain

import java.lang.Long
import java.util.{ArrayList, List}
import javax.persistence._

import doctor.domain.shared.Domain


@Entity
class User(pUsername: String, pPassword: String) extends Domain {
  @Id
  @GeneratedValue
  var id: Long = _

  val username = pUsername

  val password = pPassword

  @OneToMany(mappedBy = "user", cascade = Array(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE), fetch = FetchType.LAZY)
  val joinedChannels: List[JoinChannel] = new ArrayList[JoinChannel]

  def joinsTo(channel: Channel) = {
    val c = new JoinChannel(this, channel)
    joinedChannels.add(c)
    c
  }

  def isGuest = false

  def this() = this("", "")
}