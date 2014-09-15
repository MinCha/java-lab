package model

import org.apache.commons.lang3.builder.{HashCodeBuilder, EqualsBuilder, ToStringStyle, ToStringBuilder}

/**
 * Created by Vayne on 2014. 8. 5..
 */
class BaseModel extends AnyRef {
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
