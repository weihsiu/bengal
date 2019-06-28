package bengal.delegates

import bengal._

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

delegate for Applicative[Option] given (F: Functor[Option]) {
  export F._
  def pure[A](x: A) = Some(x)
  def (f: Option[A => B]) ap [A, B] (x: Option[A]) = (f, x) match {
    case (Some(f), Some(x)) => Some(f(x))
    case _ => None
  }
}

delegate for Monad[Option] given (A: Applicative[Option]) {
  export A._
  def (x: Option[A]) flatMap [A, B] (f: A => Option[B]) = x.flatMap(f)
}

delegate for Foldable[Option] {
  def (x: Option[A]) foldL [A, B] (z: B)(f: (B, A) => B) = x.foldLeft(z)(f)
  def (x: Option[A]) foldR [A, B] (z: B)(f: (A, B) => B) = x.foldRight(z)(f)
}

delegate for Traverse[Option] given (FO: Foldable[Option], FU: Functor[Option]) {
  export FO._, FU._
  def (x: Option[A]) traverse [G[_], A, B] (f: A => G[B]) given (AP: Applicative[G]) =
    x.fold(AP.pure(None))(f(_) map (Some(_)))
}