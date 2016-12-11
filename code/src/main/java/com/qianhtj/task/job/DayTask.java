package com.qianhtj.task.job;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

/**
 * Created by lixl on 2016/12/8.
 */
public class DayTask extends Thread{

    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    private static Logger log = Logger.getLogger("提示信息");

    private boolean status = false;

    @Override
    public synchronized void start() {
        status = true;
        super.start();
    }

    public void shutdown(){
        status = false;
    }

    @Override
    public void run() {
        try{
            Timer timer = new Timer();
            //首次执行
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 1); //凌晨1点
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date date=calendar.getTime(); //第一次执行定时任务的时间
            //如果第一次执行定时任务的时间 小于当前的时间
            //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
            if (date.before(new Date())) {
                date = Task.addDay(date, 1);
            }
            //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
            Task task = new Task();
            timer.schedule(task, date ,PERIOD_DAY);
            while(status){
                task.printProgress();
                Thread.sleep(60*1000);
                log.info(Task.processInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
