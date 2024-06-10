package com.example.myfavoriterecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GoBackToHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showingredients);
    }

    public void Back(View view) {
        finish();
    }
}
