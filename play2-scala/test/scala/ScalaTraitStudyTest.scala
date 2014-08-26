package scala

import org.junit.Test

/**
 * Created by Vayne on 2014. 8. 26..
 */
class ScalaTraitStudyTest {
  abstract class Animal {
    def say(): String
  }

  class Dog extends Animal {
    def say(): String = {
      return "Dog"
    }
  }

  trait DogSong extends Animal {
    abstract override def say(): String = {
      return super.say() + "DogSong"
    }
  }

  trait DogLol extends Animal {
    abstract override def say(): String = {
      return super.say() + "DogLol"
    }
  }ㅂ버

  @Test def stackableModification(): Unit = {
    assert(new Dog().say() == "Dog")
    assert((new Dog() with DogSong).say() == "DogDogSong")
    assert((new Dog() with DogSong with DogLol).say() == "DogDogSongDogLol")
  }
}
