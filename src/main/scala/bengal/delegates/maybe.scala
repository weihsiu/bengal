package bengal.delegates

import bengal._
import bengal.data.Maybe
import bengal.data.Maybe._

delegate [A] for Monoid[Maybe[A]] given Monoid[A] {
  def empty = Empty
  def (x: Maybe[A]) combine (y: Maybe[A]) = (x, y) match {
    case (Just(x), Just(y)) => Just(x |+| y)
    case _ => Empty
  }
}

// delegate for Functor[Maybe] {
//   def (x: Maybe[A]) map [A, B] (f: A => B) = x match {
//     case Just(x) => Just(f(x))
//     case _ => Empty
//   }
// }

delegate for Applicative[Maybe] given (F: Functor[Maybe]) {
  export F._
  def pure[A](x: A) = Just(x)
  def (f: Maybe[A => B]) ap [A, B] (x: Maybe[A]) = (f, x) match {
    case (Just(f), Just(x)) => Just(f(x))
    case _ => Empty
  }
}

delegate for Monad[Maybe] given (A: Applicative[Maybe]) {
  export A._
  def (x: Maybe[A]) flatMap [A, B] (f: A => Maybe[B]) = x match {
    case Just(x) => f(x)
    case _ => Empty
  }
}

delegate for Foldable[Maybe] {
  def (x: Maybe[A]) foldL [A, B] (z: B)(f: (B, A) => B) = x match {
    case Just(x) => f(z, x)
    case _ => z
  }
  def (x: Maybe[A]) foldR [A, B] (z: B)(f: (A, B) => B) = x match {
    case Just(x) => f(x, z)
    case _ => z
  }
}

delegate for Traverse[Maybe] given (FO: Foldable[Maybe], FU: Functor[Maybe]) {
  export FO._, FU._
  def (x: Maybe[A]) traverse [G[_], A, B] (f: A => G[B]) given (AP: Applicative[G]) =
    x.foldR(AP.pure(Empty))((x, z) => f(x) map (Just(_)))
}
