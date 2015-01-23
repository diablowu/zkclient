package com.minshenglife.zktest;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class DemoWatcher implements Watcher {
    private CountDownLatch c;
    public DemoWatcher(CountDownLatch c){
        this.c = c;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
        c.countDown();
    }

}
