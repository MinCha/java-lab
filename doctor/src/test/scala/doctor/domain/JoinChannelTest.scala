package doctor.domain

import doctor.UnitTest
import org.junit.Test

class JoinChannelTest extends UnitTest {

  @Test def canComputeUnreadCount() {
    val c = aSomeChannel()
    val u = aSomeUser()
    val joinedChannel = u.joinsTo(c)
    val messageA = c.addMessage(1, "Hello", aSomeLastMessage(c, 0))
    val messageB = c.addMessage(2, "world", aSomeLastMessage(c, 1))

    assert(joinedChannel.unreadCount == 2)
  }

  @Test def canComputeUnreadCountFromLastReadMessage() {
    val c = aSomeChannel()
    val u = aSomeUser()
    val joinedChannel = u.joinsTo(c)
    val messageA = c.addMessage(1, "Hello", aSomeLastMessage(c, 0))
    val messageB = c.addMessage(2, "world", aSomeLastMessage(c, 1))

    joinedChannel.readUntil(messageA)

    assert(joinedChannel.unreadCount == 1)
  }
}
