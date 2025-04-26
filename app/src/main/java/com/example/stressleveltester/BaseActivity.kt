package com.example.stressleveltester

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class BaseActivity : AppCompatActivity() {

    private lateinit var btStartTest : LinearLayout
    private lateinit var btSummaryTest : LinearLayout
    private lateinit var btMyProfile : LinearLayout

    private lateinit var view1 : View
    private lateinit var view2 : View
    private lateinit var view3 : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        btStartTest=findViewById(R.id.btStartTest)
        btSummaryTest=findViewById(R.id.btTestSummary)
        btMyProfile=findViewById(R.id.btMyProfile)

        view1=findViewById(R.id.view1)
        view2=findViewById(R.id.view2)
        view3=findViewById(R.id.view3)

        openFragment(StressHomeFragment())

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, StressHomeFragment())
//            .addToBackStack(null).commit()

        onClickListener()
    }

    fun onClickListener()
    {
        btStartTest.setOnClickListener {

            openFragment(StressHomeFragment())

            showView(view1)
            hideView(view2)
            hideView(view3)

        }

        btSummaryTest.setOnClickListener {

            openFragment(TestResultFragment())

            showView(view2)
            hideView(view1)
            hideView(view3)

        }

        btMyProfile.setOnClickListener {
            openFragment(ProfileFragment())

            showView(view3)
            hideView(view1)
            hideView(view2)
        }
    }

    fun showView(view: View)
    {
        view.visibility=View.VISIBLE
    }

    fun hideView(view: View)
    {
        view.visibility=View.INVISIBLE
    }

    private fun openFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }
}