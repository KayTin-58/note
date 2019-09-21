package zhang.wechat.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import zhang.wechat.constant.AttributeEnum;
import zhang.wechat.packet.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * description
 *
 * @author zb 2019/07/04 19:45
 */
public class LoginUtils {

    /**
     * 容器存储
     */
    private static Map<String, Channel> map = new ConcurrentHashMap<>(100);

    public static void markLogin(Channel channel) {
        channel.attr(AttributeEnum.IS_LOGIN.getAttributeKey()).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(AttributeEnum.IS_LOGIN.getAttributeKey());
        return loginAttr.get() != null;
    }


    public static void bindSession(Session session, Channel channel) {
        map.put(session.getUserId(),channel);
        channel.attr(AttributeEnum.SESSION.getAttributeKey()).set(session);
    }


    public static void unBindSession(Channel channel) {
        if(hasLogin(channel)) {
            map.remove(getSession(channel).getUserId());
            channel.attr(AttributeEnum.SESSION.getAttributeKey()).set(null);
        }
    }

    /**
     *通过Channel获取Session
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel) {
        return (Session)channel.attr(AttributeEnum.SESSION.getAttributeKey()).get();
    }

    /**
     * 获取Channel
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId) {
        return map.get(userId);
    }
}
