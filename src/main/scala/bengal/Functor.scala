package bengal

import scala.compiletime.{erasedValue, summonAll}
import scala.deriving.*

trait Functor[F[_]]:
  extension [A](x: F[A])
    def map[B](f: A => B): F[B]

object Functor extends TCApply[Functor]:
  def iterator[T](p: T) = p.asInstanceOf[Product].productIterator

  def functorSum[T[_], A](s: Mirror.SumOf[T[A]], elems: => List[Functor[_]]): Functor[T] =
    ???

  def functorProduct[T[_], A](p: Mirror.ProductOf[T[A]], elems: => List[Functor[_]]): Functor[T] =
    if elems.isEmpty then
      new Functor[T]:
        extension [A](x: T[A])
          def map[B](f: A => B): T[B] = x.asInstanceOf[T[B]]
    else
      new Functor[T]:
        extension [A](x: T[A])
          def map[B](f: A => B): T[B] = p.fromProduct(new Product:
            val productArity = 1
            def productElement(n: Int): Any =
              elems.head.map(x)(f)
          )

  inline given derived[T[_], A](using m: Mirror.Of[T[A]]): Functor[T] =
    lazy val elemInstances = summonAll[m.MirroredElemTypes].toList.map(_.asInstanceOf[Functor[_]])
    inline m match
      case s: Mirror.SumOf[T[_]] => functorSum(s, elemInstances)
      case p: Mirror.ProductOf[T[_]] => functorProduct(p, elemInstances)

@main
def functorDerivesTest() =
  case object CO derives Functor
  println(CO.map(identity))
