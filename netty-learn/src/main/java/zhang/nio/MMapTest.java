package zhang.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
public class MMapTest {

    static File file= new File("./test.txt");
    static ByteBuffer buffer;

    static int fileSize = 8 * 1024 * 1024;
    static boolean del = true;

    public static void main(String[] args) {
        init(1);
        deleteFile();
        int[] sizes = {128,256,512,4096,8192,1024*16,1024*32,1024*128,1024*512};
        try {
            for (int size : sizes) {
                testDBChannel(size);
                testMappedByteBuffer(size);
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init(int size) {
        buffer = ByteBuffer.allocateDirect(size);
    }

    private static void deleteFile() {
        file.delete();
    }

    private static void testDBChannel(int size) throws IOException {
        init(size);
        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        FileChannel channel = rw.getChannel();
        int writeSize = 0;
        Long start = System.currentTimeMillis();
            while (writeSize < fileSize) {
            buffer.clear();
            buffer.put(new byte[size]);
            buffer.flip();
            channel.position(writeSize);
            channel.write(buffer);
            channel.force(false);
            writeSize += size;
        }
        //channel.force(false);
        System.out.println("DirectBuffer + FileChannel write " + size + " bytes every time cost: " + (System.currentTimeMillis() - start) + "ms");
        if(del) {
            deleteFile();
        }


    }

    private static void testMappedByteBuffer(int size) throws IOException {
        init(size);
        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        FileChannel channel = rw.getChannel();
        //将磁盘文件映射到内存中
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
        int writeSize = 0;
        Long start = System.currentTimeMillis();
        while (writeSize < fileSize) {
            //这里的操作实际操作的是内存 但是最终是作用到了file上面
            map.put(new byte[size]);
            //Forces any changes made to this buffer's content to be written to the
            // storage device containing the mapped file.
            map.force();
            writeSize += size;
        }
        //map.force();
        System.out.println("MappedByteBuffer write " + size + " bytes every time cost: " + (System.currentTimeMillis() - start) + "ms");
        if(del) {
            deleteFile();
        }

    }
}