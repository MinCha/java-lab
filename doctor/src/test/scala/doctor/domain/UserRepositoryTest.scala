package doctor.domain

import doctor.IntegrationTest
import org.junit.Test

class UserRepositoryTest extends IntegrationTest {
  @Test def canJoinToChannel() {
    val user = aPersistedSomeUser()
    val channelA = aPersistedSomeChannel()
    val channelB = aPersistedSomeChannel()

    user.joinsTo(channelA)
    user.joinsTo(channelB)
    userRepository.save(user)

    flushAndClear()
    val result = userRepository.findOne(user.id)
    assert(result.joinedChannels.size() == 2)
  }
}
