package com.saaweel;

public class Film {
    private String title;
    private String director;
    private int year;

    private String image;

    public Film(String title, String director, int year, String image) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
