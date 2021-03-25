package bengal.givens

import bengal.*

given [A](using N: Numeric[A]): Monoid[A] with
  val empty = N.zero
  extension (x: A)
    def combine(y: A) = N.plus(x, y)

given NumericOrd[A](using N: Numeric[A]): Ord[A] with
  def compare(x: A, y: A) = N.compare(x, y)