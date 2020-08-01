package com.o4care.nurse.bean;

public class ImageBean {
    private String imagePath;

    private String imageName;

    private int progress;

    private long imgSize;

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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getImgSize() {
        return imgSize;
    }

    public void setImgSize(long imgSize) {
        this.imgSize = imgSize;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "imagePath='" + imagePath + '\'' +
                ", imageName='" + imageName + '\'' +
                ", progress=" + progress +
                ", imgSize=" + imgSize +
                '}';
    }
}
