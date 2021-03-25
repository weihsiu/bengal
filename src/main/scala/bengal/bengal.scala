package bengal

trait CApply[C[_]]:
  def apply[A: C]: C[A] = summon[C[A]]
  
trait TCApply[TC[_[_]]]:
  def apply[F[_]: TC]: TC[F] = summon[TC[F]]