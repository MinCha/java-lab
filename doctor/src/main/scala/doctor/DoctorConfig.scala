package doctor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = Array("doctor"))
class DoctorConfig

object WebApplication extends App {
  SpringApplication.run(classOf[DoctorConfig]);
}