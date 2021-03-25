package bengal.givens

import bengal.*
import bengal.data.Maybe
import bengal.data.Maybe.*

given [A](using Monoid[A]): Monoid[Maybe[A]] with
  val empty = Empty
  extension (x: Maybe[A])
    def combine (y: Maybe[A]) = (x, y) match
      case (Just(x), Just(y)) => Just(x |+| y)
      case _ => Empty

given Functor[Maybe] with
  extension [A](x: Maybe[A])
    def map[B] (f: A => B) = x match 
      case Just(x) => Just(f(x))
      case _ => Empty
  

given (using F: Functor[Maybe]): Applicative[Maybe] with
  export F._
  def pure[A](x: A) = Just(x)
  extension [A, B](f: Maybe[A => B])
    def ap(x: Maybe[A]) = (f, x) match
      case (Just(f), Just(x)) => Just(f(x))
      case _ => Empty

given (using A: Applicative[Maybe]): Monad[Maybe] with
  export A._
  extension [A](x: Maybe[A])
    def flatMap[B](f: A => Maybe[B]) = x match
      case Just(x) => f(x)
      case _ => Empty

given Foldable[Maybe] with
  extension [A](x: Maybe[A])
    def foldL[B](z: B)(f: (B, A) => B) = x match
      case Just(x) => f(z, x)
      case _ => z
    def foldR[B](z: B)(f: (A, B) => B) = x match
      case Just(x) => f(x, z)
      case _ => z

given (using FO: Foldable[Maybe], FU: Functor[Maybe]): Traverse[Maybe] with
  export FO._, FU._
  extension [A](x: Maybe[A])
    def traverse[G[_], B](f: A => G[B])(using AP: Applicative[G]) =
      x.foldR(AP.pure(Empty))((x, z) => f(x).map(Just(_)))
