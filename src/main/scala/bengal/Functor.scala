package bengal

import scala.compiletime.{erasedValue, summonAll}
import scala.deriving.*

trait Functor[F[_]]:
  extension [A](x: F[A])
    def map[B](f: A => B): F[B]

object Functor extends TCApply[Functor]:
  def iterator[T](p: T) = p.asInstanceOf[Product].productIterator

  def functorSum[T[_]](s: Mirror.SumOf[T[_]], elems: => List[Functor[_]]): Functor[T] =
    ???

  def functorProduct[T[_]](p: Mirror.ProductOf[T[_]], elems: => List[Functor[_]]): Functor[T] =
    ???

  inline given derived[T[_]](using m: Mirror.Of[T[_]]): Functor[T] =
    lazy val elemInstances = summonAll[m.MirroredElemTypes].toList.map(_.asInstanceOf[Functor[_]])
    inline m match
      case s: Mirror.SumOf[T[_]] => functorSum(s, elemInstances)
      case p: Mirror.ProductOf[T[_]] => functorProduct(p, elemInstances)
