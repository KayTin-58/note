package com.zhang.netty.wechat.utils;

import com.zhang.netty.wechat.constant.AttributeEnum;
import io.netty.channel.Channel;
import io.netty.util.Attribute;


/**
 * description
 *
 * @author zb 2019/07/04 19:45
 */
public class LoginUtils {

   public static void markLogin(Channel channel){
       channel.attr(AttributeEnum.IS_LOGIN.getAttributeKey()).set(true);
   }

   public static boolean hasLogin(Channel channel) {
       Attribute<Boolean> loginAttr = channel.attr(AttributeEnum.IS_LOGIN.getAttributeKey());
       return  loginAttr.get() != null;
   }
}
