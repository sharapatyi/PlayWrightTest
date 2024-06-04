package app.xtiles.autotests.util

import com.microsoft.playwright.Page

object TestPageHelper {
    private const val API_HOST = "https://prerelease.xtiles.app/"

    private fun pageUrlPattern(pageId: String): String {
        return String.format("${API_HOST}%s", pageId)
    }

    fun openPage(pageId: String, page: Page) {
        page.navigate(pageUrlPattern(pageId))
    }

}