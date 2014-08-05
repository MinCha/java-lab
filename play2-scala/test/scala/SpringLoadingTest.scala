package scala

import java.sql.{Connection, DriverManager}
import javax.persistence.{EntityManager, PersistenceContext}
import javax.sql.DataSource

import org.h2.Driver
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner] )
@ContextConfiguration (Array ("classpath:spring.xml"))
class SpringLoadingTest {

  @Test
  def springLoading() {
  }
}
