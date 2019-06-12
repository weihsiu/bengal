package bengal.examples

import bengal._
import bengal.instances._
import delegate bengal.instances.all._

object Example1 {
  def main(args: Array[String]): Unit = {
    println((Some(1) map (_ + 1)))
    println(1.0 |+| 2)
    println(SumInt(1) |+| SumInt(2))
    println(ProdInt(1) |+| ProdInt(2))
    val t1 = (1, "hello ")
    println(t1 |+| (2, "world"))
    println(Some(1).map(_ + 1))
    println(Some((_: Int) + 1) ap Some(1))
    println(Some((_: Int) + 1) <*> Some(2))
    println(Some((_: Int) + 1) ap None)
    println(Some(1) foldMap (_ + 1))
    println(List(1, 2, 3).foldR(0)(_ + _))
  }
}