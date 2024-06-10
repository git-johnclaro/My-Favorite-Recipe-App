package com.example.myfavoriterecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void performSearch(String query) {
        Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        Intent intent = new Intent(HomePage.this, CreateRecipe.class);
        startActivity(intent);
    }

    public void onImageButtonClick(View v) {
        Intent intent = new Intent(HomePage.this, GoBackToHome.class);
        startActivity(intent);
    }
}
