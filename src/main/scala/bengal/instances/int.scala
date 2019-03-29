package bengal.instances

import bengal.Monoid

opaque type SumInt = Int
object SumInt {
  def apply(x: Int): SumInt = x
  def value(x: SumInt): Int = x
}

opaque type ProdInt = Int
object ProdInt {
  def apply(x: Int): ProdInt = x
  def value(x: ProdInt): Int = x
}

trait int {
  implied for Monoid[SumInt] {
    def empty = SumInt.apply(0)
    def (x: SumInt) combine (y: SumInt) = SumInt(SumInt.value(x) + SumInt.value(y))
  }
  implied for Monoid[ProdInt] {
    def empty = ProdInt(1)
    def (x: ProdInt) combine (y: ProdInt) = ProdInt(ProdInt.value(x) * ProdInt.value(y))
  }
}