package com.qianhtj.task.get;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.fund.FundNavDao;
import com.qianhtj.task.dao.fund.FundPerformanceDao;
import com.qianhtj.task.dao.fund.FundRiskadjretStatsDao;
import com.qianhtj.task.dao.fund.FundSunshineDao;
import com.qianhtj.task.dao.pvo.MarketPvoDao;
import com.qianhtj.task.get.fund.StorageHisNavProcess;
import com.qianhtj.task.get.fund.StorageSunshineProcess;
import com.qianhtj.task.job.Task;
import org.apache.log4j.Logger;

import com.qianhtj.task.dao.pvo.FundPvoSunshineDao;
import com.qianhtj.task.get.fund.TakeSunshineProcess;

public class SingleCovSunshine {
	
	
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
	
	
	public SingleCovSunshine(String shCode){
		this.shCode = shCode; 
		covSunshineDao = new FundPvoSunshineDao();
		marketDao = new MarketPvoDao();
		fundNavDao = new FundNavDao();
		fundPerformanceDao = new FundPerformanceDao();
		fundRiskadjretStatsDao = new FundRiskadjretStatsDao();
		fundSunshineDao = new FundSunshineDao();
	}
		
	private Object close;
	
	
	public void loadData(Object ...ids){
		try{
			//查询基金详情
			close = marketDao.getLastMarketClose(shCode);
			List<Object[]> list = covSunshineDao.getListInIds(ids);
			sumcount = new AtomicLong(list.size());
			log.info("采集总数："+sumcount);
			if(null != list && list.size() > 0){
				isRun.set(true);
				long startTimeMillis = System.currentTimeMillis();
				for(Object[] data:list){
					long cStartTimeMillis = System.currentTimeMillis();
					SunshinepublicFund fund = new SunshinepublicFund();
					if(close!=null){
						fund.close = close;
					}
					fund.setBaseFundInfo(data);
					fund = new TakeSunshineProcess().exec(fund);
					fund = new StorageSunshineProcess().exec(fund);
					fund = new StorageHisNavProcess().exec(fund);
					SingleCovSunshine.index.incrementAndGet();
					log.info("====阳光私募数据存储完成【saveOrUpdate】耗时："+(System.currentTimeMillis() - cStartTimeMillis) + "ms");
				}//end for 循环
				isRun.set(false);
				log.info("====阳光私募数据存储完成【SINGLE】耗时："+(System.currentTimeMillis() - startTimeMillis) + "ms");
				index.set(0);
				sumcount.set(0);
			}
		}catch(Exception e){
			System.err.println("==== 阳光私募数据存储异常 ====");
			e.printStackTrace();
		}
	}
	
	
	
}
