package doctor.domain

import doctor.IntegrationTest
import main.java.riotapi.RiotApi
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

  @Test def canHaveInsight() {
    val id = api.getSummonerByName("광견마").getId

    val lines = api.getMatchList(id).getMatches.take(2).flatMap { e =>
      api.getMatch(e.getMatchId).getParticipants.map { t =>
        t.getTimeline
      }
    }.groupBy(_.getLane)

    println(lines.map { e =>
      e._2
      .map(_.getCsDiffPerMinDeltas)
      .map(cs => TimelineData(e._1, cs.getZeroToTen, cs.getTenToTwenty, cs.getTwentyToThirty, cs.getThirtyToEnd))
      .fold(TimelineData(e._1))(_

        + _)
    })
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