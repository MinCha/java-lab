package doctor.domain

import java.lang.Math._

import dto.Stats.ChampionStats
import main.java.riotapi.RiotApi
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConversions._

@Service
class DoctorService(@Autowired api: RiotApi) {
  val Log = LoggerFactory.getLogger(classOf[DoctorService])
  val MinimumChampion = 6
  val MinimumGameCount = 5

  def champion(name: String) = {
    val id = api.getSummonerByName(name).getId

    val result = api.getRankedStats(id).getChampions.flatMap { e =>
      val s = ChampionStat(e)

      if (s.champion.isDefined) {
        Log.info(s.toString)
        Some(s)
      } else {
        None
      }
    }.filter(_.gameCount >= MinimumGameCount).sortWith(_.score > _.score)

    if (result.size < MinimumChampion)
      ChampionDoctor(n = name, b = result.toList)
    else ChampionDoctor(
      n = name,
      b = result.take(3).toList,
      w = result.takeRight(3).reverse.toList)
  }

  def this() = this(null)
}