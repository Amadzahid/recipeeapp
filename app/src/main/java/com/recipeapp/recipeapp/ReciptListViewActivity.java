package com.recipeapp.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amadzahid on 01/12/2016.
 */
public class ReciptListViewActivity extends AppCompatActivity {

    List <RecipeModal> recipe;
    ListView listView;
    int activityType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);
        activityType = getIntent().getIntExtra("fav", 0);


    }

    @Override
    protected void onResume() {
        super.onResume();
        listView = (ListView)findViewById(R.id.listView1);
        DatabaseHandler dbH = new DatabaseHandler(ReciptListViewActivity.this);
        if(activityType==0) {
            recipe = dbH.getAllRecipe(); //= new ArrayList <RecipeModal> ();
        }else if(activityType==1){
            recipe = dbH.getFavRecipe();
        }



        RecipeListAdapter adapter = new RecipeListAdapter(ReciptListViewActivity.this, recipe);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ReciptListViewActivity.this, SingleReciptActivity.class);
                intent.putExtra("id", recipe.get(position).get_id());
                intent.putExtra("name", recipe.get(position).get_name());
                intent.putExtra("image", recipe.get(position).get_image());
                intent.putExtra("ingre", recipe.get(position).get_ingredient());
                intent.putExtra("desp", recipe.get(position).get_description());
                intent.putExtra("favorite", recipe.get(position).get_favorite());
                intent.putExtra("summary", recipe.get(position).get_summary());
                startActivity(intent);

            }
        });
    }
}
