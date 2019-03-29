package bengal.instances

import bengal._

type Id[A] = A

trait FoldableMonad[F[_]] extends Monad[F] with Foldable[F]
trait TraverseMonad[F[_]] extends FoldableMonad[F] with Traverse[F]

object all
  extends function
  with int
  with list
  with maybe
  with numeric
  with option
  with string
  with tuple
