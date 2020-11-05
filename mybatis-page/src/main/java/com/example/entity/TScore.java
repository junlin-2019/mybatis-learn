package com.example.entity;

import java.io.Serializable;

/**
 * (TScore)实体类
 *
 * @author makejava
 * @since 2020-10-06 14:45:26
 */
public class TScore implements Serializable {
    private static final long serialVersionUID = 699613342424855404L;

    private String id;

    private Integer score;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}