package com.example.a3lab;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAdditionUI() {

        onView(withId(R.id.txtResult)).perform(typeText("2"));
        onView(withId(R.id.btnPlus)).perform(click());
        onView(withId(R.id.txtResult)).perform(typeText("3"));
        onView(withId(R.id.btnEquals)).perform(click());
        onView(withId(R.id.txtResult)).check(matches(withText("5.0")));
    }

    @Test
    public void testDivisionByZeroUI() {
        onView(withId(R.id.txtResult)).perform(typeText("6"));
        onView(withId(R.id.btnDivide)).perform(click());
        onView(withId(R.id.txtResult)).perform(typeText("0"));
        onView(withId(R.id.btnEquals)).perform(click());
        onView(withId(R.id.txtResult)).check(matches(withText("Error")));
    }
}
