package com.qianhtj.task.get.fund;

import com.qianhtj.task.dao.pvo.FundPvoManagerDao;
import com.qianhtj.task.bean.SunshinepublicFund;

public class FundManagerProcess implements FundProcess<SunshinepublicFund> {

	FundPvoManagerDao fundManagerDao = new FundPvoManagerDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		//数据表【pvo_fund_manager_mapping】更新
		Object[] personnelArray =  fundManagerDao.getFundManager(fund.fundId);
		if(personnelArray != null && personnelArray.length >0){
		    fund.fundManagerId = personnelArray[0];
			fund.fundManagerName = personnelArray[1];
			fund.fundManagerLogo = personnelArray[2];
			fund.fundManagerProfile = personnelArray[3];
		}
		return fund;
	}

}
