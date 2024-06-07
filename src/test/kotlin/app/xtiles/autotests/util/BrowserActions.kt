package app.xtiles.autotests.util

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.WaitForSelectorState
import java.time.Duration

object BrowserActions {

    private lateinit var page: Page

    fun initialize(page: Page) {
        this.page = page
    }

    fun clickOnButton(selector: String) {
        page.locator(selector).click()
    }

    fun fillInput(selector: String, value: String) {
        page.locator(selector).fill(value)

        this.waitForValue(page, selector, expectedValue = value, )
    }

    fun waitForValue(page: Page, selector: String, expectedValue: String, timeout: Duration? = Duration.ofMillis(10000)) {
        val endTime = System.currentTimeMillis() + timeout!!.toMillis()
        while (System.currentTimeMillis() < endTime) {
            val actualValue = page.inputValue(selector)
            if (actualValue == expectedValue) {
                return
            }
            Thread.sleep(100) // Чекаємо 100 мс перед наступною перевіркою
        }
        throw AssertionError("Очікуване значення '$expectedValue' не з'явилося в input-полі '$selector' протягом ${timeout.seconds} секунд.")
    }

    fun waitForVisibleElement(element: String, timeout: Int = 10000) {
        page.waitForSelector(
            element,
            Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(timeout.toDouble())
        )
    }

    fun waitForHiddenElement(selector: String, timeout: Int = 10000) {
        page.waitForSelector(
            selector,
            Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(timeout.toDouble())
        )
    }

    fun waitForEnabledElement(selector: String, timeout: Int = 10000) {
        page.waitForFunction(
            "element => !element.disabled",
            page.querySelector(selector),
            Page.WaitForFunctionOptions().setTimeout(timeout.toDouble())
        )
    }

    fun waitForDisabledElement(selector: String, timeout: Int = 10000) {
        page.waitForFunction(
            "element => element.disabled",
            page.querySelector(selector),
            Page.WaitForFunctionOptions().setTimeout(timeout.toDouble())
        )
    }

    fun pause(pause: Double) {
        page.waitForTimeout(pause)
    }
}