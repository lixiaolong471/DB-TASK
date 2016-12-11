package com.qianhtj.task.job;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import org.apache.log4j.Logger;
import com.qianhtj.task.get.CovCompany;
import com.qianhtj.task.get.CovMarketIndex;
import com.qianhtj.task.get.CovSunshine;

public class Task extends TimerTask {
	
	static Logger log = Logger.getLogger(Task.class);

	private GetData[] updateDataArray = new GetData[]{
			new CovMarketIndex(),
			new CovSunshine("000300.SH"),
			new CovCompany()
	};
	
	private volatile static boolean run;
	public static long process = -1L;
    public static String processInfo;
	
	@Override
	public void run() {
		if(!run){
			log.info("【TASK】任务开始执行！");
			run = true;
			for(GetData get:updateDataArray){
				get.getData();
			}
			log.info("【TASK】定时任务执行结束！");
			run = false;
		}
	}

	
	public static Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    } 
	
	
	public void printProgress(){

		if(updateDataArray[0].getSumCount() >0){
			process = updateDataArray[0].getIndex() * 100/updateDataArray[0].getSumCount();
            processInfo = "沪深300数据执行已完成："+(((float)process)/100)+"%.总数="+updateDataArray[0].getSumCount()+"当前="+updateDataArray[0].getIndex();
		}else if(updateDataArray[1].getSumCount() >0){
			process = updateDataArray[1].getIndex() * 100L/updateDataArray[1].getSumCount();
            processInfo = "阳光私募产品【初始化】数据执行已完成："+(((float)process)/100)+"%.总数="+updateDataArray[0].getSumCount()+"当前="+updateDataArray[1].getIndex();
		}else if( updateDataArray[2].getSumCount() > 0){
			process = updateDataArray[2].getIndex() * 100L/updateDataArray[2].getSumCount();
            processInfo = "阳光私募公司数据执行已完成："+(((float)process)/100)+"%.总数="+updateDataArray[0].getSumCount()+"当前="+updateDataArray[2].getIndex();
		}

        if(updateDataArray[0].getSumCount() <= 0 &&
                updateDataArray[0].getSumCount() <= 0 &&
                    updateDataArray[0].getSumCount() <= 0){
            process = -1L;
        }
	}
}
