package bengal.givens

import bengal.Monoid

given tupleMonoid[A, B](using M1: Monoid[A], M2: Monoid[B]): Monoid[(A, B)] with
  val empty = (M1.empty, M2.empty)
  extension (x: (A, B))
    def combine(y: (A, B)) = (x._1 |+| y._1, x._2 |+| y._2)