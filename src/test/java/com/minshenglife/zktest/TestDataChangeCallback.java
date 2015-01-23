package com.minshenglife.zktest;

import com.minshenglife.zookeeper.client.watcher.callback.DataChangeCallbackAdpater;

public class TestDataChangeCallback extends DataChangeCallbackAdpater {

    @Override
    public void onDataChange(String path, byte[] data) {
        System.out.println("TestDataChangeCallback");
        System.out.println(path + "路径发生数据改变:");
        System.out.println("新数据是:"+new String(data));
    }

}
