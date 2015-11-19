package com.common.time;

import it.sauronsoftware.cron4j.Scheduler;

/**
 * Created by TR on 2015/11/18.
 */
public class StudyCrontab {

    public static void main(String[] args) {
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.
        String id = s.schedule("* * * * *", new Runnable() {
            public void run() {
                System.out.println("Another minute ticked away...");
            }
        });

        System.out.println(id);
        s.deschedule(id);
        // Starts the scheduler.
        s.start();
        // Stops the scheduler.
        s.stop();
    }

    public void method() {
        System.out.println("lalala");
    }

}
