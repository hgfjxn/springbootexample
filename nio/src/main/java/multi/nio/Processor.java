package multi.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Processor implements Runnable {
    private final Logger log = LoggerFactory.getLogger(Processor.class);

    private final static int DEFAULT_BUFFER_SIZE = 1024;
    private final LinkedBlockingDeque<SelectionKey> requests;

    public Processor(LinkedBlockingDeque<SelectionKey> requests) {
        this.requests = requests;
    }

    @Override
    public void run() {
        log.debug("start processor @" + Thread.currentThread().getName());
        while (true) {
            SelectionKey key = null;
            try {
                if ((key = requests.poll(1, TimeUnit.SECONDS)) != null) {
                    log.debug("process data...");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    try {
                        ByteBuffer byteBuffer = read(socketChannel);
                        if (byteBuffer == null) {
                            log.info("connection closed by " + socketChannel.getRemoteAddress());
                        } else {
                            String request = Charset.forName("utf-8").decode(byteBuffer).toString();
                            log.info("request data [" + System.currentTimeMillis() + "]: " + request);

                            //读取过数据后，再次关注连接，期待获取数据
                            key.interestOps(SelectionKey.OP_READ);
                            log.info("interest of OP_READ");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static ByteBuffer read(SocketChannel socketChannel) throws IOException {
        byte[] data = new byte[DEFAULT_BUFFER_SIZE];
        ByteBuffer tmp = ByteBuffer.allocate(1024);
        tmp.clear();
        int size = 0;
        int offset = 0;
        while ((size = socketChannel.read(tmp)) > 0) {

            if ((offset + size) > data.length) {
                data = grow(data);
            }
            tmp.flip();
            tmp.get(data, offset, size);
            offset += size;

            tmp.clear();
        }
        //size = -1时，表示连接断开，不能放在while循环内，因为进入while循环的条件是size > 0
        if (size == -1) {
            return null;
        }

        return ByteBuffer.wrap(data, 0, offset);
    }

    private static byte[] grow(byte[] data) {
        int to = data.length > (Integer.MAX_VALUE / 2 + 1) ? Integer.MAX_VALUE : 2 * data.length;
        byte[] newData = new byte[to];
        System.arraycopy(data, 0, newData, 0, data.length);
        return newData;
    }

    private static byte[] grow(byte[] data, int increment) {
        int to = data.length > (Integer.MAX_VALUE - increment) ? Integer.MAX_VALUE : increment + data.length;
        byte[] newData = new byte[to];
        System.arraycopy(data, 0, newData, 0, data.length);
        return newData;
    }
}
