package doctor.domain

import doctor.IntegrationTest
import main.java.riotapi.RiotApi
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

import scala.collection.JavaConversions._

class DoctorServiceTest extends IntegrationTest {
  val Log = LoggerFactory.getLogger(classOf[DoctorServiceTest])
  @Autowired val sut: DoctorService = null
  @Autowired val api: RiotApi = null

  @Test def canRecommendChampions() {
    printPretty(sut.champion("Icearrows"))
  }

  @Test def canAA() {
    println(new StandardDeviation().evaluate(Array(64, 67, 41, 34)))
  }

  @Test def canHaveInsight() {
    val id = api.getSummonerByName("광견마").getId

    val matches = api.getRecentGames(id).getGames
      .filter(_.getGameMode == "CLASSIC")
      .filter(e => e.getGameType == "CUSTOM_GAME" || e.getGameType == "MATCHED_GAME")
      .take(7)
      .map(e => api.getMatch(e.getGameId)).toList

    (for (x <- matches; y <- x.getParticipants) yield (y.getTimeline))
      .groupBy { e =>
        if (e.getLane == "BOTTOM") {
          if (e.getCreepsPerMinDeltas.getZeroToTen < 3) e.getLane + "_S"
          else e.getLane + "_C"
        } else {
          e.getLane
        }
      }.map { e =>
      e._2
        .map(_.getCreepsPerMinDeltas)
        .map(cs => TimelineData(e._1, cs.getZeroToTen, cs.getTenToTwenty, cs.getTwentyToThirty, cs.getThirtyToEnd))
        .fold(TimelineData(e._1))(_ + _)
    }.foreach(e => println(e.g + " -> " + e.sd + " " + e.median + " " + e.toString))
  }

  def printTimelineDate(t: TimelineData) {
    Log.info("%s -> SD(%1.2) MEDIAN")
  }

  def printPretty(d: ChampionDoctor) {
    Log.info(d.n + " + Best")
    d.b.foreach(e => Log.info("|_ " + e.name + " -> " + e.s.toString))
    if (d.w.isEmpty == false) {
      Log.info(d.n + " - Worst")
      d.w.foreach(e => Log.info("|_ " + e.name + " -> " + e.s.toString))
    }
  }
}