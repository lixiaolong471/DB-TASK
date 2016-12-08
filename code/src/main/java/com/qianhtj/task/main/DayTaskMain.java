package com.qianhtj.task.main;



import com.qianhtj.task.job.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class DayTaskMain {
	
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

	/**            
	 * @param args
	 */
	public static void main(String[] args){
		try{
			Timer timer = new Timer();
			//首次执行
			final Task task = new Task();
			new Thread(){
				@Override
				public void run() {
					super.run();
					task.init();
				}
			}.start();
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
	        timer.schedule(new Task(),date,PERIOD_DAY);
	        while(true){
	        	Task.printProgress();
	        	Thread.sleep(60*1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
