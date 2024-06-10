package com.example.myfavoriterecipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;

public class CreateRecipe extends AppCompatActivity implements OnClickListener  {


    EditText etName, etPTime, etCTime, etServ, etIngred;
    Button btnSubmit, btnDelete, btnUpdate ,btnList;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrecipe);
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });

                etName = findViewById(R.id.etRecipeName);
                etPTime = findViewById(R.id.etTime);
                etCTime = findViewById(R.id.etCookingTime);
                etServ = findViewById(R.id.etServings);
                etIngred = findViewById(R.id.etIngredients);

                btnSubmit = findViewById(R.id.btnSubmit);
                btnDelete = findViewById(R.id.btnDelete);
                btnUpdate = findViewById(R.id.btnUpdate);
                btnList = findViewById(R.id.btnList);

                btnSubmit.setOnClickListener(this);
                btnDelete.setOnClickListener(this);
                btnUpdate.setOnClickListener(this);
                btnList.setOnClickListener(this);

                db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS recipe(food_name VARCHAR, ptime VARCHAR, ctime VARCHAR, serve VARCHAR, ingredients VARCHAR);");
            }

            public void clearText() {
                etName.setText("");
                etPTime.setText("");
                etCTime.setText("");
                etServ.setText("");
                etIngred.setText("");
            }

            public void showMessage(String title, String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.show();
            }

            public void deleteRecipe(String recipeName) {
                int deletedRows = db.delete("recipe", "food_name = ?", new String[]{recipeName});
                if (deletedRows > 0) {
                    showMessage("Success", "Record deleted.");
                } else {
                    showMessage("Error", "No record found with the provided name.");
                }
                clearText();
            }

            public void updateRecipe(String recipeName, String ptime, String ctime, String serve, String ingredients) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("ptime", ptime);
                contentValues.put("ctime", ctime);
                contentValues.put("serve", serve);
                contentValues.put("ingredients", ingredients);

                int updatedRows = db.update("recipe", contentValues, "food_name = ?", new String[]{recipeName});
                if (updatedRows > 0) {
                    showMessage("Success", "Record updated.");
                } else {
                    showMessage("Error", "No record found with the provided name.");
                }
                clearText();
            }

            @Override
            public void onClick(View view) {
                if (view == btnList) {
                    Intent i = new Intent(CreateRecipe.this, ShowListRecipe.class);
                    startActivity(i);
                }
                if (view == btnSubmit) {
                    db.execSQL("INSERT INTO recipe VALUES('" + etName.getText() + "','" + etPTime.getText() + "','" + etCTime.getText() + "','" +
                            etServ.getText() + "','" + etIngred.getText() + "');");
                    showMessage("Success", "Record added.");
                    clearText();
                }
                if (view == btnDelete) {
                    String recipeName = etName.getText().toString();
                    if (!recipeName.isEmpty()) {
                        deleteRecipe(recipeName);
                    } else {
                        showMessage("Error", "Please enter the recipe name to delete.");
                    }
                }
                if (view == btnUpdate) {
                    String recipeName = etName.getText().toString();
                    if (!recipeName.isEmpty()) {
                        String ptime = etPTime.getText().toString();
                        String ctime = etCTime.getText().toString();
                        String serve = etServ.getText().toString();
                        String ingredients = etIngred.getText().toString();
                        updateRecipe(recipeName, ptime, ctime, serve, ingredients);
                    } else {
                        showMessage("Error", "Please enter the recipe name to update.");
                    }
                }
            }
        }


