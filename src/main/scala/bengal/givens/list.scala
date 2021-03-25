package bengal.givens

import bengal.*

given [A]: Monoid[List[A]] with
  val empty = Nil
  extension (x: List[A])
    def combine (y: List[A]) = x ++ y

given Functor[List] with
  extension [A](x: List[A])
    def map[B](f: A => B) = x.map(f)

given (using F: Functor[List]): Applicative[List] with
  export F._
  def pure[A](x: A) = List(x)
  extension [A, B](f: List[A => B])
    def ap(x: List[A]) = (f, x) match
      case (Nil, _) | (_, Nil) => Nil
      case (f :: _, xs) => xs.map(f)
  
given (using A: Applicative[List]): Monad[List] with
  export A._
  extension [A](x: List[A])
    def flatMap[B](f: A => List[B]) = x.flatMap(f)

given Foldable[List] with
  extension [A](x: List[A])
    def foldL[B](z: B)(f: (B, A) => B) = x.foldLeft(z)(f)
    def foldR[B](z: B)(f: (A, B) => B) = x.foldRight(z)(f)

given (using FO: Foldable[List], FU: Functor[List]): Traverse[List] with
  export FO._, FU._
  extension [A](x: List[A])
    def traverse[G[_], B](f: A => G[B])(using AP: Applicative[G]) =
      x.foldRight(AP.pure(Nil))((x, z) => AP.map2(f(x), z)(_ :: _))