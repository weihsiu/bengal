package bengal

trait Applicative[F[_]] extends Functor[F] {
  def pure[A](x: A): F[A]
  def (f: F[A => B]) ap [A, B] (x: F[A]): F[B]
  def (f: F[A => B]) <*> [A, B] (x: F[A]): F[B] = f ap x
  def ap2[A, B, C](x: F[A], y: F[B])(f: F[(A, B) => C]): F[C] =
    (f map (f2 => (a: A) => (b: B) => f2(a, b))) <*> x <*> y
  def map2[A, B, C](x: F[A], y: F[B])(f: (A, B) => C): F[C] =
    (x map (a => (b: B) => f(a, b))) <*> y
}