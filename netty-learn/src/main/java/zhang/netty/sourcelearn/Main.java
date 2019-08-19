package zhang.netty.sourcelearn;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2018/06/07 13:48
 */
public class Main {

    public static void main(String[] args) {
        int a = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(a);
    }
}
