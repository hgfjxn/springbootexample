package multi.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ConnectionHandler implements Runnable {
    private final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);
    private final LinkedBlockingDeque<SelectionKey> connections;
    private final Selector selector;

    public ConnectionHandler(LinkedBlockingDeque<SelectionKey> connections, Selector selector) {
        this.connections = connections;
        this.selector = selector;
    }

    @Override
    public void run() {
        log.info("start connection handler @" + Thread.currentThread().getName());
        SelectionKey key = null;
        while (true) {
            try {
                //使用带事件的阻塞获取方式，防止一直占用CPU
                if ((key = connections.poll(2, TimeUnit.SECONDS)) != null) {
                    log.debug("binding connection");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if (socketChannel != null) {
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        log.debug("connected [ " + socketChannel.getLocalAddress() + " - " + socketChannel.getRemoteAddress() + " ]");
                        //恢复ServerSocketChannel的关注
                        key.interestOps(SelectionKey.OP_ACCEPT);
                        log.info("interest Ops of OP_ACCEPT");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
