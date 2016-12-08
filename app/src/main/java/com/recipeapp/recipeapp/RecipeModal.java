package com.recipeapp.recipeapp;

/**
 * Created by amadzahid on 27/11/2016.
 */
public class RecipeModal {

    int _id;
    String _name;
    int _image;
    String _ingredient;
    String _description;
    String _summary;
    int _favorite;



    public RecipeModal() {
    }

    public RecipeModal(int _id, String _name, String _summary, int _image, String _ingredient, String _description,int _favorite) {
        this._id = _id;
        this._name = _name;
        this._summary = _summary;
        this._image = _image;
        this._ingredient = _ingredient;
        this._description = _description;
        this._favorite = _favorite;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_summary() {
        return _summary;
    }

    public void set_summary(String _summary) {
        this._summary = _summary;
    }

    public int get_image() {
        return _image;
    }

    public void set_image(int _image) {
        this._image = _image;
    }

    public String get_ingredient() {
        return _ingredient;
    }

    public void set_ingredient(String _ingredient) {
        this._ingredient = _ingredient;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public int get_favorite() {
        return _favorite;
    }

    public void set_favorite(int _favorite) {
        this._favorite = _favorite;
    }
}
