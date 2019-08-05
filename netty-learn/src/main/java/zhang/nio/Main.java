package zhang.nio;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.util.Random;

/**
 * description
 *
 * @author zb 2019/08/03 15:53
 */
@Slf4j
public class Main {
    public static void main(String[] args) {

        /**
         *IntBuffer(int mark, int pos, int lim, int cap,   // package-private
         *                  int[] hb, int offset)
         *     {
         *         super(mark, pos, lim, cap);
         *         this.hb = hb;
         *         this.offset = offset;
         *     }
         *
         * Buffer(int mark, int pos, int lim, int cap)
         */
        IntBuffer buffer = IntBuffer.allocate(10);
        Random random = new SecureRandom();
        for(int i = 0; i < buffer.capacity(); i++) {
            buffer.put(random.nextInt(20));
        }

        /**
         * public final Buffer flip() {
         *         limit = position;
         *         position = 0;
         *         mark = -1;
         *         return this;
         *     }
         * 读写切换
         */
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println( buffer.get());
        }

        /**
         * 并没有去清除buffer数组里面额内容，只是会覆盖
         *  public final Buffer clear() {
         *         position = 0;
         *         limit = capacity;
         *         mark = -1;
         *         return this;
         *     }
         */
        buffer.clear();


    }


    @SuppressWarnings("all")
    static void learnDirectBuffer() {
        /**
         * Used only by direct buffers
         * NOTE: hoisted here for speed in JNI GetDirectBufferAddress
         * long address;
         * unsafe.setMemory(base, size, (byte) 0);
         * base = unsafe.allocateMemory(size);
         */
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
    }
}
