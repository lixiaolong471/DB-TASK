package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoRiskStatsDao;
import com.qianhtj.task.dao.pvo.FundPvoRiskadjretStatsDao;
import com.qianhtj.task.main.Util;

public class FundRiskadjretStatsProcess implements FundProcess<SunshinepublicFund> {
	
	FundPvoRiskStatsDao fundRiskStatsDao = new FundPvoRiskStatsDao();
	
	FundPvoRiskadjretStatsDao fundRiskadjretStatsDao = new FundPvoRiskadjretStatsDao();
	
	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		Object maxDate =  fundRiskStatsDao.getMaxDate(fund.fundId);
		if(maxDate != null){
			Object[] riskStatsArray =  fundRiskStatsDao.getFundRiskStats(fund.fundId, maxDate);
			if(riskStatsArray != null && riskStatsArray.length>0){
				fund.hisFundStats[1].y1 = Util.getPercentData(riskStatsArray[0]);
				fund.hisFundStats[1].y2 = Util.getPercentData(riskStatsArray[1]);
				fund.hisFundStats[1].y3 = Util.getPercentData(riskStatsArray[2]);
				fund.hisFundStats[1].all = Util.getPercentData(riskStatsArray[3]);
				
				fund.hisFundStats[2].y1 = Util.getPercentData(riskStatsArray[4]);
				fund.hisFundStats[2].y2 = Util.getPercentData(riskStatsArray[5]);
				fund.hisFundStats[2].y3 = Util.getPercentData(riskStatsArray[6]);
				fund.hisFundStats[2].all = Util.getPercentData(riskStatsArray[7]);
				
				fund.hisFundStats[6].y1 = Util.getPercentData(riskStatsArray[8]);
				fund.hisFundStats[6].y2 = Util.getPercentData(riskStatsArray[9]);
				fund.hisFundStats[6].y3 = Util.getPercentData(riskStatsArray[10]);
				fund.hisFundStats[6].all = Util.getPercentData(riskStatsArray[11]);
			}
		}
		
		maxDate =  fundRiskadjretStatsDao.getMaxDate(fund.id);
		if(maxDate != null ){
			Object[] riskStatsAjdArray =  fundRiskadjretStatsDao.getRiskadjretStats(fund.id, maxDate);
			if(riskStatsAjdArray != null && riskStatsAjdArray.length>0){
				fund.hisFundStats[3].y1 = Util.getPercentData(riskStatsAjdArray[0]);
				fund.hisFundStats[3].y2 = Util.getPercentData(riskStatsAjdArray[1]);
				fund.hisFundStats[3].y3 = Util.getPercentData(riskStatsAjdArray[2]);
				fund.hisFundStats[3].all = Util.getPercentData(riskStatsAjdArray[3]);
				
				fund.hisFundStats[4].y1 = Util.getPercentData(riskStatsAjdArray[4]);
				fund.hisFundStats[4].y2 = Util.getPercentData(riskStatsAjdArray[5]);
				fund.hisFundStats[4].y3 = Util.getPercentData(riskStatsAjdArray[6]);
				fund.hisFundStats[4].all = Util.getPercentData(riskStatsAjdArray[7]);
				
				fund.hisFundStats[5].y1 = Util.getPercentData(riskStatsAjdArray[8]);
				fund.hisFundStats[5].y2 = Util.getPercentData(riskStatsAjdArray[9]);
				fund.hisFundStats[5].y3 = Util.getPercentData(riskStatsAjdArray[10]);
				fund.hisFundStats[5].all = Util.getPercentData(riskStatsAjdArray[11]);
			}
		}
		return fund;
	}

}
