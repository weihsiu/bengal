package bengal.instances

import bengal._

trait numeric {
  delegate [A] for Monoid[A] given (n: Numeric[A]) {
    def empty = n.zero
    def (x: A) combine (y: A) = n.plus(x, y)
  } 
  delegate NumericOrd[A] for Ord[A] given (n: Numeric[A]) {
    def compare(x: A, y: A) = n.compare(x, y)
  }
}