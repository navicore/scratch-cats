package onextent.scratch.scratchcats.catstests

import cats._
import cats.implicits._
import com.typesafe.scalalogging.LazyLogging
import onextent.scratch.scratchcats.Models.Cat
import org.scalatest._

class ShowSyntaxSpec extends FlatSpec with LazyLogging {

  "A cat" should "print formatly via syntax" in {

    implicit val catShow: Show[Cat] =
      Show.show(c => s"I am a cat with ${c.color} fur!")

    val cat = Cat("Garfield", 60, "white")

    assertResult("I am a cat with white fur!")(cat.show)

    println(cat.show)

  }

}
