package bengal.instances

import bengal._
import bengal.data.Maybe
import bengal.data.Maybe._

trait maybe {
  delegate [A] for Monoid[Maybe[A]] given Monoid[A] {
    def empty = Empty
    def (x: Maybe[A]) combine (y: Maybe[A]) = (x, y) match {
      case (Just(x), Just(y)) => Just(x |+| y)
      case _ => Empty
    }
  }
  delegate for TraverseMonad[Maybe] {
    def pure[A](x: A) = Just(x)
    def (f: Maybe[A => B]) ap [A, B] (x: Maybe[A]) = (f, x) match {
      case (Just(f), Just(x)) => Just(f(x))
      case _ => Empty
    }
    def (x: Maybe[A]) map [A, B] (f: A => B) = x match {
      case Just(x) => Just(f(x))
      case _ => Empty
    }
    def (x: Maybe[A]) flatMap [A, B] (f: A => Maybe[B]) = x match {
      case Just(x) => f(x)
      case _ => Empty
    }
    def (x: Maybe[A]) foldL [A, B] (z: B)(f: (B, A) => B) = x match {
      case Just(x) => f(z, x)
      case _ => z
    }
    def (x: Maybe[A]) foldR [A, B] (z: B)(f: (A, B) => B) = x match {
      case Just(x) => f(x, z)
      case _ => z
    }
    def (x: Maybe[A]) traverse [G[_], A, B] (f: A => G[B]) given (AG: Applicative[G]) =
      x.foldR(AG.pure(Empty))((x, z) => f(x) map pure)
  }
}