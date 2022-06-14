package com.example.movieapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.movieapp.MainActivity
import com.example.movieapp.R

class ErrorFragment : Fragment(R.layout.error_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exceptionName = arguments?.getString("exceptionName", "Something went wrong")
        val exceptionNameTV : TextView = view.findViewById(R.id.exceptionName)
        val restartBtn : Button = view.findViewById(R.id.restartBtn)
        exceptionNameTV.text = exceptionName

        restartBtn.setOnClickListener{
            //if (arguments?.getString("fragmentName") == "MoviesFragment")
            //{
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
           //}
//            else if (arguments?.getString("fragmentName") == "PersonFragment")
//            {
//                val intent = Intent(activity, PersonFragment::class.java)
//                startActivity(intent)
//            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("Fragment", "ErrorFragment destroyed")
    }

}