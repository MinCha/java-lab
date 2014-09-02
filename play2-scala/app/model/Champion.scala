package model

import java.util
import javax.persistence._

import org.hibernate.annotations.{FetchMode, Fetch}

import scala.beans.BeanProperty

/**
 * Created by Vayne on 2014. 7. 28..
 */
@Entity(name = "champion")
class Champion (var n: String, var s: String) extends BaseModel {
  @BeanProperty
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Int = _

  @BeanProperty
  var name = n

  @BeanProperty
  var skill = s

  @BeanProperty
  @OneToMany(mappedBy="champion", cascade = Array(CascadeType.ALL), orphanRemoval = true, fetch = FetchType.LAZY)
  var weapons: util.List[Weapon] = new util.ArrayList[Weapon]()

  def this() = this(null, null)

  def addWeapon(name : String) {
      this.addWeapon(new Weapon(this, name))
  }

  def addWeapon(weapon: Weapon) {
    this.weapons.add(weapon)
  }
}
