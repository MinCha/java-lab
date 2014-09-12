package scala

import org.junit.Test

/**
 * Date : 2014. 8. 26
 * Author : Vayne
 */
class TraitTest {
  abstract class Animal {
    def say(): String
  }

  class Dog extends Animal {
    def say(): String = "Dog"
  }

  trait DogSong extends Animal {
    abstract override def say(): String = super.say() + "DogSong"
  }

  trait DogLol extends Animal {
    abstract override def say(): String = super.say() + "DogLol"
  }

  @Test def stackableModification(): Unit = {
    assert(new Dog().say() == "Dog")
    assert((new Dog() with DogSong).say() == "DogDogSong")
    assert((new Dog() with DogSong with DogLol).say() == "DogDogSongDogLol")
  }
}
