package bengal.examples

import bengal._
import bengal.data._
import bengal.data.Maybe._
import delegate bengal.delegates._

object Example5 extends App {
  case class Manifest(weight: Int, items: List[String]) derives Monoid
  val m1 = Manifest(10, List("tape", "paper"))
  val m2 = Manifest(20, List("pen", "eraser"))
  println(m1 |+| m2)

  case class Box[A](x: A) derives Functor
  
  val b1 = Box(1)
  val b2 = Box("hello")
  val f = the[Functor[Box]]

  println(b1 map (_ + 1))
  println(b2 map (_.toUpperCase))

  println(Just("hello") map (_.length))
}