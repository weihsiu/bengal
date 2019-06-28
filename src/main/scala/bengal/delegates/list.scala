package bengal.delegates

import bengal._

delegate [A] for Monoid[List[A]] {
  def empty = Nil
  def (x: List[A]) combine (y: List[A]) = x ++ y
}

delegate for Functor[List] {
  def (x: List[A]) map [A, B] (f: A => B) = x.map(f)
}

delegate for Applicative[List] given (F: Functor[List]) {
  export F._
  def pure[A](x: A) = List(x)
  def (f: List[A => B]) ap [A, B] (x: List[A]) = (f, x) match {
    case (Nil, _) | (_, Nil) => Nil
    case (f :: _, xs) => xs.map(f)
  }
}

delegate for Monad[List] given (A: Applicative[List]) {
  export A._
  def (x: List[A]) flatMap [A, B] (f: A => List[B]) = x.flatMap(f)
}

delegate for Foldable[List] {
  def (x: List[A]) foldL [A, B] (z: B)(f: (B, A) => B) = x.foldLeft(z)(f)
  def (x: List[A]) foldR [A, B] (z: B)(f: (A, B) => B) = x.foldRight(z)(f)
}

delegate for Traverse[List] given (FO: Foldable[List], FU: Functor[List]) {
  export FO._, FU._
  def (x: List[A]) traverse [G[_], A, B] (f: A => G[B]) given (AP: Applicative[G]) =
    x.foldRight(AP.pure(Nil))((x, z) => AP.map2(f(x), z)(_ :: _))
}