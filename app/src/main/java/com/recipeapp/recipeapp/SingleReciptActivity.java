package com.recipeapp.recipeapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by amadzahid on 01/12/2016.
 */
public class SingleReciptActivity extends AppCompatActivity {
    TextView detailText;
    ImageView singleImg;
    RecipeModal modal;
    ImageView favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_recipe);
        Button btnLeft = (Button)findViewById(R.id.ingredientsbtn);
        Button btnRight = (Button)findViewById(R.id.descriptionbtn);
        detailText = (TextView)findViewById(R.id.textview);
        singleImg = (ImageView) findViewById(R.id.recipeImageView);
        favBtn = (ImageView) findViewById(R.id.add_fav);
        TextView recipeTextTitle = (TextView)findViewById(R.id.recipeTextTitle);

        modal = new RecipeModal();
        modal.set_id(getIntent().getIntExtra("id", 0));
        modal.set_name( getIntent().getStringExtra("name"));
        modal.set_image(getIntent().getIntExtra("image", 0));
        modal.set_ingredient( getIntent().getStringExtra("ingre"));
        modal.set_description( getIntent().getStringExtra("desp"));
        modal.set_favorite(getIntent().getIntExtra("favorite", 0));
        modal.set_summary(getIntent().getStringExtra("summary"));
        if(modal.get_favorite()==0){
            favBtn.setImageResource(R.drawable.heart_white);
        }else if(modal.get_favorite() == 1){
            favBtn.setImageResource(R.drawable.heart_red);
        }

        try
        {
            // get input stream
            InputStream ims = getAssets().open(modal.get_name() + ".jpeg");//modal.set]_name( getIntent().getStringExtra("name")+".jpeg"));
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            singleImg.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            return;
        }




        showIngredientOrDescrip(1);

        recipeTextTitle.setText(modal.get_name());

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIngredientOrDescrip(1);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIngredientOrDescrip(2);
            }
        });
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(SingleReciptActivity.this);
                if(modal.get_favorite()==0){
                    favBtn.setImageResource(R.drawable.heart_red);
                    modal.set_favorite(1);
                    db.updateRecipe(modal);
                }else if(modal.get_favorite() == 1){
                    favBtn.setImageResource(R.drawable.heart_white);
                    modal.set_favorite(0);
                    db.updateRecipe(modal);
                }
            }
        });


    }

    private void showIngredientOrDescrip(int pClickButtonTag){
        if(pClickButtonTag == 1){
            detailText.setText(modal.get_ingredient());
        }else if(pClickButtonTag == 2){
            detailText.setText(modal.get_description());
        }
    }
}
