package aha.oretama.jp.template

import aha.oretama.jp.ImageDiffWebApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(classes = ImageDiffWebApplication)
class ZipTemplateTest extends Specification {


  def "getZip"() {
    ZipTemplate template = new ZipTemplate()

    expect:
    def content = template.getZip(URI.create("https://ci.misosiru.io/teamcity/repository/downloadAll/BKT_Web_BrowserTest_Dev5spFirefox/67723:id/artifacts.zip"),
            "yasufumi_sekine@r.recruit.co.jp",
            "Sekine0529!")
    template.unzip("build",content)

  }
}
