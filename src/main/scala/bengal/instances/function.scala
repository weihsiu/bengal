package bengal.instances

import bengal._

trait function {
  delegate [I] for Monad[[X] =>> I => X] {
    def pure[A](x: A) = _ => x
    def (f: I => A => B) ap [A, B] (x: I => A) = y => f(y)(x(y))
    def (x: I => A) map [A, B] (f: A => B) = x andThen f
    def (x: I => A) flatMap [A, B] (f: A => I => B) = y => f(x(y))(y)
  }
}