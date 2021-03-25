package bengal

trait Monad[F[_]] extends Applicative[F]:
  extension [A](x: F[A])
    def flatMap[B] (f: A => F[B]): F[B]
    def >>=[B](f: A => F[B]): F[B] = x.flatMap(f)
    def >>[B](y: F[B]): F[B] = x.flatMap(_ => y)

object Monad extends TCApply[Monad]