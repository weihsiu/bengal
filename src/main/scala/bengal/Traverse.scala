package bengal

trait Traverse[F[_]] extends Functor[F] with Foldable[F] {
  def (x: F[A]) traverse [G[_], A, B] (f: A => G[B]) given (AG: Applicative[G]): G[F[B]]
  def (x: F[G[A]]) sequence [G[_], A] given Applicative[G]: G[F[A]] = x traverse identity
}