package com.kasparpeterson.mctdddemo.signup;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kasparpeterson.mctdddemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by kaspar on 05/04/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    @Rule
    public ActivityTestRule<SignUpActivity> activity = new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void onSignUpClicked_name_and_password_empty() {
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.signup_state_text_view)).check(matches(withText("")));

        onView(withId(R.id.signup_name_edit_text)).check(matches(hasErrorText("Name has to be filled")));
        onView(withId(R.id.signup_password_edit_text)).check(matches(hasErrorText("Password has " +
                "to be at least 8 characters")));
    }

    @Test
    public void onSignUpClicked_valid() {
        onView(withId(R.id.signup_name_edit_text)).perform(typeText("Name"));
        onView(withId(R.id.signup_password_edit_text)).perform(typeText("P2345678"));
        onView(withId(R.id.signup_button)).perform(click());
        onView(withId(R.id.signup_state_text_view)).check(matches(withText(R.string.signup_signup_successful)));
    }

}
