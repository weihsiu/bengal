package bengal.instances

import bengal._

trait list {
  implied [A: Monoid] for Monoid[List[A]] {
    def empty = Nil
    def (x: List[A]) combine (y: List[A]) = x.zip(y).map(_ |+| _)
  }
  implied for TraverseMonad[List] {
    def pure[A](x: A) = List(x)
    def (f: List[A => B]) ap [A, B] (x: List[A]) = (f, x) match {
      case (Nil, _) | (_, Nil) => Nil
      case (f :: _, xs) => xs.map(f)
    }
    def (x: List[A]) map [A, B] (f: A => B) = x.map(f)
    def (x: List[A]) flatMap [A, B] (f: A => List[B]) = x.flatMap(f)
    def (x: List[A]) foldL [A, B] (z: B)(f: (B, A) => B) = x.foldLeft(z)(f)
    def (x: List[A]) foldR [A, B] (z: B)(f: (A, B) => B) = x.foldRight(z)(f)
    def (x: List[A]) traverse [G[_], A, B] (f: A => G[B]) given (AG: Applicative[G]) =
      x.foldRight(AG.pure(Nil))((x, z) => AG.map2(f(x), z)(_ :: _))
  }
  implied [A] given (O: Ord[A]) for Ord[List[A]] {
    def compare(xs: List[A], ys: List[A]) = (xs, ys) match {
      case (Nil, Nil) => 0
      case (Nil, _) => -1
      case (_, Nil) => 1
      case (x :: xs1, y :: ys1) =>
        val r = O.compare(x, y)
        if (r != 0) r else compare(xs1, ys1)
    }
  }
}