package onextent.scratch.scratchcats.q

import org.scalatest._

class QSpec extends FlatSpec {

  class SlowAppendQueue[T](elems: List[T]) {

    def head: T = elems.head
    def tail: SlowAppendQueue[T] = new SlowAppendQueue[T](elems.tail)
    def enqueu(x: T): SlowAppendQueue[T] = new SlowAppendQueue[T](elems ::: List(x))

    override def toString: String = elems.mkString
  }

  "An append q" should "enqueue" in {

    val q = new SlowAppendQueue[Int](List(1,2,3))
    println(s"see q: $q")

    val q1 = q enqueu 4
    println(s"see q1: $q1")
    println(s"see q: $q")

  }

  class SlowHeadQueue[T](smele: List[T]) {

    def head: T = smele.last
    def tail: SlowHeadQueue[T] = new SlowHeadQueue(smele.init)
    def enqueu(x: T): SlowHeadQueue[T] = new SlowHeadQueue[T](x :: smele)

    override def toString: String = smele.reverse.mkString
  }

  "A head q" should "enqueue" in {

    val q = new SlowHeadQueue[Int](List(1,2,3).reverse)
    println(s"see q: $q")

    val q1 = q enqueu 4
    println(s"see q1: $q1")
    println(s"see q: $q")

  }

}
