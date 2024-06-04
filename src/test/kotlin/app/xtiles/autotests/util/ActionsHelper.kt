package app.xtiles.autotests.util

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.ElementState
import com.microsoft.playwright.options.ElementState.*

object ActionsHelper {

    fun waitForElement(page: Page, element: String, state: ElementState = VISIBLE): Boolean {
        return when (state) {
            VISIBLE -> page.locator(element).isVisible
            HIDDEN -> page.locator(element).isHidden
            STABLE -> false
            ENABLED -> page.locator(element).isEnabled
            DISABLED -> page.locator(element).isDisabled
            EDITABLE -> page.locator(element).isEditable
        }
    }
}