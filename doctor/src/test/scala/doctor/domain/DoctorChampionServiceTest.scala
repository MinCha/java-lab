package doctor.domain

import doctor.IntegrationTest
import dto.MatchList.MatchList
import org.junit.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class ChampionsServiceTest extends IntegrationTest {
  val Log = LoggerFactory.getLogger(classOf[ChampionsServiceTest])
  @Autowired val sut: DoctorService = null

  @Test def canRecommendChampions() {
    printPretty(sut.champion("Icearrows"))
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