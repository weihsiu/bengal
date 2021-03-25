package bengal.givens

import bengal.Monoid

given intMonoid: Monoid[Int] with
  val empty = 0
  extension (x: Int)
    def combine(y: Int) = x + y 

object WrappedInt:
  opaque type SumInt = Int
  object SumInt:
    def apply(x: Int): SumInt = x
    def value(x: SumInt): Int = x

  given sumIntMonoid: Monoid[SumInt] with
    val empty = 0
    extension (x: SumInt)
      def combine(y: SumInt) = x + y

  opaque type ProdInt = Int
  object ProdInt:
    def apply(x: Int): ProdInt = x
    def value(x: ProdInt): Int = x

  given prodIntMonoid: Monoid[ProdInt] with
    val empty = 1
    extension (x: ProdInt)
      def combine(y: ProdInt) = x * y