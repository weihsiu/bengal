package bengal

trait Ord[A]:
  def compare(x: A, y: A): Int
  extension (x: A)
    def <(y: A) = compare(x, y) < 0
    def >(y: A) = compare(x, y) > 0