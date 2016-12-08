package com.qianhtj.task.get;

import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.fund.MarketIndexesDao;
import com.qianhtj.task.dao.pvo.MarketPvoIndexesDao;
import com.qianhtj.task.job.GetData;
import org.apache.log4j.Logger;

import com.qianhtj.task.bean.HisMarket;

public class CovMarketIndex implements GetData {
	private static Logger logger = Logger.getLogger(CovMarketIndex.class);
	public static int sumcount = 0;
	
	public static int index = 0;
	
	MarketIndexesDao marketIndexesDao;
	MarketPvoIndexesDao marketPvoIndexesDao;
	
	public CovMarketIndex(){
		marketIndexesDao = new MarketIndexesDao();
		marketPvoIndexesDao = new MarketPvoIndexesDao();
	}
	
	@Override
	public void init() {
		getData(null);
	}

	@Override
	public void getData(Date date) {
		
		List<Object[]> marketArray =  marketPvoIndexesDao.findByStartDate(date);
		sumcount = marketArray.size();
		logger.info("沪深300数据总数="+sumcount);
		if(marketArray != null && marketArray.size() > 0)  {
			for(Object[] market: marketArray){
				HisMarket obj = new HisMarket(market[0],market[1],market[2],market[3]);
				marketIndexesDao.saveOrUpdate(obj);
				index ++;
			}
		}
		sumcount  = 0;
		index = 0;
	}

}
