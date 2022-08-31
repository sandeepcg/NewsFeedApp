package com.example.mapapplication.hiltUtil

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions
import com.sandeep.assignment.newsfeedapp.R
import com.sandeep.assignment.newsfeedapp.ui.MainActivity

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: Fragment.() -> Unit = {}
) {
    val startActivityIntent =
        Intent.makeMainActivity(
                ComponentName(
                    ApplicationProvider.getApplicationContext(), MainActivity::class.java))
            .putExtra(
                "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
                themeResId)

    ActivityScenario.launch<MainActivity>(startActivityIntent).onActivity { activity ->
        fragmentFactory?.let { activity.supportFragmentManager.fragmentFactory = it }
        val fragment: Fragment =
            activity.supportFragmentManager.fragmentFactory.instantiate(
                Preconditions.checkNotNull(T::class.java.classLoader) as ClassLoader,
                T::class.java.name)
        fragment.arguments = fragmentArgs
        activity
            .supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()
        fragment.action()
    }
}
