package doctor.domain

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation

object TimelineData {
  def apply(g: String): TimelineData = TimelineData(g, 0D, 0D, 0D, 0D, 0)
}

case class TimelineData(g: String, a: Double, b: Double, c: Double, d: Double, s: Int = 1) {
  def +(t: TimelineData) = {
    TimelineData(g, this.a + t.a, this.b + t.b, this.c + t.c, this.d + t.d, s + 1)
  }

  def sd = new StandardDeviation().evaluate(Array(a, b, c, d))

  def median = (a + b + c + d) / s
}
