package com.zhang.Design_Patterns.prototype_mode.pck_02;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * description
 *
 * @author zb 2019/05/19 17:57
 */
@Setter
@Getter
public class Monkey implements Cloneable,Serializable{
    private String name;
    private Double weight;
    private Double height;
    private JinGuBang jinGuBang;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Monkey monkey = (Monkey)ois.readObject();
            monkey.jinGuBang = new JinGuBang();
            return monkey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
