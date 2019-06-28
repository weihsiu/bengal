package bengal.delegates

import bengal.Monoid

delegate [A, B] for Monoid[(A, B)] given (M1: Monoid[A], M2: Monoid[B]) {
  def empty = (M1.empty, M2.empty)
  def (x: (A, B)) combine (y: (A, B)) = (x._1 |+| y._1, x._2 |+| y._2)
}