package com.common.time;

/**
 * ����ǵ��ȵ�taskִ�з���
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
     * ��������Ų��ż�д
     */
    public void run() {

    }
}
