package bengal.instances

import bengal._

trait string {
  delegate for Monoid[String] {
    def empty = ""
    def (x: String) combine (y: String) = x ++ y
  }
}