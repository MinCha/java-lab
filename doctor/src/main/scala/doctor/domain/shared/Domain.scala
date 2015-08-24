package doctor.domain.shared

import org.apache.commons.lang.builder.{EqualsBuilder, HashCodeBuilder, ToStringBuilder, ToStringStyle}

class Domain {
  override def toString: String = {
    ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE)
  }

  override def hashCode: Int = {
    HashCodeBuilder.reflectionHashCode(this)
  }

  override def equals(o: scala.Any): Boolean = {
    EqualsBuilder.reflectionEquals(this, o);
  }
}