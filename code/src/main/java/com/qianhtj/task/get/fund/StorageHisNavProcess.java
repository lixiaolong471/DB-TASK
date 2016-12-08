package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import org.apache.log4j.Logger;
import com.qianhtj.task.bean.FundPvoNav;
import com.qianhtj.task.dao.fund.FundNavDao;
public class StorageHisNavProcess implements FundProcess<SunshinepublicFund>{
	private static Logger log = Logger.getLogger(StorageHisNavProcess.class);
	public SunshinepublicFund exec(SunshinepublicFund fund){
		FundNavDao fundPvoNavDao = new FundNavDao();
		for(FundPvoNav nav:fund.navs){
			try{
				fundPvoNavDao.saveOrUpdate(nav);	
			}catch(Exception e){
				log.error("====阳光私募数据存储-历史净值"+e.getMessage());
			}
		}
		return fund;
	}
}
