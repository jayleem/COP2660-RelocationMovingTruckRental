package com.jayleem.relocationmovingtruckrental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Calculate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        //instantiate controls
        TextView totalLabel = (TextView) findViewById(R.id.calcTotal);
        ImageView imgV = (ImageView) findViewById(R.id.calcTruckImage);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        float miles = sharedPref.getFloat("key1", 0);
        int truckType= sharedPref.getInt("key2", 0);
        float rentalCost = sharedPref.getFloat("key3", 0);

        //currency formatter
        float total = rentalCost + (miles * 0.99f);
        DecimalFormat currency = new DecimalFormat("$0.00");
        totalLabel.setText("Total: " + currency.format(total));

        //change image
        if (truckType == 1) {
            imgV.setImageResource(R.drawable.ten_ft_truck);
        }
        if (truckType == 2) {
            imgV.setImageResource(R.drawable.seventeen_ft_truck);
        }
        if (truckType == 3) {
            imgV.setImageResource(R.drawable.twentysix_ft_truck);
        }
    }
}
