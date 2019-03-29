package bengal

trait Foldable[F[_]] {
  def (x: F[A]) foldL [A, B] (z: B)(f: (B, A) => B): B
  def (x: F[A]) foldR [A, B] (z: B)(f: (A, B) => B): B
  def (x: F[A]) foldMap [A, B] (f: A => B) given (MB: Monoid[B]): B =
    x.foldL(MB.empty)((a, x) => a combine f(x))
  def (x: F[A]) fold [A] given Monoid[A]: A = x foldMap identity
}