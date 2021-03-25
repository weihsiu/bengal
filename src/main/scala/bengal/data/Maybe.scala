package bengal.data

import bengal.*

enum Maybe[+A]  {
  case Just(x: A)
  case Empty
}