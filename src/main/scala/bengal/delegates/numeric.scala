package bengal.delegates

import bengal._

delegate [A] for Monoid[A] given (N: Numeric[A]) {
  def empty = N.zero
  def (x: A) combine (y: A) = N.plus(x, y)
}

delegate NumericOrd[A] for Ord[A] given (N: Numeric[A]) {
  def compare(x: A, y: A) = N.compare(x, y)
}