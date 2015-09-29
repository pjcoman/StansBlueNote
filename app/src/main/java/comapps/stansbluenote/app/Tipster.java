package comapps.stansbluenote.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class Tipster extends Activity {

    // Widgets in the application

    private EditText txtAmount;
    private EditText txtPeople;
    private EditText txtTipOther;
    private RadioGroup rdoGroupTips;
    private Button btnCalculate;
    private Button btnReset;

    private TextView txtTipAmount;
    private TextView txtTotalToPay;
    private TextView txtTipPerPerson;
    private TextView txtpercentsign;

    // For the id of radio button selected

    private int radioCheckedId = -1;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipcalc);

        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        // Access the various widgets by their id in R.java

        txtAmount = (EditText) findViewById(R.id.txtAmount);

        //On app load, the cursor should be in the Amount field  

        txtAmount.requestFocus();

        txtPeople = (EditText) findViewById(R.id.txtPeople);
        txtTipOther = (EditText) findViewById(R.id.txtTipOther);
        txtpercentsign = (TextView) findViewById(R.id.txtpercentsign);

        rdoGroupTips = (RadioGroup) findViewById(R.id.RadioGroupTips);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        //On app load, the Calculate button is disabled

        btnCalculate.setEnabled(false);

        btnReset = (Button) findViewById(R.id.btnReset);

        txtTipAmount = (TextView) findViewById(R.id.txtTipAmount);
        txtTotalToPay = (TextView) findViewById(R.id.txtTotalToPay);
        txtTipPerPerson = (TextView) findViewById(R.id.txtTipPerPerson);

        // On app load, disable the 'Other tip' percentage text field

        txtTipOther.setEnabled(false); 

    
        /*
         * Attach a OnCheckedChangeListener to the
         * radio group to monitor radio buttons selected by user
         */

        rdoGroupTips.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // Enable/disable Other Percentage tip field

                if (checkedId == R.id.radioFifteen
                        || checkedId == R.id.radioTwenty) {
                    txtTipOther.setEnabled(false);
      
       /*
        * Enable the calculate button if Total Amount and No. of
        * People fields have valid values.
        */

                    btnCalculate.setEnabled(txtAmount.getText().length() > 0
                            && txtPeople.getText().length() > 0);
                }
                if (checkedId == R.id.radioOther) {

                    // enable the Other Percentage tip field

                    txtTipOther.setEnabled(true);
                    txtTipOther.setVisibility(View.VISIBLE);
                    txtpercentsign.setVisibility(View.VISIBLE);


                    // set the focus to this field

                    txtTipOther.requestFocus();
                    btnCalculate.setEnabled(txtAmount.getText().length() > 0 &&
                            txtPeople.getText().length() > 0 &&
                            txtTipOther.getText().length() > 0);
                }
                radioCheckedId = checkedId;
            }
        });


        txtAmount.setOnKeyListener(mKeyListener);
        txtPeople.setOnKeyListener(mKeyListener);
        txtTipOther.setOnKeyListener(mKeyListener);

        btnCalculate.setOnClickListener(mClickListener);
        btnReset.setOnClickListener(mClickListener);

    }

    private OnKeyListener mKeyListener = new OnKeyListener() {

        public boolean onKey(View v, int keyCode, KeyEvent event) {

            switch (v.getId()) {
                case R.id.txtAmount:
                case R.id.txtPeople:
                    btnCalculate.setEnabled(txtAmount.getText().length() > 0
                            && txtPeople.getText().length() > 0);
                case R.id.txtTipOther:
                    btnCalculate.setEnabled(txtAmount.getText().length() > 0
                            && txtPeople.getText().length() > 0);
                    break;
            }
            return false;
        }

    };
    private OnClickListener mClickListener = new OnClickListener() {

        public void onClick(View v) {
            if (v.getId() == R.id.btnCalculate) {


                calculate();
            } else {
                Log.i("setting values", "btnReset");
                reset();
            }
        }
    };

    private void reset() {
        txtTipAmount.setText("");
        txtTotalToPay.setText("");
        txtPeople.setText("");
        txtTipPerPerson.setText("");
        txtAmount.setText("");
        txtTipOther.setText("");
        rdoGroupTips.clearCheck();
        rdoGroupTips.check(R.id.radioFifteen);
        txtAmount.requestFocus();

        txtTipAmount.setVisibility(View.INVISIBLE);

        txtTotalToPay.setVisibility(View.INVISIBLE);

        txtTipPerPerson.setVisibility(View.INVISIBLE);

        txtTipOther.setVisibility(View.INVISIBLE);

        txtpercentsign.setVisibility(View.INVISIBLE);


    }

    private void calculate() {


        Double billAmount = Double.parseDouble(txtAmount.getText().toString());
        Double totalPeople = Double.parseDouble(txtPeople.getText().toString());


        Double percentage = null;
        boolean isError = false;

        if (billAmount < 1.0) {
            showErrorAlert("Enter a valid Total Amount.", txtAmount.getId());
            isError = true;
        }

        if (totalPeople < 1.0) {
            showErrorAlert("Enter a valid value for No. of people.",
                    txtPeople.getId());
            isError = true;
        }


        if (radioCheckedId == -1) {
            radioCheckedId = rdoGroupTips.getCheckedRadioButtonId();


        }

        if (radioCheckedId == R.id.radioFifteen) {


            percentage = 15.00;
        } else if (radioCheckedId == R.id.radioTwenty) {

            Log.i("setting values", "4");

            percentage = 20.00;

        } else if (radioCheckedId == R.id.radioOther) {

            percentage = Double.parseDouble(txtTipOther.getText().toString());

            Log.i("setting values", "5");

        }

        if (percentage < 1.0) {
            showErrorAlert("Enter a valid Tip percentage",
                    txtTipOther.getId());
            isError = true;
        }
            
            
            /*
             * If all fields are populated with valid values, then proceed to
             * calculate the tips
             */

        if (!isError) {


            Double tipAmount = ((billAmount * percentage) / 100);
            Double totalToPay = billAmount + tipAmount;
            Double perPersonPays = totalToPay / totalPeople;
      
               
                /* change to currency format here */

            NumberFormat formatdollarsUS = NumberFormat.getCurrencyInstance(Locale.US);

            String tipAmount1 = formatdollarsUS.format(tipAmount);
            String totalToPay1 = formatdollarsUS.format(totalToPay);
            String perPersonPays1 = formatdollarsUS.format(perPersonPays);

            txtTipAmount.setText(tipAmount1);
            txtTipAmount.setVisibility(View.VISIBLE);
            txtTotalToPay.setText(totalToPay1);
            txtTotalToPay.setVisibility(View.VISIBLE);
            txtTipPerPerson.setText(perPersonPays1);
            txtTipPerPerson.setVisibility(View.VISIBLE);


            ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                    .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
               
              /* txtTipAmount.setText("$" + tipAmount.toString()); 
                txtTotalToPay.setText("$" + totalToPay.toString());
                txtTipPerPerson.setText("$" + perPersonPays.toString()); */


        }

    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this).setTitle("Error")
                .setMessage(errorMessage).setNeutralButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        findViewById(fieldId).requestFocus();
                    }
                }).show();
    }
}