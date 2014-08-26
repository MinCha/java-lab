package scala

import model.{Champion, ChampionRepository}
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by Vayne on 2014. 8. 26..
 */
@RunWith(classOf[SpringJUnit4ClassRunner] )
@ContextConfiguration (Array ("classpath:spring.xml"))
class ChampionRepositoryTest() {
  @Autowired val sut: ChampionRepository = null

  @Test def findBySkill(): Unit = {
    val champ = new Champion("vayne", "tumble")
    sut.save(champ)

    val result = sut.findBySkill("tumble")

    assert(result.get(0).getName == "vayne")
  }

  @Test def findByName(): Unit = {
    val champ = new Champion("vayne", "tumble")
    sut.save(champ)

    val result = sut.findByName("vayne")

    assert(result.getSkill == "tumble")
  }
}
