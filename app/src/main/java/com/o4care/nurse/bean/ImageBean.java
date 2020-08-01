package com.o4care.nurse.bean;

public class ImageBean {
    private String imagePath;

    private String imageName;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "imagePath='" + imagePath + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
