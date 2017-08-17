package com.project.nikhil.predicto;

/**
 * Created by nikhil on 9/8/17.
 */

public class grid_object {
    private int image;
    private String name;
    private String name_hindi;
    private Class aclass;
    private cordinates cordinates;

    public grid_object(int image, String name, String name_hindi, Class aclass, com.project.nikhil.predicto.cordinates cordinates) {
        this.image = image;
        this.name = name;
        this.name_hindi = name_hindi;
        this.aclass = aclass;
        this.cordinates = cordinates;
    }

    public com.project.nikhil.predicto.cordinates getCordinates() {
        return cordinates;
    }

    public void setCordinates(com.project.nikhil.predicto.cordinates cordinates) {
        this.cordinates = cordinates;
    }

    public grid_object(int image, String name, String name_hindi,Class bclass) {
        this.image = image;
        this.name = name;
        aclass=bclass;
        this.name_hindi=name_hindi;
    }

    public String getName_hindi() {
        return name_hindi;
    }

    public void setName_hindi(String name_hindi) {
        this.name_hindi = name_hindi;
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
