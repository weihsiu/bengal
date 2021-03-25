// https://adelbertc.github.io/publications/typeclasses-scala17.pdf

trait Functor[F[_]]
trait Monad[F[_]] extends Functor[F]
trait Traverse[F[_]] extends Functor[F]

def needFunctor[F[_]](using Functor[F]): Unit = ???

def tAndM[F[_]](using Monad[F], Traverse[F]): Unit =
  given Functor[F] = summon[Monad[F]]
  needFunctor[F]
