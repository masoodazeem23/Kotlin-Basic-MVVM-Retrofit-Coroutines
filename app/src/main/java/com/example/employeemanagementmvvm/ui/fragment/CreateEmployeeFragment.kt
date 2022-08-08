package com.example.employeemanagementmvvm.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.util.Util
import com.example.employeemanagementmvvm.R
import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData
import com.example.employeemanagementmvvm.ui.EmployeeActivity
import com.example.employeemanagementmvvm.ui.EmployeeViewModel
import com.example.employeemanagementmvvm.util.Resource
import com.example.employeemanagementmvvm.util.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_employee.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import kotlin.time.Duration.Companion.days


class CreateEmployeeFragment : Fragment(R.layout.fragment_create_employee) {

    lateinit var employeeViewModel: EmployeeViewModel
    lateinit var user:EmployeeData
    val TAG = "CreateEmployeeFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        employeeViewModel = (activity as EmployeeActivity).employeeViewModel

        employeeViewModel.createEmployee.observe(viewLifecycleOwner, Observer { response->
            when (response) {
                is Resource.Success -> {

                    response.data?.let{
                        Log.d(TAG, "Employee Created msg ${it.data.name}")
                        user = EmployeeData(it.data.age,it.data.name,it.data.salary,it.data.id)

                    }
                    hideProgressBar()
                    Log.e(TAG, "Employee Created ${response.message.toString()}")
                    Snackbar.make(view,"Employee Inserted Successfully!!", Snackbar.LENGTH_SHORT).show()

                    employeeViewModel.insert(user)

                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "error in api calling: $message")
                        val ctw = ContextThemeWrapper(context, R.style.CustomSnackbarTheme)
                        Snackbar.make(view,"Something went wrong. Please try again!", Snackbar.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
                else -> {}
            }
        })

        saveEmpBtn.setOnClickListener(View.OnClickListener {
            hideSoftKeyboard(view)
            Log.d(TAG,"setOnClickListener")
           if(empAgeET.text.isNotEmpty() && empNameET.text.isNotEmpty() && empSalaryET.text.isNotEmpty() ) {
               user = EmployeeData(
                   empAgeET.text.toString().toInt(),
                   empNameET.text.toString(),
                   empSalaryET.text.toString().toInt(),
                   null
               )

               employeeViewModel.createEmployee(user)
           }
           else{
               Snackbar.make(view,"Fields should not be empty!", Snackbar.LENGTH_SHORT).show()
           }
        })


    }

    private fun hideProgressBar() {
        paginationProgressBarCreate.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarCreate.visibility = View.VISIBLE
    }


    private fun hideSoftKeyboard(view: View) {
        val manager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }






}