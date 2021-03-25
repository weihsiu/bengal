package bengal.givens

import bengal.*

given function1Monad[I]: Monad[[X] =>> I => X] with
  def pure[A](x: A) = _ => x
  extension [A, B](f: I => A => B)
    def ap(x: I => A) = y => f(y)(x(y))
  extension [A](x: I => A)
    def map[B](f: A => B) = x.andThen(f)
    def flatMap[B](f: A => I => B) = y => f(x(y))(y)