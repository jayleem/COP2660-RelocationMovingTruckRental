package com.jayleem.relocationmovingtruckrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int minPX = 36;
    int maxPX = 40;
    int animDur = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //register event listener to TextView
        final TextView tv = (TextView) findViewById(R.id.welcomeMsg);
        //text animation
        ValueAnimator animator = ValueAnimator.ofFloat(minPX, maxPX);
        animator.setDuration(animDur);
        animator.setRepeatMode(animator.REVERSE);
        animator.setRepeatCount(animator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tv.setTextSize(animatedValue);
            }
        });
        animator.start();

        //register event listener to layout
        ConstraintLayout cLayout = (ConstraintLayout) findViewById(R.id.welcomeScreen);
        cLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Home.class));
            }
        });
    }
}
