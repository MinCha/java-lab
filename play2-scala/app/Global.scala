import org.springframework.context.support.ClassPathXmlApplicationContext
import play.api._

object Global extends GlobalSettings {
  var ctx: ClassPathXmlApplicationContext = _

  override def onStart(app: Application) {
    Logger.info("Loading Spring Context")
    ctx = new ClassPathXmlApplicationContext("classpath:spring.xml")
    ctx.start()
    Logger.info("Bean Count : " + ctx.getBeanDefinitionCount)
    ctx.getBeanDefinitionNames.foreach(println)
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    Logger.info("Loading controller : " + controllerClass)
    ctx.getBean(controllerClass)
  }

  override def onStop(app: Application) {
    ctx.close()
    super.onStop(app)
  }
}
