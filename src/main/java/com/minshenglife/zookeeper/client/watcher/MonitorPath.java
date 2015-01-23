package com.minshenglife.zookeeper.client.watcher;

public class MonitorPath {

    private String path;
    private NodeChangeCallback callback;

    public MonitorPath(String path, NodeChangeCallback callback) {
        super();
        this.path = path;
        this.callback = callback;
    }
    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public NodeChangeCallback getCallback() {
        return callback;
    }

    public void setCallback(NodeChangeCallback callback) {
        this.callback = callback;
    }
}
