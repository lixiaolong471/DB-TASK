package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.main.Util;

public class TakeSunshineProcess implements FundProcess<SunshinepublicFund> {

	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		try{
            System.out.println("--------exec-1----------");
			if(fund != null){
                System.out.println("--------exec-2----------");
				FundProcess<SunshinepublicFund> hisPerformanceProcess = new FundHisPerformanceProcess(); 
				FundProcess<SunshinepublicFund> riskadjretStatsProcess = new FundRiskadjretStatsProcess();
				FundProcess<SunshinepublicFund> hisNavProcess = new FundHisNavProcess();
				FundProcess<SunshinepublicFund> navProcess = new FundNavProcess();
				FundProcess<SunshinepublicFund> managerProcess = new FundManagerProcess();
				FundProcess<SunshinepublicFund> strategyProcess = new FundStrategyProcess();
				FundProcess<SunshinepublicFund> companyProcess = new FundCompanyProcess();
				FundProcess<SunshinepublicFund> custodianProcess = new FundCustodianProcess();
				FundProcess<SunshinepublicFund> statusPocess = new FundStatusPocess();
				FundProcess<SunshinepublicFund> seoPocess = new SeoInfoProecess();
				fund = hisPerformanceProcess.exec(fund);
				fund = riskadjretStatsProcess.exec(fund);
				fund = navProcess.exec(fund);
				fund = hisNavProcess.exec(fund);
				fund = managerProcess.exec(fund);
				fund = strategyProcess.exec(fund);
				fund = companyProcess.exec(fund);		
				fund = custodianProcess.exec(fund);
				fund = statusPocess.exec(fund);
				fund =	seoPocess.exec(fund);
				fund.fundManagerLogo = Util.covImg(fund.fundManagerLogo);
			}
            System.out.println("--------exec-3----------");
		}catch(Exception e){
			e.printStackTrace();
		}
		return fund;
	}
}
