package bengal.givens

import bengal.Monoid

given Monoid[String] with
  val empty = ""
  extension (x: String)
    def combine(y: String) = x ++ y