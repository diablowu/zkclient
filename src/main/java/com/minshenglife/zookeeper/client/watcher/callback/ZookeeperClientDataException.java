package com.minshenglife.zookeeper.client.watcher.callback;

import com.minshenglife.zookeeper.client.watcher.exception.ZookeeperClientException;

public class ZookeeperClientDataException extends ZookeeperClientException {

    /**
     * 
     */
    private static final long serialVersionUID = -610612482192113286L;
    
    private String path;
    
    public ZookeeperClientDataException(String path, String msg, Exception e) {
        super(msg,e);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
