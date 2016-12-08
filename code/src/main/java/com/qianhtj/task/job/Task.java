package com.qianhtj.task.job;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.qianhtj.task.get.CovCompany;
import com.qianhtj.task.get.CovMarketIndex;
import com.qianhtj.task.get.CovSunshine;
import com.qianhtj.task.get.FastCovSunshine;

public class Task extends TimerTask {
	
	static Logger log = Logger.getLogger(Task.class);

	private GetData[] updateDataArray = new GetData[]{
			new CovMarketIndex(),
			new CovSunshine("000300.SH"),
			new CovCompany()
	};
	
	private GetData[] initDataArray = new GetData[]{
			new CovMarketIndex(),
			new FastCovSunshine("000300.SH"),
			new CovCompany()
	};
	
	
	private volatile static boolean run;
	private static long process;
	
	@Override
	public void run() {
		if(!run){
			log.info("【TASK】任务开始执行！");
			run = true;
			for(GetData get:updateDataArray){
				get.getData(addDay(new Date(),-1));
			}
			log.info("【TASK】定时任务执行结束！");
			run = false;
		}
	}
	
	public void init(){
		if(!run){
			run = true;
			log.info("【初始化】定时任务开始执行！");
			run = true;
			for(GetData get:initDataArray){
				get.init();
			}
			log.info("【初始化】定时任务执行结束！");
			run = false;
		}
	}
	
	
	public static Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    } 
	
	
	public static void printProgress(){
		if(CovMarketIndex.sumcount >0){
			process = CovMarketIndex.index * 10000/CovMarketIndex.sumcount;
			log.info("沪深300数据执行已完成："+(((float)process)/100)+"%.总数="+CovMarketIndex.sumcount+"当前="+CovMarketIndex.index);
		}else if( CovSunshine.sumcount.get() > 0){
			process = CovSunshine.index.get() * 10000L/CovSunshine.sumcount.get();
			log.info("阳光私募产品【更新】数据执行已完成："+(((float)process)/100)+"%.总数="+CovSunshine.sumcount.get()+"当前="+CovSunshine.index.get());
		}else if( FastCovSunshine.sumcount.get() > 0){
			process = FastCovSunshine.index.get() * 10000L/FastCovSunshine.sumcount.get();
			log.info("阳光私募产品【初始化】数据执行已完成："+(((float)process)/100)+"%.总数="+FastCovSunshine.sumcount.get()+"当前="+FastCovSunshine.index.get());
		}else if(CovCompany.sumcount >0){
			process = CovCompany.index * 10000/CovCompany.sumcount;
			log.info("阳光私募公司数据执行已完成："+(((float)process)/100)+"%.总数="+CovCompany.sumcount+"当前="+CovCompany.index);
		}
	}
}
