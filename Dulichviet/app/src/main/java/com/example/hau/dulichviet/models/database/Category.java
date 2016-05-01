package com.example.hau.dulichviet.models.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by TRUNGHAU on 5/1/2016.
 */
@Table(name ="Category")
public class Category extends Model {

    @Column(name = "_id")
    public String id;
    @Column(name = "name")
    public String name;
    @Column(name = "image_id")
    public String image_id;

    public static List<Category> all() {
        return new Select().from(Category.class).execute();
    }

    public static void deleteAllCategory() {
        new Delete().from(Category.class).execute();
    }


}
