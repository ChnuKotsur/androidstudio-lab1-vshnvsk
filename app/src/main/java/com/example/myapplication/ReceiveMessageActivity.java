package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReceiveMessageActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent = getIntent();
        String country = intent.getStringExtra("country");

        TextView messageView = findViewById(R.id.message);
        String anthem = getAnthemByCountry(country);
        messageView.setText(anthem);

        LinearLayout layout = findViewById(R.id.receive_layout);
        int flagResource = getResources().getIdentifier(country.toLowerCase() + "_flag", "drawable", getPackageName());
        layout.setBackgroundResource(flagResource);
    }

    private String getAnthemByCountry(String country){
        switch (country){
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
