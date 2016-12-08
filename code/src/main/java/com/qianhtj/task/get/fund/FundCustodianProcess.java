package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoCompanyInfoDao;

public class FundCustodianProcess implements FundProcess<SunshinepublicFund> {

	FundPvoCompanyInfoDao companyInfoDao = new FundPvoCompanyInfoDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		//数据表【pvo_company_info】托管银行更新 
		if(fund.custodianId != null){
			Object[] companyArray =  companyInfoDao.getCompanyInfo(fund.custodianId);
			if(companyArray != null && companyArray.length > 0 && companyArray[0] != null)  {
				fund.custodianId = companyArray[0];
				fund.custodianName = companyArray[2];
				fund.custodianShortName = companyArray[1];
			}
		}//pvo_company_info end if
		return fund;
	}

}
