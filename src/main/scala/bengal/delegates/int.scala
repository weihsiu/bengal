package bengal.delegates

import bengal.Monoid

object WrappedInts {
  opaque type SumInt = Int

  object SumInt {
    def apply(x: Int): SumInt = x
    def value(x: SumInt): Int = x
  }

  delegate for Monoid[SumInt] {
    def empty = 0
    def (x: SumInt) combine (y: SumInt) = x + y
  }
  
  opaque type ProdInt = Int

  object ProdInt {
    def apply(x: Int): ProdInt = x
    def value(x: ProdInt): Int = x
  }

  delegate for Monoid[ProdInt] {
    def empty = 1
    def (x: ProdInt) combine (y: ProdInt) = x * y
  }
}