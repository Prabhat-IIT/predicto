package com.project.nikhil.predicto;

/**
 * Created by nikhil on 9/8/17.
 */

public class grid_object {
    private int image;
    private String name;
    private Class aclass;

    public grid_object(int image, String name,Class bclass) {
        this.image = image;
        this.name = name;
        aclass=bclass;
    }

    public Class getAclass() {

        return aclass;
    }

    public void setAclass(Class aclass) {
        this.aclass = aclass;
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
