package bengal

trait Monad[F[_]] extends Applicative[F] {
  def (x: F[A]) flatMap [A, B] (f: A => F[B]): F[B]
  def (x: F[A]) >>= [A, B] (f: A => F[B]): F[B] = x flatMap f
  def (x: F[A]) >> [A, B] (y: F[B]): F[B] = x flatMap (_ => y)
}