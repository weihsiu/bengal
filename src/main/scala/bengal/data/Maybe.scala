package bengal.data

import scala.Eql

enum Maybe[+A] derives Eql {
  case Just(x: A)
  case Empty
}