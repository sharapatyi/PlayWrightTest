package app.xtiles.autotests

import app.xtiles.autotests.constants.TestPageIds
import app.xtiles.autotests.objects.UserLoginPageObject
import app.xtiles.autotests.util.BaseTestInit
import app.xtiles.autotests.util.BrowserActions
import com.microsoft.playwright.Page
import io.qameta.allure.Allure
import org.junit.AfterClass
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.testng.ITestResult
import java.io.ByteArrayInputStream
import java.nio.file.Paths

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
        BrowserActions.initialize(page)

        UserLoginPageObject.login(
            page = page,
            email = this.email ?: System.getProperty("login.native.email").toString(),
            password = this.password ?: System.getProperty("login.native.password").toString()
        )
    }

    @AfterClass
    fun tearDown() {
        BaseTestInit.close()
    }

    @AfterEach
    fun attachFilesToFailedTest(result: ITestResult) {
        if (!result.isSuccess) {
            val screenshot = page.screenshot(
                Page
                    .ScreenshotOptions()
                    .setFullPage(true)
                    .setPath(Paths.get("build/allure-result/screenshot_${result.name}.png"))
            )
            Allure.addAttachment(result.name, ByteArrayInputStream(screenshot))
        }
    }
}