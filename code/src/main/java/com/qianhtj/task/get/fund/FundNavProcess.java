package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoNavDao;

public class FundNavProcess implements FundProcess<SunshinepublicFund> {
	
	FundPvoNavDao fundPvoNavDao = new FundPvoNavDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		//数据表【pvo_nav】更新 
		Object maxDate = fundPvoNavDao.getMaxDate(fund.fundId);
		if(maxDate != null){
			Object[] navArray =  fundPvoNavDao.getFundNav(fund.fundId, maxDate);
			if(navArray != null && navArray.length > 0){
				fund.priceDate = navArray[0];
				fund.valid = navArray[1];
				fund.nav = navArray[2];
				fund.cumulativeNavWithdrawal = navArray[3];
				fund.cumulativeNav = navArray[4];
			}
		}
		return fund;
	}

}
