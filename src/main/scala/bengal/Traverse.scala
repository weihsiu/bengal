package bengal

trait Traverse[F[_]] extends Functor[F] with Foldable[F]:
  extension [A](x: F[A])
    def traverse [G[_], B] (f: A => G[B])(using AP: Applicative[G]): G[F[B]]
  extension[G[_], A](x: F[G[A]])
    def sequence(using Applicative[G]): G[F[A]] = x traverse identity

object Traverse extends TCApply[Traverse]