package com.minshenglife.zktest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.Stat;

import com.minshenglife.zookeeper.client.watcher.MonitorPath;
import com.minshenglife.zookeeper.client.watcher.ZnodeDataMonitor;

public class Init implements ServletContextListener, Watcher {

   ZooKeeper zk;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String path = "/test/wx/token";
        String path1 = "/test/wx/ticket";
        String servers = "10.0.22.137:2181,10.0.22.137:2182,10.0.22.137:2183";
        
        
        ZnodeDataMonitor mon = new ZnodeDataMonitor(servers);
        
        byte[] data = mon.readData(path);
        System.out.println(path + " 当前数据 : "+ new String(data));
        
        byte[] data1 = mon.readData(path1);
        System.out.println(path1 + " 当前数据 : "+ new String(data1));
        
        
        
        List<MonitorPath> ms = new ArrayList<MonitorPath>();
        ms.add(new MonitorPath(path, new TestDataChangeCallback()));
        ms.add(new MonitorPath(path1, new TestData1ChangeCallback()));
        
        mon.monitAll(ms);
        
        

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

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
//            System.out.println("连接成功开始读取数据");
//            try {
//                String path = "/test/wx/token";
//                byte[] data = zk.getData(path,false,null);
//                
//                System.out.println(path + " 当前数据 : "+ new String(data));
//                
//                
//                
//                
//                
////                System.out.println("开始注册监听器");
//////                ZnodeDataMonitor mon = new ZnodeDataMonitor(this.zk);
//////                
//////                List<MonitorPath> ms = new ArrayList<MonitorPath>();
//////                
////                MonitorPath mp = new MonitorPath();
////                mp.setCallback(new TestDataChangeCallback());
////                mp.setPath(path);
//////                ms.add(mp);
//////                mon.monitAll(ms);
////                DataChangeWatcher.registCallback(mp);
////                DataChangeWatcher wc = new DataChangeWatcher(zk, null);
////                while(true){
//                    CountDownLatch c = new CountDownLatch(1);
//                    zk.getData("/test/wx/token",new DemoWatcher(c) , null);
//                    c.await();
////                }
//
//
//
//            } catch (Exception e) {
//                System.out.println("注册监听器异常");
//                e.printStackTrace();
//            }
//        }

    }
}
