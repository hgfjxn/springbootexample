package multi.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class RequestHandler implements Runnable {
    private final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private final LinkedBlockingDeque<SelectionKey> connections;
    private final LinkedBlockingDeque<SelectionKey> requests;
    private Selector selector;
    private Executor connectionHandlerProssor = Executors.newSingleThreadExecutor();

    public RequestHandler(LinkedBlockingDeque<SelectionKey> connections, LinkedBlockingDeque<SelectionKey> requests) {
        this.connections = connections;
        this.requests = requests;
    }

    @Override
    public void run() {
        log.info("start request handler");
        try {
            selector = Selector.open();
            //启动后台连接处理
            connectionHandlerProssor.execute(new ConnectionHandler(connections, selector));
            while (true) {
                //设定selector的阻塞，防止一直占用CPU
                int num = selector.select(1000);

                //有关注的SelectionKey准备就绪
                if (num > 0) {
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();

                        if (key.isReadable()) {
                            //取消请求SocketChannel的读关注，在读取数据处理后继续关注。
                            key.interestOps(0);
                            log.info("uninterest of OP_READ untill done processing");


                            log.debug("read data and enqueue");
                            requests.offer(key);
                        } else if (!key.isValid()) {
                            log.info("key is invalid");
                            key.cancel();
                        }

                        //一定要移除已经处理过的，不然会重复处理。
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //对外提供selector唤醒的机制
    public void wakeup() {
        selector.wakeup();
    }

    public void close() throws IOException {

        Set<SelectionKey> selectionKeys = selector.keys();
        if (selectionKeys != null && selectionKeys.size() > 0) {
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                it.next().cancel();
                it.remove();
            }
        }
        //key.cancle只有在selector select后才能起作用。
        selector.selectNow();
        selector.close();
    }
}
