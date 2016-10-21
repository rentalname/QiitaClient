package sample.qiitaclient

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import rx.Observable
import sample.qiitaclient.client.ArticleClient


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun `各ビューが表示されていること_ただしプログレスバーは非表示`() {
        onView(withId(R.id.list_view)).check(matches(isDisplayed()))
        onView(withId(R.id.query_edit_text)).check(matches(isDisplayed()))
        onView(withId(sample.qiitaclient.R.id.kensaku_button)).check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar)).check(matches((isNotDisplayed())))
    }

    @Test
    fun `検索ボタンがタップされたら_入力されたクエリ文字列で記事検索APIを叩くこと`() {
        val articleClient = mock(ArticleClient::class.java).apply {
            `when`(search("user:ngsq_taro")).thenReturn(Observable.just(listOf()))
        }
        activityTestRule.activity.articleClient = articleClient

        onView(withId(R.id.query_edit_text)).perform(typeText("user:ngsw_taro")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.kensaku_button)).perform(click())
        verify(articleClient).search("user:ngsw_taro")
    }

    private fun isNotDisplayed(): Matcher<in View> = Matchers.not(isDisplayed())
}