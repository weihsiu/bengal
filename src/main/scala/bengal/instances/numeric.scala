package bengal.instances

import bengal._

trait numeric {
  implied [A] given (n: Numeric[A]) for Monoid[A] {
    def empty = n.zero
    def (x: A) combine (y: A) = n.plus(x, y)
  } 
  implied NumericOrd[A] given (n: Numeric[A]) for Ord[A] {
    def compare(x: A, y: A) = n.compare(x, y)
  }
}