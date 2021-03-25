package bengal.givens

import bengal.Monoid

given stringMonoid: Monoid[String] with
  val empty = ""
  extension (x: String)
    def combine(y: String) = x ++ y