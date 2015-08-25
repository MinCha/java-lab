package doctor.infrastructure

import java.util.Date

import constant.Region
import doctor.IntegrationTest
import main.java.riotapi.RiotApi
import org.joda.time.DateTime
import org.junit.Test

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class ChampionsServiceTest extends IntegrationTest {
  val r = Region.KR
  val s = "집사 털의요정"

  @Test def canRecommendChampions() {
    val api = new RiotApi("1183e71a-a4a4-4e05-9299-17718fb70591", r)

    println(api.getChampions)

    val id = api.getSummonerByName(s).getId

    println(api.getMatchList(id).getMatches.foreach(e => println(e.getMatchId)))
    val m = api.getMatch(1455708833)
    println(m)

    println(api.getRankedStats(id))
  }
}
