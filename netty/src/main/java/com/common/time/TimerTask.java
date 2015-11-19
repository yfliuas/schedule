package com.common.time;

/**
 * 这个是调度的task执行方法
 * Created by TR on 2015/11/19.
 */
public class TimerTask implements Runnable {

    private String userName;
    private String taskName;

    public TimerTask(String userName, String taskName) {
        this.taskName = taskName;
        this.userName = userName;
    }

    /**
     * 这个先留着不着急写
     */
    public void run() {

    }
}
