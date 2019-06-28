package bengal.data

import bengal._
import scala.Eql

enum Maybe[+A] derives Eql, Functor  {
  case Just(x: A)
  case Empty
}