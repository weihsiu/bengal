package bengal

trait Functor[F[_]]:
  extension [A](x: F[A])
    def map[B](f: A => B): F[B]

object Functor extends TCApply[Functor]
