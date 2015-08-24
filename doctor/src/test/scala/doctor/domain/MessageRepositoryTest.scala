package doctor.domain

import doctor.IntegrationTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import scala.collection.JavaConversions._

class MessageRepositoryTest extends IntegrationTest {
  @Autowired
  val sut: MessageRepository = null

  @Test
  def canSave() {
    val channel = aPersistedSomeChannel()
    val message = new Message(channel, 1, "hello", 0)

    sut.save(message)
    flushAndClear()

    val result = sut.findOne(message.id)
    assert(result.id == message.id)
  }

  @Test
  def canFindGreaterThanSomeId() {
    val c = aPersistedSomeChannel()
    val a = new Message(c, 1, "hello one", 0)
    val b = new Message(c, 2, "hello two", 1)
    val last = new Message(c, 3, "hello three", 2)

    sut.save(List(a, b, last))
    flushAndClear()

    val result = sut.findByChannelIdAndIdGreaterThan(c.id, a.id)
    assert(result.size == 2)
    assert(result.head.id == b.id)
    assert(result.last.id == last.id)
  }

  @Test
  def canFindLastSequence() {
    val c = aPersistedSomeChannel()
    val a = new Message(c, 1, "hello one", 1)
    val b = new Message(c, 2, "hello two", 2)
    val last = new Message(c, 3, "hello three", 3)

    sut.save(List(a, b, last))
    flushAndClear()

    val result = sut.findTop1ByChannelIdOrderByIdDesc(c.id)
    assert(result.sequence == last.sequence)
  }

  @Test
  def canHandleMessageTypes() {
    val c = aPersistedSomeChannel()
    val a = new Message(c, 1, "hello one", 1, MessageType.PLAIN)
    val b = new Message(c, 2, "hello one", 2, MessageType.NEW_CHANNEL)

    sut.save(a)
    sut.save(b)
    flushAndClear()

    val result = sut.findByChannelIdOrderByIdAsc(c.id)
    assert(result.size() == 2)
    assert(result.head.id == a.id)
    assert(result.last.id == b.id)
  }
}