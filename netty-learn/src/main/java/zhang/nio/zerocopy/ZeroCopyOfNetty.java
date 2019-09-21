package zhang.nio.zerocopy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.jboss.netty.buffer.ChannelBuffers;


/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/9/5
 */
public class ZeroCopyOfNetty {
    public static void main(String[] args) {
        /**
         *    int readerIndex;
         *    int writerIndex;
         *    int markedReaderIndex;
         *    int markedWriterIndex;
         *    List<CompositeByteBuf.Component> components; // 所有子 byteBuf
         *
         */
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();


        /**
         * 返回虚拟  byteBuf
         */
        ByteBuf byteBuf = compositeByteBuf.component(1);

    }
}
