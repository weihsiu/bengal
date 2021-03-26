val pair: [X, Y] => X => Y => (X, Y) = [X, Y] => (x: X) => (y: Y) => (x, y)

val intInt = pair[Int, Int]
intInt(1)(2)

val booleanString = pair[Boolean, String]
booleanString(false)("hello")

