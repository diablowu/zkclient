package com.minshenglife.zookeeper.client.watcher.exception;

public class ZookeeperClientException extends RuntimeException {
    
    

    
    /**
     * 
     */
    private static final long serialVersionUID = 280211798648935482L;
    protected String msg;
    
    
    public ZookeeperClientException(String msg, Exception e) {
        this.msg = msg;
    }




    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

}
