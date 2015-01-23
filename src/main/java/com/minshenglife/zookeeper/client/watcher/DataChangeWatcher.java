package com.minshenglife.zookeeper.client.watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

import com.minshenglife.zookeeper.client.watcher.callback.ZookeeperClientDataException;


/**
 * 数据监视器
 * @author Diablo Wu
 * @Date 2015年1月22日 上午9:41:54
 */
public class DataChangeWatcher implements Watcher {
    private static final Map<String,NodeChangeCallback> CALLBACK_MAP = new HashMap<String, NodeChangeCallback>();
    
    /**
     * 注册call处理器
     * @param path
     * @param callback
     */
    public static void registCallback(MonitorPath mon) {
        CALLBACK_MAP.put(mon.getPath(), mon.getCallback());
    }
    
    private ZooKeeper zk;
    private CountDownLatch latch;
    
    
    
    public DataChangeWatcher(ZooKeeper zk, CountDownLatch latch){
        this.zk = zk;
        this.latch = latch;
    }
    

    /* (non-Javadoc)
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event) {
        String path = event.getPath();
        EventType eventType = event.getType();
        if(!eventType.equals(EventType.None) && path!=null){
            byte[] data = null;
            List<String> children = new ArrayList<String>(0);
            try {
                if(event.getType().equals(EventType.NodeDataChanged)){
                    data = zk.getData(path, false, null);
                }else if(event.getType().equals(EventType.NodeChildrenChanged)){
                    children.addAll(zk.getChildren(path, null));
                }
            } catch (Exception e) {
                throw new ZookeeperClientDataException(path, "获取数据时出错", e);
            }
            CALLBACK_MAP.get(path).on(event.getPath(), data, children, event);
        }
        latch.countDown();
    }

}
