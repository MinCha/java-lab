package doctor.interfaces

trait BaseController {
  def ensure[T](alias: Any)(any: T): T = {
    require(any != null, s"""'$alias' does not exist.""")
    any
  }
}
