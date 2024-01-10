package com.saaweel;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private boolean loaned;

    public Book(int id, String title, String author, String category, int loaned) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.loaned = loaned == 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isLoaned() {
        return this.loaned;
    }

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }
}
