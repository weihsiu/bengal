package bengal.examples

import bengal.*
import bengal.givens.{*, given}

object Example2:
  @main
  def example2Main() =
      def max[T](x: T, y: T)(using ord: Ord[T]): T =
        if (ord.compare(x, y) < 1) y else x
      max(1, 2)
      // max(List(1, 2), List(3, 4))
      max(1, 2)(using numericOrd[Int])