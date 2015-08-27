package doctor

import constant.Region
import main.java.riotapi.RiotApi
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = Array("doctor"))
class DoctorConfig {
  @Bean def riotApi = new RiotApi("1183e71a-a4a4-4e05-9299-17718fb70591", Region.KR)
}

object WebApplication extends App {
  SpringApplication.run(classOf[DoctorConfig]);
}