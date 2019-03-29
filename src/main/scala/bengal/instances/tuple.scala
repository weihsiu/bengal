package bengal.instances

import bengal.Monoid

trait tuple {
  implied [A, B] given (m1: Monoid[A], m2: Monoid[B]) for Monoid[(A, B)] {
    def empty = (m1.empty, m2.empty)
    def (x: (A, B)) combine (y: (A, B)) = (x._1 |+| y._1, x._2 |+| y._2)
  }
}