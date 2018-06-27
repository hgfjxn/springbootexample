package win.hgfdodo.aop.service;

import org.springframework.stereotype.Service;

@Service
public class Close {

    public void closeService() {

        System.out.println("closing the door");
    }
}
