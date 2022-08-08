package com.example.employeemanagementmvvm.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.employeemanagementmvvm.R
import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData
import com.example.employeemanagementmvvm.ui.EmployeeActivity
import com.example.employeemanagementmvvm.ui.EmployeeViewModel
import com.example.employeemanagementmvvm.util.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_employee.*
import kotlinx.android.synthetic.main.fragment_update_employee.*


class UpdateEmployeeFragment : Fragment(R.layout.fragment_update_employee) {
    lateinit var viewModel: EmployeeViewModel
    lateinit var employee:Data
    val args: UpdateEmployeeFragmentArgs by navArgs()
    val TAG = "CreateEmployeeFragment"
    lateinit var emp_name:String
    var emp_age:Int = 0
    var emp_salary:Int = 0
    var emp_id:Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as EmployeeActivity).employeeViewModel
        employee = args.employeedata
        emp_id = emp_id
        emp_name = employee.employee_name
        emp_age = employee.employee_age
        emp_salary = employee.employee_salary

        updateEmpNameET.setText(emp_name)
        updateEmpAgeET.setText(emp_age)
        updateEmpSalaryET.setText(emp_salary)


        viewModel.updateEmployee.observe(viewLifecycleOwner, Observer { response->
            when (response) {
                is Resource.Success -> {

                    response.data?.let{
                        Log.d(TAG, "Employee Created msg ${it.data.name}")
                    }
                    hideProgressBar()
                    Log.e(TAG, "Employee Created ${response.message.toString()}")
                    Snackbar.make(view,"Employee Updated Successfully!!", Snackbar.LENGTH_SHORT).show()

                 //   viewModel.insert(user)

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
            var emp = EmployeeData(emp_age,emp_name,emp_salary,emp_id)
                viewModel.updateEmployeeData(emp_id,emp)
//            }
//            else{
//                Snackbar.make(view,"Fields should not be empty!", Snackbar.LENGTH_SHORT).show()
//            }
        })


    }

    private fun hideProgressBar() {
        paginationProgressBarUpdate.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarUpdate.visibility = View.VISIBLE
    }


    private fun hideSoftKeyboard(view: View) {
        val manager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}