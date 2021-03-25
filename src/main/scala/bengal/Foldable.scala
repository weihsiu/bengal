package bengal

trait Foldable[F[_]]:
  extension [A](x: F[A])
    def foldL[B](z: B)(f: (B, A) => B): B
    def foldR[B](z: B)(f: (A, B) => B): B
    def foldMap[B](f: A => B)(using MB: Monoid[B]): B = x.foldL(MB.empty)((a, x) => a combine f(x))
    def fold(using Monoid[A]): A = x.foldMap(identity)

object Foldable extends TCApply[Foldable]