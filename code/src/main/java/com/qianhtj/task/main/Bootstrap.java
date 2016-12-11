package com.qianhtj.task.main;



import com.qianhtj.task.job.DayTask;
import com.qianhtj.task.job.Task;
import com.qianhtj.task.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

public class Bootstrap {
	
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    private static Logger log = Logger.getLogger("提示信息");

	/**            
	 * @param args
	 */
	public static void main(String[] args){
		new DayTask().start();
	}
}
