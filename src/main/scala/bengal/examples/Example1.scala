package bengal.examples

import bengal.*
import bengal.givens.WrappedInt.*
import bengal.givens.{*, given}

object Example1:
  def test1[F[_]](using Monad[F]): F[Unit] =
    for
      a <- summon[Monad[F]].pure(1)
      b <- summon[Monad[F]].pure("hello")
    yield println(s"a = $a, b = $b")

  def test2[F[_]](using M: Monad[F], T: Traverse[F]): F[Unit] =
    given Functor[F] = M
    for
      a <- M.pure(1)
      b <- M.pure(M.pure("hello")).sequence
    yield println(s"a = $a, b = $b")

  @main
  def example1Main() =
    println((Some(1) map (_ + 1)))
    println(1.0 |+| 2)
    println("hello " |+| "world")
    println(SumInt(1) |+| SumInt(2))
    println(ProdInt(1) |+| ProdInt(2))
    println((1, "hello") |+| (2, "world"))
    println(Some(1).map(_ + 1))
    println(Some((_: Int) + 1) ap Some(1))
    println(Some((_: Int) + 1) <*> Some(2))
    println(Some((_: Int) + 1) ap None)
    println(Some(1) foldMap (_ + 1))
    println(List(1, 2, 3).foldR(0)(_ + _))
    test1[Option]
    test2[List]