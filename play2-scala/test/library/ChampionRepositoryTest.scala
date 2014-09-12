package library

import javax.persistence.{EntityManager, PersistenceContext}

import model.{Champion, ChampionRepository, Weapon}
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Vayne on 2014. 8. 26..
 */
@RunWith(classOf[SpringJUnit4ClassRunner] )
@ContextConfiguration (Array ("classpath:spring.xml"))
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
class ChampionRepositoryTest() {
  @Autowired val sut: ChampionRepository = null
  @PersistenceContext val context: EntityManager = null

  @Test def findBySkill(): Unit = {
    val champion = new Champion("vayne", "tumble")
    champion.addWeapon(new Weapon(champion, "first arrow"))
    champion.addWeapon(new Weapon(champion, "second arrow"))
    sut.save(champion)
    clearAndFlush

    val result = sut.findBySkill("tumble")

    assert(result.get(0).getName == "vayne")
  }

  @Test def findByName(): Unit = {
    val champion = new Champion("vayne", "tumble")
    champion.addWeapon(new Weapon(champion, "first arrow"))
    champion.addWeapon(new Weapon(champion, "second arrow"))
    sut.save(champion)
    clearAndFlush

    val result = sut.findByName("vayne")

    assert(result.getSkill == "tumble")
    assert(result.getWeapons.size() == 2)
    assert(result.getWeapons.get(0).getName == "first arrow")
    assert(result.getWeapons.get(1).getName == "second arrow")
  }

  @Test def find(): Unit = {
    val champion = new Champion("vayne", "tumble")
    champion.addWeapon(new Weapon(champion, "first arrow"))
    champion.addWeapon(new Weapon(champion, "second arrow"))
    val id = sut.save(champion)
    clearAndFlush

    //TODO 여기서만 Join 동작함. 왜?
    sut.findOne(id.getId)

    val result = sut.findByName("vayne")

    assert(result.getSkill == "tumble")
  }

  def clearAndFlush: Unit = {
    context.clear()
    context.flush()
  }
}
