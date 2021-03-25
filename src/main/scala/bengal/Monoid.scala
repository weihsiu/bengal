package bengal

trait Monoid[A]:
  def empty: A
  extension (x: A)
    def combine(y: A): A
    def |+|(y: A): A = x.combine(y)

object Monoid extends CApply[Monoid]