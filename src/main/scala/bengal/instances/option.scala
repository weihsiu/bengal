package bengal.instances

import bengal._

trait option {
  delegate [A] for Monoid[Option[A]] given Monoid[A] {
    def empty = None
    def (x: Option[A]) combine (y: Option[A]) = (x, y) match {
      case (Some(x), Some(y)) => Some(x |+| y)
      case _ => None
    }
  }
  delegate for Functor[Option] {
    def (x: Option[A]) map [A, B] (f: A => B) = x.map(f)
  }
  delegate for TraverseMonad[Option] {
    def pure[A](x: A) = Some(x)
    def (f: Option[A => B]) ap [A, B] (x: Option[A]) = (f, x) match {
      case (Some(f), Some(x)) => Some(f(x))
      case _ => None
    }
    def (x: Option[A]) map [A, B] (f: A => B) = x.map(f)
    def (x: Option[A]) flatMap [A, B] (f: A => Option[B]) = x.flatMap(f)
    def (x: Option[A]) foldL [A, B] (z: B)(f: (B, A) => B) = x.foldLeft(z)(f)
    def (x: Option[A]) foldR [A, B] (z: B)(f: (A, B) => B) = x.foldRight(z)(f)
    def (x: Option[A]) traverse [G[_], A, B] (f: A => G[B]) given (AG: Applicative[G]) =
      x.foldRight(AG.pure(None))((x, z) => f(x) map pure)
  }
}