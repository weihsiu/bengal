package bengal.givens

import bengal.*

given listOrd[A: Ord]: Ord[List[A]] with
  def compare(xs: List[A], ys: List[A]) =
    xs.zip(ys).map(Ord[A].compare).dropWhile(_ == 0).headOption.getOrElse(0)
    
given listMonoid[A]: Monoid[List[A]] with
  val empty = Nil
  extension (x: List[A])
    def combine (y: List[A]) = x ++ y

given listFunctor: Functor[List] with
  extension [A](x: List[A])
    def map[B](f: A => B) = x.map(f)

given listApplicative: Applicative[List] with
  export listFunctor.*
  def pure[A](x: A) = List(x)
  extension [A, B](f: List[A => B])
    def ap(x: List[A]) = (f, x) match
      case (Nil, _) | (_, Nil) => Nil
      case (f :: _, xs) => xs.map(f)
  
given listMonad: Monad[List] with
  export listApplicative.*
  extension [A](x: List[A])
    def flatMap[B](f: A => List[B]) = x.flatMap(f)

given listFoldable: Foldable[List] with
  extension [A](x: List[A])
    def foldL[B](z: B)(f: (B, A) => B) = x.foldLeft(z)(f)
    def foldR[B](z: B)(f: (A, B) => B) = x.foldRight(z)(f)

given listTraverse: Traverse[List] with
  export listFunctor.*
  export listFoldable.*
  extension [A](x: List[A])
    def traverse[G[_], B](f: A => G[B])(using AP: Applicative[G]) =
      x.foldRight(AP.pure(Nil))((x, z) => AP.map2(f(x), z)(_ :: _))