package onextent.scratch.scratchcats

import com.typesafe.scalalogging.LazyLogging
import onextent.scratch.scratchcats.Models.Cat
import org.scalatest._

class PrintableInterfaceObjectSpec extends FlatSpec with LazyLogging {

  "An obj" should "format" in {

    import PrintableInstances._
    val str = Printable.format("hiya")
    assert(str.equals("hiya"))
    val intStr = Printable.format(123)
    assert(intStr.equals("123"))

  }

  "A cat" should "format" in {

    import PrintableInstances._
    val cat = Cat("Gargield", 60, "brown")
    assertResult("I am a cat with brown fur!")(Printable.format(cat))

  }

}