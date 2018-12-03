package com.moonpi.swiftnotes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.tinkoff.allure.annotations.DisplayName;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@DisplayName("First Steps Espresso")
class SampleTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    @DisplayName("Simple Test")
    public void javaExampleTest() {
        onView(withId(R.id.newNote)).perform(click());
        onView(allOf(withId(R.id.titleEdit), isDisplayed())).check(matches(withHint("Title")));
        onView(allOf(withId(R.id.bodyEdit), isDisplayed())).check(matches(withHint("Note")));
    }
}
