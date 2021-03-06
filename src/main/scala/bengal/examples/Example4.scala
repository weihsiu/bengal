package bengal.examples

import bengal._
import bengal.delegates.WrappedInts._ // TODO no need to "import delegate WrappedInts._"?
import delegate bengal.delegates._

object Example4 {
  def test1[F[_]] given Monad[F]: F[Unit] = for {
    a <- the[Monad[F]].pure(1)
    b <- the[Monad[F]].pure("hello")
  } yield println(s"a = $a, b = $b")
  def test2[F[_]] given (M: Monad[F], T: Traverse[F]): F[Unit] = {
    delegate for Monad[F] = M // delegate for Monad[F] = the[Monad[F]] // stack overflow
    for {
      a <- M.pure(1)
      b <- M.pure(M.pure("hello")).sequence
    } yield println(s"a = $a, b = $b")
  }
  def main(args: Array[String]): Unit = {
    println((Some(1) map (_ + 1)))
    println(1.0 |+| 2)
    println("hello " |+| "world")
    println(SumInt(1) |+| SumInt(2))
    println(ProdInt(1) |+| ProdInt(2))
    println(((1, "hello"): (Int, String)) |+| (2, "world")) // https://github.com/lampepfl/dotty/issues/6706
    println(Some(1).map(_ + 1))
    println(Some((_: Int) + 1) ap Some(1))
    println(Some((_: Int) + 1) <*> Some(2))
    println(Some((_: Int) + 1) ap None)
    println(Some(1) foldMap (_ + 1))
    println(List(1, 2, 3).foldR(0)(_ + _))
    test1[Option]
    test2[List]
  }
}