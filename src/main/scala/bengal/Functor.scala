package bengal

trait Functor[F[_]] {
  def (x: F[A]) map [A, B] (f: A => B): F[B]
}