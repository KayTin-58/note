package com.zhang;

/**
 * description
 *
 * @author zb 2019/05/21 15:16
 */
public class Test {
    public static void main(String[] args) {
        String P_UserId = "zhangsan";
        String P_OrderId = "201611101001";
        String P_CardId = "6217230222222222222";
        String P_CardPass = "ICBC";
        String P_FaceValue = "战士";
        String P_ChannelId = "001";
        String P_Subject = "转账";
        String P_Price = "100.0";
        String P_Quantity = "ccc";
        String P_Description = "hanson";
        String P_Notic = "hanson";
        String P_Result_URL = "hanson";
        String P_Notify_URL = "hanson";
        String P_PostKey = "hanson";
        String postUrl = String.format(
                "?P_UserId=%s&P_OrderId=%s&P_CardId=%s&P_CardPass=%s&P_FaceValue=%s&P_ChannelId=%s&P_Subject=%s&P_Price=%s"
                , P_UserId
                , P_OrderId
                , P_CardId
                , P_CardPass
                , P_FaceValue
                , P_ChannelId
                , P_Subject
                , P_Price
                );
        System.out.println(postUrl);

        String exportJsonApi ="1234";
        String serviceName = "HDSP";
        boolean checkPoliciesExists = true;

        String s = String.format("%s?serviceName=%s&checkPoliciesExists=%b",exportJsonApi, serviceName, checkPoliciesExists);
        System.out.println(s);
    }

}
