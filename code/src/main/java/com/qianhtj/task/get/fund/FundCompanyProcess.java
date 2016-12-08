package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoCompanyInfoDao;

public class FundCompanyProcess implements FundProcess<SunshinepublicFund> {

	FundPvoCompanyInfoDao companyInfoDao = new FundPvoCompanyInfoDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		if(fund.advisorId != null){
			Object[] companyArray =  companyInfoDao.getCompanyInfo(fund.advisorId);
			if(companyArray != null && companyArray.length > 0 && companyArray[0] != null)  {
				fund.advisorName = companyArray[1];
				fund.advisorShortName = companyArray[2];
				fund.advisorProfile = companyArray[3];
			}
		}//pvo_company_info end if
		return fund;
	}

}
