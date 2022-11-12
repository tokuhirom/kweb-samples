import io.github.bonigarcia.seljup.Arguments
import io.github.bonigarcia.seljup.SeleniumJupiter
import io.kotest.matchers.shouldBe
import kweb.*
import kweb.template.RoutingApp
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URI
import java.time.Duration

@ExtendWith(SeleniumJupiter::class)
class InputCheckedTest(@Arguments("--headless") val driver: ChromeDriver) {
    private val port = 7660

    companion object {
        private lateinit var routingApp: RoutingApp

        @JvmStatic
        @BeforeAll
        fun setupServer() {
            routingApp = RoutingApp(port = 7660)
        }

        @JvmStatic
        @AfterAll
        fun tearDownServer() {
            routingApp.server.close()
        }
    }

    @Test
    fun checkBeforeAndAfterClick() {
        val wait = WebDriverWait(driver, Duration.ofSeconds(5))

        driver.get("http://localhost:$port/")

        wait.until { routingApp.pageCounter.get() == 1 }
        routingApp.pageCounter.get() shouldBe 1

        val createLink = driver.findElement(By.className("createLink"))
        createLink.click()

        wait.until { URI(driver.currentUrl).path == "/create" }
        URI(driver.currentUrl).path shouldBe "/create"

        val input = driver.findElement(By.tagName("input"))
        input.sendKeys("hello")
        input.submit()

        wait.until { routingApp.pageCounter.get() == 2 }
        routingApp.pageCounter.get() shouldBe 2
    }
}
