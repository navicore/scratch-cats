package onextent.scratch.scratchcats

import com.typesafe.scalalogging.LazyLogging
import org.scalatest._
import onextent.data.navipath.dsl.NaviPathSyntax._

import scala.io.Source

class ScratchSpec extends FlatSpec with LazyLogging {

  val jsonString: String = Source.fromResource("widget.json").mkString

  "A json string" should "parse" in {

    val parsedJson = jsonString.asJson
    val debug = parsedJson.query[String]("$.widget.debug")
    assert(debug.contains("on"))

  }

  // Define a very simple JSON AST
  sealed trait MyJson
  final case class JsObject(get: Map[String, MyJson]) extends MyJson
  final case class JsString(get: String) extends MyJson
  final case class JsNumber(get: Double) extends MyJson
  case object JsNull extends MyJson

  // The "serialize to JSON" behaviour is encoded in this trait
  trait MyJsonWriter[A] {
    def write(value: A): MyJson
  }

  final case class Human(name: String, email: String)

  object MyJsonWriter {

    implicit val stringWriter: MyJsonWriter[String] =
      (value: String) => JsString(value)

    implicit val humanWriter: MyJsonWriter[Human] =
      (value: Human) => JsObject(
        Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        ))

    implicit class MyJsonWriterOps[A](value: A) {
      def toJson(implicit w: MyJsonWriter[A]): MyJson =
        w.write(value)
    }

  }

  "An obj" should "be json via syntax" in {

    //import MyJsonWriterInstances._
    //import MyJsonSyntax._
    import MyJsonWriter._

    val dave = Human("Dave", "dave@example.com")
    println(s"syntax 1 ${dave.toJson}")

    val daveJson = Human("Dave", "dave@example.com").toJson
    println(s"syntax 2 $daveJson")

  }

  "An obj" should "be be found implicitly" in {

    //import MyJsonWriterInstances._

    val o = implicitly[MyJsonWriter[String]]

    println(s"implicitly $o")

  }
}