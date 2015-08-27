package doctor.interfaces

import doctor.domain.DoctorService
import doctor.interfaces.view.DoctorView
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RestController}

@RestController
class DoctorAPIController(@Autowired doctorService: DoctorService) extends BaseController {
  val Log = LoggerFactory.getLogger(classOf[DoctorAPIController])

  @RequestMapping(Array("/doctor/{name}"))
  def findChannelList(@PathVariable name: String) = {
    ensure("name")(name)
    Log.info("Name -> " + name)
    println(doctorService.champion(name))
    new DoctorView(name, doctorService.champion(name))
  }

  def this() = this(null)
}