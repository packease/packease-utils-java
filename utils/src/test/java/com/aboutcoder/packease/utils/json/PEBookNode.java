package com.aboutcoder.packease.utils.json;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 24/09/2016 4:57 PM<br />
 * @description <br />
 */
public class PEBookNode {
    private String category;
    private String author;
    private String title;
    private String isbn;
    private Double price;

    /**
     * Get category <br>
     *
     * @return Returns the category. <br>
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set category <br>
     *
     * @param category The category to set. <br>
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get author <br>
     *
     * @return Returns the author. <br>
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set author <br>
     *
     * @param author The author to set. <br>
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get title <br>
     *
     * @return Returns the title. <br>
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title <br>
     *
     * @param title The title to set. <br>
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get isbn <br>
     *
     * @return Returns the isbn. <br>
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set isbn <br>
     *
     * @param isbn The isbn to set. <br>
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get price <br>
     *
     * @return Returns the price. <br>
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set price <br>
     *
     * @param price The price to set. <br>
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}
