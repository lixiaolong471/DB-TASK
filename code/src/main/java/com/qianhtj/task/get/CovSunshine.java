package com.qianhtj.task.get;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.fund.FundPerformanceDao;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.dao.pvo.MarketPvoDao;
import com.qianhtj.task.get.fund.StorageHisNavProcess;
import com.qianhtj.task.get.fund.StorageSunshineProcess;
import com.qianhtj.task.job.GetData;
import com.qianhtj.task.job.Task;
import org.apache.log4j.Logger;

import com.qianhtj.task.dao.fund.FundNavDao;
import com.qianhtj.task.dao.fund.FundRiskadjretStatsDao;
import com.qianhtj.task.dao.pvo.FundPvoSunshineDao;
import com.qianhtj.task.get.fund.TakeSunshineProcess;
import com.qianhtj.task.main.Util;

public class CovSunshine implements GetData {
	
	
	static Logger log = Logger.getLogger(Task.class);
	
	public static AtomicLong sumcount = new AtomicLong(0);
	public static AtomicLong index = new AtomicLong(0);
	public static  AtomicBoolean isRun = new AtomicBoolean(false);
	
	FundPvoSunshineDao covSunshineDao;
	MarketPvoDao marketDao;
	
	FundNavDao fundNavDao;
	FundPerformanceDao fundPerformanceDao;
	FundRiskadjretStatsDao fundRiskadjretStatsDao;
	FundSunshineDao fundSunshineDao;
	
	String shCode;
	
	BlockingQueue<SunshinepublicFund> baseDatas = new ArrayBlockingQueue<SunshinepublicFund>(50);
	
	
	public CovSunshine(String shCode){
		this.shCode = shCode; 
		covSunshineDao = new FundPvoSunshineDao();
		marketDao = new MarketPvoDao();
		fundNavDao = new FundNavDao();
		fundPerformanceDao = new FundPerformanceDao();
		fundRiskadjretStatsDao = new FundRiskadjretStatsDao();
		fundSunshineDao = new FundSunshineDao();
	}
		
	private Object close;
	
	
	@Override
	public void init() {
		Date endDate = covSunshineDao.getMaxDate();
		Date startDate = Util.addDay(endDate, -1); //covSunshineDao.getMinDate();
		loadData(startDate,endDate);
	}
	


	@Override
	public void getData(Date date) {
		loadData(date,null);
	}
	
	
	private void loadData(Date startDate,Date enddate){
		try{
			//查询基金详情
			close = marketDao.getLastMarketClose(shCode);
			List<Object[]> list = covSunshineDao.getList(startDate,enddate);
			sumcount = new AtomicLong(list.size());
			log.info("采集总数："+sumcount);
			if(null != list && list.size() > 0){
				isRun.set(true);
				long startTimeMillis = System.currentTimeMillis();
				for(Object[] data:list){
//					long cStartTimeMillis = System.currentTimeMillis();
					SunshinepublicFund fund = new SunshinepublicFund();
					if(close!=null){
						fund.close = close;
					}
					fund.setBaseFundInfo(data);
					fund = new TakeSunshineProcess().exec(fund);
					fund = new StorageSunshineProcess().exec(fund);
					fund = new StorageHisNavProcess().exec(fund);
					CovSunshine.index.incrementAndGet();
//					log.info("====阳光私募数据存储完成【saveOrUpdate】耗时："+(System.currentTimeMillis() - cStartTimeMillis) + "ms");
				}//end for 循环
				isRun.set(false);
				log.info("====阳光私募数据存储完成【ALL】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
				index.set(0);
				sumcount.set(0);
			}
		}catch(Exception e){
			System.err.println("==== pvo_company_info数据表更新异常 ====");
			e.printStackTrace();
		}
	}
	
	
	
}
