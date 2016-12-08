package com.recipeapp.recipeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView detailText;
    String MY_PREFS_NAME;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_Show = (Button)findViewById(R.id.btn_Show);
        Button btn_fav = (Button) findViewById(R.id.my_fav);

        btn_Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReciptListViewActivity.class);
                startActivity(intent);
            }
        });

        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReciptListViewActivity.class);
                intent.putExtra("fav",1);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("isOpenFirstTime", false)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isOpenFirstTime", true);
            editor.commit();
            readFromFile();
        }



    }

    private void readFromFile(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("recipe.txt")));

            StringBuilder sb = new StringBuilder();
            String mLine = reader.readLine();
            while (mLine != null) {
                sb.append(mLine); // process line
                mLine = reader.readLine();
            }
            reader.close();
String temp = sb.toString();
            JSONObject jsonObj = new JSONObject(temp);
            JSONArray jsonArray = jsonObj.getJSONArray("recipe");
                for(int i=0; i < jsonArray.length(); i++  ) {

                    JSONObject c = jsonArray.getJSONObject(i);
                    RecipeModal modal = new RecipeModal();
                    modal.set_name(c.getString("name"));
                    modal.set_summary(c.getString("summary"));
                    modal.set_ingredient(c.getString("ingredients"));
                    modal.set_description(c.getString("description"));
                    modal.set_favorite(0);
                    DatabaseHandler db = new DatabaseHandler(MainActivity.this);

                    db.addRecipe(modal);

                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
