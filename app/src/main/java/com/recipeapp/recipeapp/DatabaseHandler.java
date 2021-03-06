package com.recipeapp.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by amadzahid on 25/11/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Recipes_app";
    // Contacts table name
    private static final String TABLE_CONTACTS = "recipe";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SUM = "summary";
    private static final String KEY_IMG = "image";
    private static final String KEY_ING = "ingredient";
    private static final String KEY_DES = "description";
    private static final String KEY_FAV = "favorite";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_SUM + " TEXT, "
                + KEY_IMG + " INTEGER, "
                + KEY_ING + " TEXT, "
                + KEY_DES + " TEXT,"
                + KEY_FAV + " INTEGER"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }
    // Adding new contact
    void addRecipe(RecipeModal recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.get_name()); // Recipe Name
        values.put(KEY_SUM, recipe.get_summary()); // Recipe Summary
        values.put(KEY_IMG, recipe.get_image()); // Recipe Image
        values.put(KEY_ING, recipe.get_ingredient()); // Recipe ingredient
        values.put(KEY_DES, recipe.get_description()); // Recipe Description
        values.put(KEY_FAV, recipe.get_favorite()); // Recipe favorite
        // Inserting Row
        long result = db.insert(TABLE_CONTACTS, null, values);
        Log.v("Inserted", "Inserted = " + result);
        db.close(); // Closing database connection
    }
    // Getting single contact
    RecipeModal getRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME,KEY_SUM, KEY_IMG, KEY_ING, KEY_DES }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        RecipeModal recipe = new RecipeModal(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5),Integer.parseInt(cursor.getString(6)));
        // return contact
        return recipe;
    }
    boolean checkIfExist(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_IMG }, KEY_IMG + "=?",
                new String[] { name }, null, null, null, null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    // Getting All Recipe
    public List<RecipeModal> getAllRecipe() {
        List<RecipeModal> recipeList = new ArrayList<RecipeModal>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeModal recipe = new RecipeModal();
                recipe.set_id(Integer.parseInt(cursor.getString(0)));
                recipe.set_name(cursor.getString(1));
                recipe.set_summary(cursor.getString(2));
                recipe.set_image(Integer.parseInt(cursor.getString(3)));
                recipe.set_ingredient(cursor.getString(4));
                recipe.set_description(cursor.getString(5));
                recipe.set_favorite(Integer.parseInt(cursor.getString(6)));
                // Adding contact to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }
        // return contact list
        return recipeList;
    }

    // Getting All Favorite Recipe
    public List<RecipeModal> getFavRecipe() {
        List<RecipeModal> recipeList = new ArrayList<RecipeModal>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS + " where " + KEY_FAV  + "=1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeModal recipe = new RecipeModal();
                recipe.set_id(Integer.parseInt(cursor.getString(0)));
                recipe.set_name(cursor.getString(1));
                recipe.set_summary(cursor.getString(2));
                recipe.set_image(Integer.parseInt(cursor.getString(3)));
                recipe.set_ingredient(cursor.getString(4));
                recipe.set_description(cursor.getString(5));
                recipe.set_favorite(Integer.parseInt(cursor.getString(6)));
                // Adding contact to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }
        // return contact list
        return recipeList;
    }

    // Updating single contact and update with phone
    public int updateRecipe(RecipeModal recipe) {
//        String updateQuery = "UPDATE "+ TABLE_CONTACTS  +
//                " SET  ('"+ KEY_NAME + "', '" + KEY_SUM + "', '" + KEY_IMG + "', '" + KEY_ING+ "', '" + KEY_DES+ "', '" + KEY_FAV + "')" +
//                "   value ('"+ recipe.get_name()+ "', '" + recipe.get_summary()+ "', " + recipe.get_image()+ ", '" + recipe.get_ingredient()+ "', '" + recipe.get_description()+ "', '" + recipe.get_favorite() + ")" +
//                "WHERE " + KEY_ID + "="+ recipe.get_id() +";";
//
        SQLiteDatabase db = this.getWritableDatabase();
//        db.rawQuery(updateQuery, null);
//        db.close();
        ContentValues data=new ContentValues();
        data.put(KEY_NAME,recipe.get_name());
        data.put(KEY_FAV,recipe.get_favorite());
        data.put(KEY_SUM,recipe.get_summary());
        data.put(KEY_IMG,recipe.get_image());
        data.put(KEY_ING,recipe.get_ingredient());
        data.put(KEY_DES,recipe.get_description());
        db.update(TABLE_CONTACTS, data, KEY_ID+"=" + recipe.get_id(), null);
        db.close();

        return 0;
    }


    // Deleting single contact
    public boolean deleteContact(RecipeModal recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        int value = db.delete(TABLE_CONTACTS, KEY_ID + " = " + recipe.get_id(), null);
        if(value == 1){
            return true;
        }
        return false;
    }
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}