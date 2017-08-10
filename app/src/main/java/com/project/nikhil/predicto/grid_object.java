package com.project.nikhil.predicto;

/**
 * Created by nikhil on 9/8/17.
 */

public class grid_object {
    private int image;
    private String name;

    public grid_object(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
