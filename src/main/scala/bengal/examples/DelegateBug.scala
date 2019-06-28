package bengal.examples

object DelegateBug {
  trait Semigroup[A] {
    def (x: A) combine (y: A): A
  }
  delegate for Semigroup[Int] = ???
  delegate [A, B] for Semigroup[(A, B)] given Semigroup[A], Semigroup[B] = ???
  // ((1, 1)) combine (2, 2) // doesn't compile
  ((1, 1): (Int, Int)) combine (2, 2) // compiles
}