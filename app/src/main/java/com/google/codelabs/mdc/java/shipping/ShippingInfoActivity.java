package com.google.codelabs.mdc.java.shipping;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShippingInfoActivity extends AppCompatActivity {

    private String selectedItemText;
    private LinearLayout parent_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipping_info_activity);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View rootView = findViewById(android.R.id.content);
        Button button = findViewById(R.id.save_button);
        parent_layout = findViewById(R.id.parent_layout);

        final List<TextInputLayout> textInputLayouts = Utils.findViewsWithType(
                rootView, TextInputLayout.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* boolean noErrors = true;
                for (TextInputLayout textInputLayout : textInputLayouts) {
                    String editTextString = textInputLayout.getEditText().getText().toString();
                    if (editTextString.isEmpty()) {
                        textInputLayout.setError(getResources().getString(R.string.error_string));
                        noErrors = false;
                    } else {
                        textInputLayout.setError(null);
                    }
                }
                if (noErrors) {
                    // All fields are valid!

                }*/
            }
        });

        // Get reference of widgets from XML layout
        final Spinner spinner1 = findViewById(R.id.spinner1);
        final Spinner spinner2 = findViewById(R.id.spinner2);
        final Spinner spinner3 = findViewById(R.id.spinner3);
        final Spinner spinner4 = findViewById(R.id.spinner4);
        final Spinner spinner5 = findViewById(R.id.spinner5);

        onSpnTouchListener(spinner1);
        onSpnTouchListener(spinner2);
        onSpnTouchListener(spinner3);
        onSpnTouchListener(spinner4);
        onSpnTouchListener(spinner5);

        String[] testArray = getResources().getStringArray(R.array.test);
        final List<String> plantsList = new ArrayList<>(Arrays.asList(testArray));

        spinner1.setAdapter(setArrayAdapter(plantsList));
        spinner2.setAdapter(setArrayAdapter(plantsList));
        spinner3.setAdapter(setArrayAdapter(plantsList));
        spinner4.setAdapter(setArrayAdapter(plantsList));
        spinner5.setAdapter(setArrayAdapter(plantsList));

        onItemSelectedListener(spinner1);
        onItemSelectedListener(spinner2);
        onItemSelectedListener(spinner3);
        onItemSelectedListener(spinner4);
        onItemSelectedListener(spinner5);

        Utils.hideKeyboard(this);
    }

    private void onSpnTouchListener(Spinner spinner) {

        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                parent_layout.requestFocus();
                return false;
            }
        });


    }

    private void getFocusOnSpinner(Spinner spinner) {
        spinner.setFocusable(true);
        spinner.setFocusableInTouchMode(true);
        spinner.requestFocus();
    }

    public String onItemSelectedListener(final Spinner spinner) {

        selectedItemText = "";
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);

                if (position > 0) {

                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorHint));
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return selectedItemText;
    }

    public ArrayAdapter setArrayAdapter(List<String> plantsList) {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, plantsList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_drop_down);

        return spinnerArrayAdapter;
    }

}
