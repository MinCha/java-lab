package doctor

import javax.persistence.{EntityManager, PersistenceContext}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitSuite
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[DoctorConfig]))
@Transactional
abstract class IntegrationTest extends JUnitSuite with IntegrationFixture {
  @PersistenceContext val context: EntityManager = null

  def flush() {
    context.flush
  }

  def clear() {
    context.clear
  }

  def flushAndClear() {
    flush()
    clear()
  }
}

abstract class UnitTest extends JUnitSuite with Fixture