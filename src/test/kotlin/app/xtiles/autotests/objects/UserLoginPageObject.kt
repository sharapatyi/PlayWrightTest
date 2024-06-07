package app.xtiles.autotests.objects

import app.xtiles.autotests.util.BrowserActions
import app.xtiles.autotests.util.BrowserActions.waitForHiddenElement
import com.microsoft.playwright.Page

object UserLoginPageObject {

    const val loginButton = "button[data-qa='page-login-continue']"
    const val inputEmail = "input[placeholder='Email']"
    const val inputPassword = "input[placeholder='Password']"

    fun login(page: Page, email: String, password: String) {
        //GIVEN
        BrowserActions.waitForVisibleElement(element = loginButton)
        BrowserActions.waitForVisibleElement(element = inputEmail)
        BrowserActions.waitForVisibleElement(element = inputPassword)

        //WHEN
        BrowserActions.fillInput(inputEmail, email)
        BrowserActions.fillInput(inputPassword, password)
        BrowserActions.clickOnButton(loginButton)

        //THEN
        waitForHiddenElement(inputEmail)
        waitForHiddenElement(inputPassword)
        waitForHiddenElement(loginButton)
    }
}