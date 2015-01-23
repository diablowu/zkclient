package com.minshenglife.zookeeper.client.watcher;

import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;


/**
 * znode数据变动回调方法
 * @author Diablo Wu
 * @Date 2015年1月22日 上午9:32:54
 */
public abstract class NodeChangeCallback {
    
    protected ZooKeeper zk = null;
    
    public NodeChangeCallback(){
        
    }
    
    public NodeChangeCallback(ZooKeeper zk){
        this.zk = zk;
    }
    
    public void on(String path,byte[] data,List<String> children,WatchedEvent event){
        switch (event.getType()) {
            case NodeCreated:
                onCreate(path, data);
                break;
            case NodeDeleted:
                onDelete(path, data);
                break;
            case NodeDataChanged:
                onDataChange(path, data);
                break;
            case NodeChildrenChanged:
                onChildrenChanged(path, data, children);
            case None:
                break;
            default:
                break;
        }
    }
    
    /**
     * 数据变动
     * @param path
     * @param data
     */
    public abstract void onDataChange(String path,byte[] data);
    
    /**
     * 节点创建
     * @param path
     * @param data
     */
    public abstract void onCreate(String path,byte[] data); 
    
    /**
     * 节点删除
     * @param path
     * @param data
     */
    public abstract void onDelete(String path,byte[] data);
    
    /**
     * 子元素发生改变
     * @param path
     * @param data
     * @param children 变动后的节点信息
     */
    public abstract void onChildrenChanged(String path,byte[] data,List<String> children);

}
