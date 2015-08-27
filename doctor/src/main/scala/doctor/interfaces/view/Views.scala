package doctor.interfaces.view

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.{JsonAutoDetect, JsonIgnoreProperties, JsonInclude, JsonPropertyOrder}
import doctor.domain.{ChampionStat, ChampionDoctor}

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY, setterVisibility =
  Visibility.NONE)
trait View

class PlainListView(list: List[Any]) extends View {
  def getItems = list.asJava
}

class ListView(hasNext: Boolean, list: List[Any]) extends View {
  def getHasNext = hasNext

  def getSize = list.size

  def getItems = list.asJava
}

class DoctorView(name: String, c: ChampionDoctor) extends View {
  def getName = name

  def getBest = c.b.map(new ChampionView(_)).asJava

  def getWorst = c.w.map(new ChampionView(_)).asJava
}

class ChampionView(s: ChampionStat) extends View {
  def getName = s.name

  def getScore = s.score

  def getInformation = s.toString
}
