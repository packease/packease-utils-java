package com.aboutcoder.packease.utils.json;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 24/09/2016 5:02 PM<br />
 * @description <br />
 */
public class PEStoreNode<T> {
    private T book;

    /**
     * Get book <br>
     *
     * @return Returns the book. <br>
     */
    public T getBook() {
        return book;
    }

    /**
     * Set book <br>
     *
     * @param book The book to set. <br>
     */
    public void setBook(T book) {
        this.book = book;
    }
}
