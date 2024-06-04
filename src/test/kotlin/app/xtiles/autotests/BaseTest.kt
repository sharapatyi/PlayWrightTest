package app.xtiles.autotests

import app.xtiles.autotests.constants.TestPageIds
import app.xtiles.autotests.objects.UserLoginPageObject
import app.xtiles.autotests.util.ActionsHelper
import app.xtiles.autotests.util.BaseTestInit
import com.microsoft.playwright.Page
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest(
    val email: String? = null,
    val password: String? = null,
    val createFirstTile: Boolean? = true,
    val deleteAllProjectsBeforeAll: Boolean? = true,
    val createNewProject: Boolean? = true,
    val deleteAllWorkspaces: Boolean? = false
) {

    private lateinit var page: Page

    @BeforeAll
    fun setup() {
        page = BaseTestInit.init()
        page.navigate(TestPageIds.LOGIN_PAGE.id)

        UserLoginPageObject.login(
            page = page,
            email = this.email ?: System.getProperty("login.native.email").toString(),
            password = this.password ?: System.getProperty("login.native.password").toString()
        )
    }
}