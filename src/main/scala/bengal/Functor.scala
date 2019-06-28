package bengal

trait Functor[F[_]] {
  def (x: F[A]) map [A, B] (f: A => B): F[B]
}

object Functor {
  inline def derived[F[_]] given (_F: _Functor[F]): Functor[F] = new Functor[F] {
    def (x: F[A]) map [A, B] (f: A => B) = _F.map(x)(f)
  }
} 

// https://github.com/milessabin/shapeless/issues/906
trait _Functor[F[_]] {
  def map[A, B](x: F[A])(f: A => B): F[B]
}

object _Functor {
  import shapeless._
  delegate for _Functor[Id] = new _Functor[Id] {
    def map[A, B](x: Id[A])(f: A => B) = f(x)
  }
  implicit def functorGen[F[_]](implicit inst: K1.Instances[_Functor, F]): _Functor[F] = new _Functor[F] {
    def map[A, B](x: F[A])(f: A => B) = 
      inst.map(x)([t[_]] => (ft: _Functor[t], ta: t[A]) => ft.map(ta)(f))
  }
  inline def derived[F[_]] given (G: K1.Generic[F]): _Functor[F] =
    functorGen(K1.mkInstances[_Functor, F](G))
  
}