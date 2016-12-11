package com.qianhtj.task.get;

import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.fund.MarketIndexesDao;
import com.qianhtj.task.dao.pvo.MarketPvoIndexesDao;
import com.qianhtj.task.job.GetData;
import com.qianhtj.task.utils.DateUtils;
import org.apache.log4j.Logger;

import com.qianhtj.task.bean.HisMarket;

public class CovMarketIndex extends GeneralGetData implements GetData {

	MarketIndexesDao marketIndexesDao;
	MarketPvoIndexesDao marketPvoIndexesDao;
	
	public CovMarketIndex(){
		marketIndexesDao = new MarketIndexesDao();
		marketPvoIndexesDao = new MarketPvoIndexesDao();
	}

    @Override
    public List<Object[]> getList(Date startDate, Date endDate) {
        return marketPvoIndexesDao.findByStartDate(startDate,endDate);
    }

    @Override
    public void process(Object[] data) {
        HisMarket obj = new HisMarket(data[0],data[1],data[2],data[3]);
        marketIndexesDao.saveOrUpdate(obj);
    }

}
