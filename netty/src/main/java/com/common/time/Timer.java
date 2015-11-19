package com.common.time;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要写两个接口
 * 1、这个类是一个单例模式
 * 2、只要两个方法
 *       addTask
 *       dropTask
 *       需要重载一个多个
 * Created by TR on 2015/11/19.
 */
public class Timer {
    private static Timer timer = null;

    private Scheduler scheduler = null;

    private Timer() {
        scheduler = new Scheduler();
    }

    public synchronized static Timer getInstance() {
        if(timer == null) {
            timer = new Timer();
        }
        return timer;
    }

    /**
     * 添加一个注册信息
     * @param scheduleInfo
     * @param timerTask
     * @return
     */
    public String addTask(String scheduleInfo, TimerTask timerTask) {
        return scheduler.schedule(scheduleInfo, timerTask);
    }

    /**
     * 添加多个注册信息
     * @param scheduleInfoList
     * @param timerTask
     * @return
     */
    public List<String> addTask(List<String> scheduleInfoList, TimerTask timerTask) {
        List<String> ids = new ArrayList<String>();
        for(int i=0;i < scheduleInfoList.size(); i++) {
            ids.add(scheduler.schedule(scheduleInfoList.get(i), timerTask));
        }
        return ids;
    }

    /**
     * 注册信息
     * @param username
     *       用户名
     * @param taskName
     *       任务名
     * @return
     *       这个任务的id
     */
    @Deprecated
    public String addTask(String username, String taskName, String scheduleInfo) {
        return scheduler.schedule(scheduleInfo, new TimerTask(username, taskName));
    }

    /**
     * 删除一个任务
     * @param taskId
     */
    public void dropTask(String taskId) {
        scheduler.deschedule(taskId);
    }

    /**
     * 删除多个个任务
     * @param taskIds
     */
    public void dropTask(List<String> taskIds) {
        for(String id : taskIds) {
            scheduler.deschedule(id);
        }
    }
}
