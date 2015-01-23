package com.minshenglife.zookeeper.client.watcher.callback;

import java.util.List;

import com.minshenglife.zookeeper.client.watcher.NodeChangeCallback;

/**
 * znode数据改变适配器
 * @author Diablo Wu
 * @Date 2015年1月22日 上午9:57:04
 */
public abstract class DataChangeCallbackAdpater extends NodeChangeCallback {

    @Override
    public abstract void onDataChange(String path, byte[] data);

    @Override
    public void onCreate(String path, byte[] data) {
        // nothing to do
    }

    @Override
    public void onDelete(String path, byte[] data) {
        // nothing to do

    }

    @Override
    public void onChildrenChanged(String path, byte[] data,
            List<String> children) {
        // nothing to do
    }

}
