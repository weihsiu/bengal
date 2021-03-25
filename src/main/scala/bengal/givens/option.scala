package bengal.givens

import bengal.*

given optionMonoid[A: Monoid]: Monoid[Option[A]] with
  val empty = None
  extension (x: Option[A])
    def combine (y: Option[A]) = (x, y) match
      case (Some(x), Some(y)) => Some(x |+| y)
      case _ => None

given optionFunctor: Functor[Option] with
  extension [A](x: Option[A])
    def map[B](f: A => B) = x.map(f)

given optionApplicative: Applicative[Option] with
  export optionFunctor.*
  def pure[A](x: A) = Some(x)
  extension [A, B](f: Option[A => B])
    def ap(x: Option[A]) = (f, x) match
      case (Some(f), Some(x)) => Some(f(x))
      case _ => None

given optionMonad: Monad[Option] with
  export optionApplicative.*
  extension [A](x: Option[A])
    def flatMap[B](f: A => Option[B]) = x.flatMap(f)

given optionFoldable: Foldable[Option] with
  extension [A](x: Option[A])
    def foldL[B](z: B)(f: (B, A) => B) = x.foldLeft(z)(f)
    def foldR[B](z: B)(f: (A, B) => B) = x.foldRight(z)(f)

given optionTraverse: Traverse[Option] with
  export optionFunctor.*
  export optionFoldable.*
  extension [A](x: Option[A])
    def traverse[G[_], B](f: A => G[B])(using AP: Applicative[G]) =
      x.fold(AP.pure(None))(f(_).map(Some(_)))