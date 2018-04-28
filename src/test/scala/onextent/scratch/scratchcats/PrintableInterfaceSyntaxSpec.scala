package onextent.scratch.scratchcats

import com.typesafe.scalalogging.LazyLogging
import onextent.scratch.scratchcats.Models.Cat
import org.scalatest._

class PrintableInterfaceSyntaxSpec extends FlatSpec with LazyLogging {

  "A cat" should "print formatly via syntax" in {

    import PrintableInstances._
    import PrintableSyntax._
    val cat = Cat("Garfield", 60, "white")
    assertResult("I am a cat with white fur!")(Printable.format(cat))

    cat.print

  }

}