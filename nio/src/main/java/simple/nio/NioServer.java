package simple.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public void init() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void work() throws IOException {
        if (!serverSocketChannel.isOpen()) {
            throw new RuntimeException("server socket not open!");
        }
        System.out.println("server started");
        int count = -1;
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    System.out.println("prepare to accept connection...");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    if (socketChannel != null) {
                        System.out.println(socketChannel.hashCode() + " accepted @ " + System.currentTimeMillis());
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                } else if (key.isReadable()) {
                    System.out.println("socket channel readable @ " + System.currentTimeMillis());
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(32);
                    buffer.clear();
                    int x = socketChannel.read(buffer);
                    if (x > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.limit()];
                        buffer.get(bytes, 0, buffer.limit());
                        count = Integer.parseInt(new String(bytes));

                        System.out.println(socketChannel.hashCode() + " get: " + count);
                        count++;
                        System.out.println("socket channel writable @ " + System.currentTimeMillis());
                        socketChannel.write(ByteBuffer.wrap(String.valueOf(count).getBytes()));
//                    key.interestOps(SelectionKey.OP_READ);
                        System.out.println(socketChannel.hashCode() + " write: " + count);
                    } else if (x < 0) {
                        socketChannel.close();
                    }

                } else {
                    System.out.println("key: " + key.interestOps() + ", key channel hash code:" + key.channel().hashCode());
                }
                it.remove();
            }
        }


    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.init();
        nioServer.work();
    }
}
