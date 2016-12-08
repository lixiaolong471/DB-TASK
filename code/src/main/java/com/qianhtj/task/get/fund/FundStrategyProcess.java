package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoStrategyDao;

public class FundStrategyProcess implements FundProcess<SunshinepublicFund> {

	FundPvoStrategyDao fundStrategyDao = new FundPvoStrategyDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		Object[] strategyArray =  fundStrategyDao.getStrategy(fund.fundId);
		if(strategyArray != null && strategyArray.length > 0){
			fund.strategy = strategyArray[0];
			fund.substrategy = strategyArray[1];
		}
		return fund;
	}

}
