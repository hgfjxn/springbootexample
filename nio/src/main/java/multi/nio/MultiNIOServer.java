package multi.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class MultiNIOServer {
    private final Logger log = LoggerFactory.getLogger(MultiNIOServer.class);

    public final static int CORE_NUM = 3;
    private LinkedBlockingDeque<SelectionKey> connections = new LinkedBlockingDeque<>();
    private LinkedBlockingDeque<SelectionKey> requests = new LinkedBlockingDeque<>();
    private List<RequestHandler> requestHandlers = new ArrayList<>();
    private ExecutorService connectionHandlePool = Executors.newFixedThreadPool(CORE_NUM);
    private ExecutorService processorPool = Executors.newFixedThreadPool(CORE_NUM);
    private int connectionId = 0;


    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public void init() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));

        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void listen() throws IOException {

        for (int i = 0; i < CORE_NUM; i++) {
            RequestHandler requestHandler = new RequestHandler(connections, requests);
            requestHandlers.add(requestHandler);
            connectionHandlePool.execute(requestHandler);

            Processor processor = new Processor(requests);
            processorPool.execute(processor);
        }

        log.info("server started ... ");

        while (true) {
            int num = selector.select();
            if (num > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        log.info("uninterest OP_ACCEPT of serverSocketChannel");
                        //取消key绑定的serversocketchannel 的OP_ACCEPT关注，在connection建立后，关注OP_READ
                        key.interestOps(0);
                        log.debug("get connection, add to queue");
                        connections.offer(key);
                        connectionId++;

                        log.debug("wake up a request handler");
                        int size = requestHandlers.size();
                        requestHandlers.get((connectionId + 1) % size).wakeup();
                    }

                    it.remove();
                }
            }
        }
    }

    public void close() {
        connectionHandlePool.shutdown();
        processorPool.shutdown();
        try {
            selector.selectNow();
            selector.close();
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MultiNIOServer multiNIOServer = new MultiNIOServer();
        try {
            multiNIOServer.init();
            multiNIOServer.listen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
