package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateMessageActivity extends Activity {

    private Spinner spinner;
    private String[] countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        countries = getResources().getStringArray(R.array.counties);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countries[position];
                findViewById(R.id.sendOnActivity).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CreateMessageActivity.this, ReceiveMessageActivity.class);
                        intent.putExtra("country", selectedCountry);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //not used
            }
        });
    }

    public void onSendMessageToApp(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        String selectedCountry = spinner.getSelectedItem().toString();
        String countryAnthem = getAnthemByCountry(selectedCountry);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Anthem of " + selectedCountry);
        intent.putExtra(Intent.EXTRA_TEXT, countryAnthem);

        String chooserTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
        startActivity(chosenIntent);
    }


    private String getAnthemByCountry(String country) {
        switch (country) {
            case "Sudan":
                return getString(R.string.sudan_anthem);
            case "Argentina":
                return getString(R.string.argentina_anthem);
            case "Norvegia":
                return getString(R.string.norvegia_anthem);
            case "FrenchGuiana":
                return getString(R.string.frenchguiana_anthem);
            case "Mongolia":
                return getString(R.string.mongolia_anthem);
            default:
                return "";
        }
    }

}
