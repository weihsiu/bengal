package bengal.examples

import bengal.data.Maybe
import bengal.data.Maybe._
// import scala.language.strictEquality

object Example3 {
  def main(args: Array[String]): Unit = {
    val x1: Maybe[Int] = Just(1)
    val y1: Maybe[Int] = Empty
    x1 == y1
    //x1 == 1

    val x2: Option[Int] = Some(1)
    val y2: Option[Int] = None
    x2 == y2
    //x2 == 1
  }
}