package bengal.examples

import bengal._
import implied bengal.instances.all._

object Example2 {
  def main(args: Array[String]): Unit = {
    def max[T](x: T, y: T) given (ord: Ord[T]): T =
      if (ord.compare(x, y) < 1) y else x
    max(1, 2)
    max(List(1, 2), List(3, 4))
    max(1, 2) given NumericOrd[Int]
  }
}