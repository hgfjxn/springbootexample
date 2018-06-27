package simple.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NioClient {
    private final static int N = 1;
    private Selector selector;
    private Executor executor;

    public void init() throws IOException {
        selector = Selector.open();
        executor = Executors.newFixedThreadPool(N);
    }

    public void work() throws IOException {
        for (int i = 0; i < N; i++) {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 9999));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

        int count = 0;

        while (true) {
            int num = selector.select(1000);
            if (num > 0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    if (key.isConnectable()) {
                        System.out.println(socketChannel.hashCode() + ": is ready to connect server @ " + System.currentTimeMillis());
                        if (socketChannel.isConnectionPending()) {
                            if (socketChannel.finishConnect()) {
                                System.out.println(socketChannel.hashCode() + " write... @ " + System.currentTimeMillis());
                                socketChannel.register(selector, SelectionKey.OP_READ);
                                socketChannel.write(ByteBuffer.wrap(String.valueOf(++count).getBytes()));
                            }
                        }
                    } else if (key.isReadable()) {
                        System.out.println(socketChannel.hashCode() + ": prepare to read.");
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        int x = socketChannel.read(buffer);
                        if (x > 0) {
                            //此处必须flip才能读取数据。
                            buffer.flip();
                            byte[] bytes = new byte[buffer.limit()];
                            buffer.get(bytes, 0, buffer.limit());
                            count = Integer.parseInt(new String(bytes));
                            System.out.println("buffer position:" + buffer.position() + ", limit: " + buffer.limit());
                            System.out.println(socketChannel.hashCode() + " get : " + count);
                            key.interestOps(SelectionKey.OP_WRITE);
                        } else {
                            socketChannel.close();
                            key.cancel();
                        }
                    } else if (key.isWritable()) {
                        System.out.println(socketChannel.hashCode() + " write : " + ++count);
                        ByteBuffer buffer = ByteBuffer.wrap(String.valueOf(count).getBytes());
                        socketChannel.write(buffer);

                        key.interestOps(SelectionKey.OP_READ);
                    } else {
                        System.out.println(socketChannel.hashCode() + " : ooo... " + key.readyOps());
                        key.cancel();
                    }
                    it.remove();
                }
            }

            if (count == 50) {
                System.out.println("outting...");
                break;
            }
        }
    }

    public void close() throws IOException {
        Iterator<SelectionKey> it = selector.keys().iterator();
        while (it.hasNext()) {
            SelectionKey key = it.next();
            key.channel().close();
        }

        selector.close();
    }

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.init();
        client.work();
        client.close();
    }
}
