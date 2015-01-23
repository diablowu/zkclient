package com.minshenglife.zookeeper.client.watcher;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.ZooKeeper;

public class MonitorRunner implements Callable<Integer> {

    private ZooKeeper zk;
    
    private MonitorPath mp;
    
    public MonitorRunner(ZooKeeper zk, MonitorPath mp){
        this.zk = zk;
        this.mp = mp;
        
    }
    
    @Override
    public Integer call() throws Exception {
        DataChangeWatcher.registCallback(mp);
        while(true){
            CountDownLatch latch = new CountDownLatch(1);
            DataChangeWatcher wc = new DataChangeWatcher(zk, latch);
            zk.getData(mp.getPath(), wc, null);
            latch.await();
        }
    }

}
