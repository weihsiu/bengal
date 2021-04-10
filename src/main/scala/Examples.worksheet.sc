val pair: [X, Y] => X => Y => (X, Y) = [X, Y] => (x: X) => (y: Y) => (x, y)

val intInt = pair[Int, Int]
intInt(1)(2)

val booleanString = pair[Boolean, String]
booleanString(false)("hello")

import java.util.*
val gc = GregorianCalendar(1582, 9, 4)
gc.setTimeZone(TimeZone.getTimeZone("GMT+0:00"))
// gc.setGregorianChange(Date(1582, 9, 4))
gc.toInstant
gc.add(Calendar.DAY_OF_MONTH, 1)
gc.toInstant
