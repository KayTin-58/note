package com.zhang.FenBuShi_Base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * description
 *
 * @author zb 2019/06/17 14:04
 */
public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        person.setAddre("中国-湖北-随州");
        person.setName("张锡凯");
        person.setId(123);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(person);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            System.out.println(bytes.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
