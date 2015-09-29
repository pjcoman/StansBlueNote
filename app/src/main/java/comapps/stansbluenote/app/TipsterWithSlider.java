package comapps.stansbluenote.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


import java.text.NumberFormat;
import java.util.Locale;
//import java.lang.Double;

public class TipsterWithSlider extends Activity implements SeekBar.OnSeekBarChangeListener {

    // Widgets in the application

    private EditText txtAmount;
    private String txtPeople;
    private Integer tipPercentValueFromSlider;
    private Double tipPercentValue;

    Spinner noOfPeople;





    private TextView txtTipAmount;
    private TextView txtTotalToPay;
    private TextView txtTipPerPerson;
    private TextView txtpercentsign;

    private SeekBar tipSlider;
    private TextView tipAmount;
    private Button clearButton;

    // For the id of radio button selected



    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipcalcwithslider2);

        // Action Bar

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(R.color.Black));
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/chalkdust.ttf");
        int titleId = getResources().getIdentifier("action_bar_title", "id",
                "android");
        TextView actionbartitle = (TextView) findViewById(titleId);
        actionbartitle.setTextSize(18);
        actionbartitle.setTextColor(Color.rgb(94, 139, 246));

        // Action Bar end


        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        // Access the various widgets by their id in R.java

        noOfPeople = (Spinner) findViewById(R.id.spPeople);

        tipSlider = (SeekBar) findViewById(R.id.seekBar);
        tipAmount = (TextView) findViewById(R.id.tipAmount);

        tipSlider.setVisibility(View.INVISIBLE);
        tipAmount.setVisibility(View.INVISIBLE);

        tipPercentValue = .15;





        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtAmount.setText("");


        //On app load, the cursor should be in the Amount field  

        txtAmount.requestFocus();

        // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

       txtAmount.addTextChangedListener(new TextWatcher() {

           public void afterTextChanged(Editable s) {





           }

           public void beforeTextChanged(CharSequence s, int start,
                                         int count, int after) {
           }

           public void onTextChanged(CharSequence s, int start,
                                     int before, int count) {

               tipSlider.setVisibility(View.VISIBLE);
               tipAmount.setVisibility(View.VISIBLE);

               if(!s.toString().equals("")){
                   txtAmount.removeTextChangedListener(this);

                   String cleanString = s.toString().replaceAll("[$,.]", "");
                   Log.i("SBN cleanString" , "cleanString = " + cleanString);
                   double parsed = Double.parseDouble(cleanString);
                   String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));
                   Log.i("SBN formatted" , "formatted = " + formatted);

                   txtAmount.setText(formatted);
                   txtAmount.setSelection(formatted.length());

                   txtAmount.addTextChangedListener(this);
               }








           }
       });






    //    txtPeople = (EditText) findViewById(R.id.txtPeople);

        txtpercentsign = (TextView) findViewById(R.id.txtpercentsign);







        txtTipAmount = (TextView) findViewById(R.id.txtTipAmount);
        txtTotalToPay = (TextView) findViewById(R.id.txtTotalToPay);
        txtTipPerPerson = (TextView) findViewById(R.id.txtTipPerPerson);

        // On app load, disable the 'Other tip' percentage text field



        // txtPeople.setText("1");

        // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

        noOfPeople.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                String numberofpeople = adapter.getItemAtPosition(position).toString();
                txtPeople = numberofpeople;

                // Showing selected spinner item
                // Toast.makeText(getApplicationContext(),
                //        "Selected Country : " + item, Toast.LENGTH_LONG).show();

                if (txtAmount.length() != 0)  {

                String cleantxtAmount = txtAmount.getText().toString();
                cleantxtAmount = cleantxtAmount.replaceAll("[$,]", "");

                Log.i("SBN cleantxtAmount" , "cleantxtAmount = " + cleantxtAmount);

                Double billAmount = Double.parseDouble(cleantxtAmount);
                Double totalPeople = Double.parseDouble(txtPeople);



                boolean isError = false;

                if (billAmount < 1.0) {
                    showErrorAlert("Enter a valid Total Amount.", txtAmount.getId());
                    isError = true;
                }









            /*
             * If all fields are populated with valid values, then proceed to
             * calculate the tips
             */

                if (!isError) {

                    tipPercentValueFromSlider = tipSlider.getProgress();

                    tipPercentValue = (double) tipPercentValueFromSlider / 100;

                    NumberFormat format = NumberFormat.getPercentInstance(Locale.getDefault());
                    String percentage = format.format(tipPercentValue);

                    tipAmount.setText(percentage);


                    Double tipAmount = (billAmount * tipPercentValue);
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




            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


        // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

        Button clearButton = (Button) findViewById(R.id.button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                EditText et = (EditText) findViewById(R.id.txtAmount);
                et.setText("0");
                txtTipAmount.setVisibility(View.INVISIBLE);
                txtTotalToPay.setVisibility(View.INVISIBLE);
                txtTipPerPerson.setVisibility(View.INVISIBLE);

                tipSlider.setVisibility(View.INVISIBLE);
                tipAmount.setVisibility(View.INVISIBLE);

                noOfPeople.setSelection(1);
            }
        });

        // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx




        tipSlider.setOnSeekBarChangeListener(this);

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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar == tipSlider && fromUser) {

            String cleantxtAmount = txtAmount.getText().toString();
            cleantxtAmount = cleantxtAmount.replaceAll("[$,]", "");

            Log.i("SBN cleantxtAmount" , "cleantxtAmount = " + cleantxtAmount);

            Double billAmount = Double.parseDouble(cleantxtAmount);
            Double totalPeople = Double.parseDouble(txtPeople);



            boolean isError = false;

            if (billAmount < 1.0) {
                showErrorAlert("Enter a valid Total Amount.", txtAmount.getId());
                isError = true;
            }









            /*
             * If all fields are populated with valid values, then proceed to
             * calculate the tips
             */

            if (!isError) {

                tipPercentValueFromSlider = seekBar.getProgress();

                tipPercentValue = (double) tipPercentValueFromSlider / 100;

                NumberFormat format = NumberFormat.getPercentInstance(Locale.getDefault());
                String percentage = format.format(tipPercentValue);

                tipAmount.setText(percentage);


                Double tipAmount = (billAmount * tipPercentValue);
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

    }




    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    // Action Bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);




    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_map:
                Map();
                return true;
            case R.id.action_gmail:
                Gmail();
                return true;
            case R.id.action_call:
                CallStans();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Map() {

        // TODO Auto-generated method stub

        Intent intent5 = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/Stan's+Blue+Note/@32.824255,-96.769911,17z/data=!3m1!4b1!4m2!3m1!1s0x864e9f40bd0b9551:0x3c0fa6a555470cde?hl=en"));
        intent5.setComponent(new ComponentName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"));
        startActivity(intent5);

    }


    private void Gmail() {
        // TODO Auto-generated method stub

        // The following code is the implementation of Email client
        Intent intent3 = new Intent(android.content.Intent.ACTION_SEND);
        intent3.setType("text/plain");
        String[] address = {"stansbluenote@gmail.com"};

        intent3.putExtra(android.content.Intent.EXTRA_EMAIL, address);
        intent3.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
        intent3.putExtra(android.content.Intent.EXTRA_TEXT, "text");

        startActivityForResult((Intent.createChooser(intent3, "Email")), 1);

    }



    private void CallStans() {

        // TODO Auto-generated method stub

        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("tel:2148271977"));
        startActivity(callIntent);


    }

    // Action Bar end
}