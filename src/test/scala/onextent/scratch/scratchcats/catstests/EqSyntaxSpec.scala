package onextent.scratch.scratchcats.catstests

import cats.Eq
import cats.Show
import cats.implicits._
import com.typesafe.scalalogging.LazyLogging
import onextent.scratch.scratchcats.Models.Cat
import org.scalatest._

class EqSyntaxSpec extends FlatSpec with LazyLogging with Matchers {

  "A cat" should "equal a cat via syntax" in {

    val convertToEqualizer = ()  // shadow ScalaTest

    implicit val catEq: Eq[Cat] =
      Eq.instance[Cat] { (cat1, cat2) =>
        cat1.toString === cat2.toString // NOTE Intellij can't find === but it is there for String via an Eq instance
      }

    implicit val catShow: Show[Cat] =
      Show.show(c => s"I am a cat with ${c.color} fur!")

    val cat1 = Cat("Garfield", 60, "orange")
    val cat2 = Cat("Not Garfield", 60, "blue")
    val cat3 = "Really Not Garfield"
    val cat4 = Cat("Garfield", 60, "orange")

    assertResult("I am a cat with orange fur!")(cat1.show)

    val r1 = cat1 === cat4
    assertResult(true)(r1)

    val r2 = cat1 === cat4
    assertResult(true)(r2)

    //val r3 = cat1 === cat3 // compile error since there is no Eq imple for Cat and String!  yay!!

    val r4 = cat1 === cat2
    assertResult(false)(r4)

    println(cat1.show)
    println(cat2.show)

  }

  "An optional cat" should "equal an optional cat" in {

    val convertToEqualizer = ()  // shadow ScalaTest

    implicit val catEq: Eq[Cat] =
      Eq.instance[Cat] { (cat1, cat2) =>
        cat1.toString === cat2.toString // NOTE Intellij can't find === but it is there for String via an Eq instance
      }

    val cat1 = Cat("Garfield", 60, "orange")

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    assertResult(false)(optionCat1 === optionCat2)

  }


}
