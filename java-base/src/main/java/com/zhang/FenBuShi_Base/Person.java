package com.zhang.FenBuShi_Base;

import java.io.Serializable;

/**
 * description
 *
 * @author zb 2019/06/17 14:03
 */
public class Person implements Serializable {


    private String name;
   private Integer id;
   private String addre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddre() {
        return addre;
    }

    public void setAddre(String addre) {
        this.addre = addre;
    }
}
