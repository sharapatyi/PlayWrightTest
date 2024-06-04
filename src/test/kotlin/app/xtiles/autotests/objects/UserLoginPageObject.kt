package app.xtiles.autotests.objects

import app.xtiles.autotests.util.ActionsHelper
import com.microsoft.playwright.Page

object UserLoginPageObject {

    const val loginButton = "button[data-qa='page-login-continue']"
    const val inputEmail = "input[placeholder='Email']"
    const val inputPassword = "input[placeholder='Password']"

    fun login(page: Page, email: String, password: String) {
        ActionsHelper.waitForElement(page = page, element = loginButton)
        ActionsHelper.waitForElement(page = page, element = inputEmail)
        ActionsHelper.waitForElement(page = page, element = inputPassword)
    }
}