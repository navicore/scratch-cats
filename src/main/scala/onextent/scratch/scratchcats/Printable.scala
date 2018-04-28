package onextent.scratch.scratchcats

import onextent.scratch.scratchcats.Models.Cat

trait Printable[A] {
  def format(value: A): String
  def print(value: A): Unit
}

object PrintableInstances {

  implicit val stringPrinter: Printable[String] = new Printable[String] {
    override def format(value: String): String = value.toString
    override def print(value: String): Unit = println
  }

  implicit val intPrinter: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
    override def print(value: Int): Unit = println
  }

  implicit val catPrinter: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = {
      s"I am a cat with ${value.color} fur!"
    }
    override def print(cat: Cat): Unit = {
      println(format(cat))
    }
  }

}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)
  def print[A](value: A)(implicit p: Printable[A]): Unit = p.print(value)
}


object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)
    def print(implicit p: Printable[A]): Unit = p.print(value)
  }

}
