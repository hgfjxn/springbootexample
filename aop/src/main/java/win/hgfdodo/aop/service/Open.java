package win.hgfdodo.aop.service;

import org.springframework.stereotype.Service;

@Service
public class Open {
    public void openService() {
        System.out.println("opening the door");
    }

    public void delock(){
        System.out.println("open the lock");
    }
}
