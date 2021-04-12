package bengal.data

import bengal.*

case class OptionT[F[_], A](value: F[Option[A]])

given optionTFunctor[F[_]: Functor]: Functor[[X] =>> OptionT[F, X]] with
  extension [A](x: OptionT[F, A])
    def map[B](f: A => B) = OptionT(x.value.map(_.map(f)))

given optionTApplicative[F[_]: Applicative]: Applicative[[X] =>> OptionT[F, X]] with
  export optionTFunctor.*
  def pure[A](x: A) = OptionT(summon[Applicative[F]].pure(Some(x)))
  extension [A, B](f: OptionT[F, A => B])
    def ap(x: OptionT[F, A]) = (f, x) match
      case (Some(f), Some(x)) => Some(f(x))
      case _ => None

given optionMonad: Monad[Option] with
  export optionApplicative.*
  extension [A](x: Option[A])
    def flatMap[B](f: A => Option[B]) = x.flatMap(f)
