package com.example.coding_basics

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.coding_basics.databinding.ActivityTipcalculatorBinding
import java.text.NumberFormat

class tipcalculator : AppCompatActivity() {
    lateinit var binding: ActivityTipcalculatorBinding // Binding object instance with access to the views in the activity_main.xml layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipcalculator)
        val actionBar = supportActionBar //To change the ActionBar
        actionBar!!.title =
            "Tip Calculator" //To check if actionbar is not empty and then set the title to specified.
        /*above line can also be written as :-
        if(actionBar != null){
        actionBar.title = "Tip Calculator"
        }
         */
        actionBar.setDisplayHomeAsUpEnabled(true) //To enable back button on actionBar, need to add parent activity for the same in manifest file
        binding = ActivityTipcalculatorBinding.inflate(layoutInflater) // Inflate the layout XML file and return a binding object instance
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener { calculateTip() } // Setup a click listener on the calculate button to calculate the tip
        binding.amountEditText.setOnKeyListener { view, keyCode, _->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.amountEditText.text.toString()// Get the decimal value from the cost of service EditText field
        val amount = stringInTextField.toDoubleOrNull()

        if(amount == null || amount==0.0){  // If the cost is null or 0, then display 0 tip and exit this function early.
            displayTip(0.0)
            return
        }

        val tipPercentCalculate:Double = when(binding.tipCat.checkedRadioButtonId){ // Get the tip percentage based on which radio button is selected
            R.id.radAmazing -> 0.20
            R.id.radGood -> 0.18
            R.id.radOk -> 0.15
            else ->0.0
        }

        var finalCost = tipPercentCalculate * amount // Calculate the tip

        val roundOn = binding.swtRoundup.isChecked  /*If the switch for rounding up the tip toggled on (isChecked is true), then round up the
         finalCost. Otherwise do not change the tip value. */
        if(roundOn){
            finalCost = kotlin.math.ceil(finalCost)
        }

        displayTip(finalCost)
        val totalAmt:Double = finalCost + amount
        displayTotal(totalAmt)
    }
    private fun displayTip(tip: Double) { /*Format the tip amount according to the local currency and display it onscreen.Example would be "Tip Amount: $10.00".*/
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtCalculated.text = getString(R.string.tip_amt,formattedTip)
    }
    private fun displayTotal(amt: Double) { /*Format the tip amount according to the local currency and display it onscreen.Example would be "Tip Amount: $10.00".*/
        val formattedTip = NumberFormat.getCurrencyInstance().format(amt)
        binding.txtTotal.text = getString(R.string.total_amt,formattedTip)
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean { //Key listener for hiding the keyboard when the "Enter" button is tapped.
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}
