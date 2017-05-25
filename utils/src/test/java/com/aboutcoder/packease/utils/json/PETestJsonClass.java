package com.aboutcoder.packease.utils.json;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/13/16 12:16 PM<br />
 * @description <br />
 */
public class PETestJsonClass {
    private Integer id;
    private int number;
    private String name;

    public void initTestJsonClass(Integer id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    /**
     * Get id <br>
     *
     * @return Returns the id. <br>
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set id <br>
     *
     * @param id The id to set. <br>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get number <br>
     *
     * @return Returns the number. <br>
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set number <br>
     *
     * @param number The number to set. <br>
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get name <br>
     *
     * @return Returns the name. <br>
     */
    public String getName() {
        return name;
    }

    /**
     * Set name <br>
     *
     * @param name The name to set. <br>
     */
    public void setName(String name) {
        this.name = name;
    }
}
