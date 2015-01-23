package com.minshenglife.zookeeper.client.watcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

import com.minshenglife.zookeeper.client.watcher.callback.ZookeeperClientDataException;
import com.minshenglife.zookeeper.client.watcher.exception.ZookeeperClientException;

/**
 * 节点数据变更监视器
 * @author Diablo Wu
 * @Date 2015年1月22日 上午11:19:44
 */
public class ZnodeDataMonitor implements Watcher {
    
//    private static final Logger LOGGER = new
    
    private ZooKeeper zk;
    
    private ExecutorService taskPool;
    
    private int MAX_MONITOR_SIZE = 30;

    public ZnodeDataMonitor(ZooKeeper zk){
        this.zk = zk;
    }
    
    public ZnodeDataMonitor(String servers){
        try {
            zk = new ZooKeeper(servers, 10000, this);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(!zk.getState().equals(States.CONNECTED)){
            while(true){
                if(zk.getState().equals(States.CONNECTED)){
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    public byte[] readData(String path){
       
        byte[] data = null;
        try {
           data = zk.getData(path, null, null);
        } catch (Exception e) {
            throw new ZookeeperClientDataException(path, "读取数据时发生异常", e);
        } 
        
        return data;
    }
    
    
    public void monitAll(List<MonitorPath> monitors){
        if(MAX_MONITOR_SIZE < monitors.size()){
            throw new IllegalArgumentException("最多只能监听30个znode");
        }
        this.taskPool = Executors.newFixedThreadPool(monitors.size());
        
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>(monitors.size());
        
        for (MonitorPath mp  : monitors) {
            tasks.add(new MonitorRunner(zk,mp));
        }
        
        try {
            taskPool.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new ZookeeperClientException("任务调度失败", e);
        }
    }
    
    
    @Override
    public void process(WatchedEvent event) {
//        System.out.println("***************************");
//        System.out.println(event.getType());
//        System.out.println(event.getState());
//        System.out.println("***************************");

        if (event.getState() == KeeperState.SyncConnected) {
//            System.out.println(zk.getState());
        }

    }
    



}
