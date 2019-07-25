package com.ssh;

/**
 * description
 *
 * @author zb 2019/07/18 10:21
 */
public class MainSSH {
    static final String IP ="192.168.230.140";
    static final String USER_NAME ="root";
    static final String PASSWORD ="root";
    public static void main(String[] args) {
        paramTest();
    }


    static void paramTest() {
        String cmd ="bash /root/ssh/parameter.sh 1 2 3";
        String str = RemoteCommandUtil.execute(RemoteCommandUtil.login(IP,USER_NAME,PASSWORD),cmd);
        System.out.println(str);
    }

    static void spark() {
        String cmd = "/root/app/spark-2.4.3/bin/spark-shell";
        String str = RemoteCommandUtil.execute(RemoteCommandUtil.login(IP,USER_NAME,PASSWORD),cmd);
        System.out.println(str);
    }



}
