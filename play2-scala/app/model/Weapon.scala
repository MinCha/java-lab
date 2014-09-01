package model

import javax.persistence._

import org.hibernate.annotations.{FetchMode, Fetch}

import scala.beans.BeanProperty

/**
 * Created by Vayne on 2014. 8. 28..
 */
@Entity(name="weapon")
class Weapon (c: Champion, n: String) extends BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var name = n

  @BeanProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="champion_id")
  var champion: Champion = c

  def this() = this(null, null)
}
