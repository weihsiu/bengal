package bengal.delegates

import bengal.Monoid

delegate for Monoid[String] {
  def empty = ""
  def (x: String) combine (y: String) = x ++ y
}