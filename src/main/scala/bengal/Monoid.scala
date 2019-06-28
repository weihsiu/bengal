package bengal

trait Monoid[A] {
  def empty: A
  def (x: A) combine (y: A): A
  def (x: A) |+| (y: A): A = x combine y
}

object Monoid {
  import shapeless._
  def monoidGen[A](pi: K0.ProductInstances[Monoid, A]): Monoid[A] = new Monoid[A] {
    def empty = pi.construct([t] => (m: Monoid[t]) => m.empty)
    def (x: A) combine (y: A) = pi.map2(x, y)([t] => (m: Monoid[t], t0: t, t1: t) => m.combine(t0)(t1))
  }
  inline def derived[A] given (PG: K0.ProductGeneric[A]): Monoid[A] =
    monoidGen(K0.mkProductInstances[Monoid, A](PG))
}