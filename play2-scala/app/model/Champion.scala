package model

import javax.persistence.{GenerationType, GeneratedValue, Id, Entity}

import scala.beans.BeanProperty

/**
 * Created by Vayne on 2014. 7. 28..
 */
@Entity
class Champion (private var name: String, private var skill: String) extends BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Int = _

  def this() = this(null, null)
}