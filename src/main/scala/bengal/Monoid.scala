package bengal

trait Monoid[A] {
  def empty: A
  def (x: A) combine (y: A): A
  def (x: A) |+| (y: A): A = x combine y
}