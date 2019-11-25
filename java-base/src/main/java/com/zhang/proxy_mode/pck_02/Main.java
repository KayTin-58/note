package com.zhang.proxy_mode.pck_02;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

/**
 * description
 *
 * @author zb 2019/05/21 23:18
 */
public class Main {
    public static void main(String[] args) {
        DanShenDog danShenDog = (DanShenDog) new WuDiMeiPo().getInstance(new XiaoMingDanshenDog());
        System.out.println(danShenDog.getClass());
        danShenDog.DanshenDogFindF();

        DanShenDog danShenDog01 = (DanShenDog) new GameDear().getInstance(new XiaoMingDanshenDog());
        System.out.println(danShenDog01.getClass());
        danShenDog01.playGame();


        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy", new Class[] {danShenDog01.getClass()});
        try (FileOutputStream fos = new FileOutputStream(new File("D:\\$Proxy.class"))) {
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
