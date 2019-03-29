package bengal

trait Ord[A] {
  def compare(x: A, y: A): Int
  def (x: A) < (y: A) = compare(x, y) < 0
  def (x: A) > (y: A) = compare(x, y) > 0
}