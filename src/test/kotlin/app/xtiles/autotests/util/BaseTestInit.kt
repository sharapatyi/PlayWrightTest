package app.xtiles.autotests.util

import com.microsoft.playwright.Browser
import com.microsoft.playwright.Browser.NewContextOptions
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.Tracing
import com.microsoft.playwright.options.Cookie
import com.microsoft.playwright.options.ViewportSize

object BaseTestInit {

    const val API_HOST = "https://stage.xtiles.app/"

    private lateinit var playwright: Playwright
    private lateinit var browser: Browser
    private lateinit var page: Page

    fun init(): Page {
        //Ініціалізуємо Playwright
        playwright = Playwright.create()

        //Налаштовуємо браузер
        browser = playwright.chromium().launch(
            BrowserType.LaunchOptions()
                .setArgs(
                listOf(
                    "--disable-extensions",
                    "--disable-infobars",
                    "--test-type",
                    "--no-sandbox",
                    "--disable-browser-side-navigation",
                    "--disable-gpu",
                    "--dns-prefetch-disable",
                    "--disable-infobars"
                )
            )
                .setHeadless(false)
        )

        //Встановлюємо роздільну здатність екрана
        val contextOptions = NewContextOptions()
            .setViewportSize(ViewportSize(1920, 1080))
            .setUserAgent("Chrome AutoTests")
            .setBypassCSP(true)

        val context = browser.newContext(contextOptions)

        page = context.newPage()

        //Вичищаємо кукі та локальний стор
        page.context().clearCookies()
//        page.evaluate("() => localStorage.clear()")

        //Додаємо кукі для увімкнення sourcemap
        addCookie("enablesourcemaps", "true")

        //включаємо трейсінг та скріншоти
        context.tracing().start(
            Tracing
                .StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(false)
        )

        return context.newPage()
    }

    private fun addCookie(name: String, value: String) {
        page.context().addCookies(
            listOf(
                Cookie(name, value).apply {
                    url = "https://stage.xtiles.app/"
                }
            )
        )
    }

    fun close() {
        //Закриття сторінки та браузера
        page.close()
        browser.close()

        //Закриття playwright
        playwright.close()
    }
}