package contents

import org.jsoup.Jsoup
import scala.collection.JavaConversions._
import org.junit.Test
import org.scalatest.junit.JUnitSuite

class ContentsExtractorTest extends JUnitSuite {
  @Test def canExtractContents() {
    case class TextAndLink(text: String, link: String)
    val page = Jsoup.connect("http://m.inven.co.kr/lol/").get
    val candidate = (for (e <- page.select("a[href]")) yield {
      val link = e.attr("href")
      val text = e.text

      if (mayBeContent(link, text)) {
        TextAndLink(link, text)
      }
    })
    println(candidate)
  }

  def mayBeContent(link: String, text: String) = {
    if (link.matches( """^[^0-9]+$""")) false
    else if (link.contains("javascript")) false
    else if (text.isEmpty) false
    else true
  }
}
