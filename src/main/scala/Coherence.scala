// https://adelbertc.github.io/publications/typeclasses-scala17.pdf

trait Functor[F[_]] {}
trait Monad[F[_]] extends Functor[F] {}
trait Traverse[F[_]] extends Functor[F] {}

def needFunctor[F[_]] given Functor[F]: Unit = {}

def tAndM[F[_]] given Monad[F], Traverse[F]: Unit = {
  delegate for Functor[F] = the[Monad[F]]
  needFunctor[F]
}
