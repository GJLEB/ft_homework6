package com.moonpi.swiftnotes.Test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.DisplayName


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.view.View
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.step
import java.io.File
import android.content.Context.ACTIVITY_SERVICE
import android.app.ActivityManager
import android.content.Context
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.KeyEvent


@RunWith(AndroidJUnit4::class)
@DisplayName("First Steps Espresso")
internal class SampleTest {


    @Before
    fun del(){
        File("sdcard/allure-results").deleteRecursively()
        Runtime.getRuntime().exec("pm clear me.com.moonpi.swiftnotes");
        }

    @get:Rule
     var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @DisplayName("Case 1")
    fun FirsTest() {
        step("Запуск приложения") {
            deviceScreenshot("Запущено")
        }
        step("Проверка элементов") {
            onView(withText("Swiftnotes")).check(matches(isDisplayed()));
            onView(withText("Press '+' to add new note")).check(matches(isDisplayed()));
            onView(withId(R.id.newNote)).check(matches(isDisplayed()));
            deviceScreenshot("Элементы")
        }
        step("Проверка после нажатия на кнопку +"){
            onView(withId(R.id.newNote)).perform(click())
            onView(allOf(withId(R.id.titleEdit), isDisplayed())).check(matches(withHint("Title")));
            onView(allOf(withId(R.id.bodyEdit), isDisplayed())).check(matches(withHint("Note")));
            deviceScreenshot("Элементы")
        }

        step("Нажатие кнопки назад"){
            onView(isRoot()).perform(ViewActions.pressBack());
            onView(isRoot()).perform(ViewActions.pressBack());
            onView(withText("Save changes?")).check(matches(isDisplayed()));
            onView(withText("Yes")).check(matches(isDisplayed()));
            onView(withText("No")).check(matches(isDisplayed()));
            deviceScreenshot("Диалогове окно")
        }
        step("Возврат на главный экран"){
            onView(withText("No")).perform(click())
            deviceScreenshot("Гланвый экран")
        }


    }

    @Test
    @DisplayName("Second Test")
    fun SecondTest(){
        step("Проверка после нажатия на кнопку +"){
            onView(withId(R.id.newNote)).perform(click())
            deviceScreenshot("Экран создания заметки")
        }
        step("Ввод текста") {
            onView(withId(R.id.scrollView)).perform(scrollTo(), click());
            onView(withId(R.id.scrollView)).perform(typeText("Note "));
            onView(withId(R.id.scrollView)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_1));

        }

    }

}
