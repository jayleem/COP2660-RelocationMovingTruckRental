package com.jayleem.relocationmovingtruckrental;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Toast;

public class Home extends AppCompatActivity {

    int miles;
    int truckType;
    float rentalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //controls instantiated
        final ImageButton[] buttons = {
                (ImageButton) findViewById(R.id.btnTruckTen),
                (ImageButton) findViewById(R.id.btnTruckTenSeven),
                (ImageButton) findViewById(R.id.btnTruckTwoSix)
        };
        final EditText numMiles = (EditText) findViewById(R.id.editTextMiles);

        //SharedObject instantiated
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //register image buttons event listener
        for (ImageButton ib : buttons) {
            if (ib != null) {
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Get sender and assign it as tmpButton
                        ImageButton tmpBtn = (ImageButton) findViewById(v.getId());

                        //Logic for updating cost variable
                        if (tmpBtn == buttons[0]) {
                            truckType = 1;
                            rentalCost = 19.95f;
                        }
                        if (tmpBtn == buttons[1]) {
                            truckType = 2;
                            rentalCost = 29.95f;
                        }
                        if (tmpBtn == buttons[2]) {
                            truckType = 3;
                            rentalCost = 39.95f;
                        }
                        //Change background color of the pressed button
                        changeColor(tmpBtn, buttons);
                    }
                });
            }
        }

        //register btnGetPrice event listener
        Button btn = (Button) findViewById(R.id.btnGetPrice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPref.edit();

                //error checking user selected a truck
                if (truckType > 0) {
                    editor.putInt("key2", truckType);
                    editor.putFloat("key3", rentalCost);
                } else {
                    Toast.makeText(Home.this, "Select a rental truck before continuing", Toast.LENGTH_LONG).show();
                    return;
                }

                //error checking user input miles
                if (numMiles.getText().length() > 0) {
                    miles = Integer.parseInt(numMiles.getText().toString());
                    if (miles >= 0) {
                        editor.putFloat("key1", miles);
                    }
                    else {
                        Toast.makeText(Home.this, "Invalid input, input a valid number of miles", Toast.LENGTH_LONG).show();
                        return;
                    }
                } else {
                    Toast.makeText(Home.this, "Input a valid number of miles", Toast.LENGTH_LONG).show();
                    return;
                }

                //if error checking passed then start next activity
                editor.commit();
                startActivity(new Intent(Home.this, Calculate.class));
            }
        });
    }


    private void changeColor(ImageButton tmp, ImageButton[] buttons) {
        for (ImageButton tmpIB : buttons) {
            if (tmp == tmpIB) {
                tmpIB.setBackgroundColor(Color.parseColor("#5C3F3E3E"));
            } else {
                tmpIB.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            }

        }
    }
}
