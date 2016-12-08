package com.qianhtj.task.get.fund;
import com.qianhtj.task.bean.SunshinepublicFund;
import com.qianhtj.task.dao.pvo.FundPvoPerformanceDao;
import com.qianhtj.task.dao.pvo.FundPvoPerformanceRankingNewDao;
import com.qianhtj.task.main.Util;

public class FundHisPerformanceProcess implements FundProcess<SunshinepublicFund> {

	private FundPvoPerformanceDao fundPerformanceDao = new FundPvoPerformanceDao();
	private FundPvoPerformanceRankingNewDao performanceRankingNewDao = new FundPvoPerformanceRankingNewDao();

	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		//数据表【pvo_fund_performance】更新相关数据
		Object maxDate = fundPerformanceDao.getMaxDate(fund.fundId);
		if(maxDate != null ){
			//ret_incep,ret_ytd,ret_1y,ret_6m,ret_2y,ret_3y,
			Object[] performanceArray = fundPerformanceDao.getPerformance(fund.fundId, maxDate);
			if(performanceArray!=null && performanceArray.length>0){
				fund.retincep = Util.getPercentData(performanceArray[16]);
				fund.retYtd = Util.getPercentData(performanceArray[17]);
				fund.ret1y = Util.getPercentData(performanceArray[18]);
				fund.sixm = Util.getPercentData(performanceArray[19]);
				
				fund.hisFundStats[0].y2 = Util.getPercentData(performanceArray[20]);
				fund.hisFundStats[0].y3 = Util.getPercentData(performanceArray[21]);
				fund.hisFundStats[0].y5 = Util.getPercentData(performanceArray[22]);
				fund.hisFundStats[0].all= Util.getPercentData(performanceArray[23]);
				
				for(int i=0;i<8;i++){
					fund.hisPerformance[i].fundPerformance = Util.getPercentData(performanceArray[i]);
				}
				for(int i=8;i<16;i++){
					fund.hisPerformance[i-8].threeCsi = Util.getPercentData(performanceArray[i]);
				}
			}
		}
		maxDate =  performanceRankingNewDao.getMaxDate(fund.fundId);
		if(maxDate != null){
			Object[] newPerformanceArray =  performanceRankingNewDao.getPerformanceRanking(fund.fundId, maxDate);
			if(newPerformanceArray!=null && newPerformanceArray.length>0){
				for(int i=0;i<6;i++){
					fund.hisPerformance[i].absrank = newPerformanceArray[i];
				}
				for(int i=6;i<12;i++){
					fund.hisPerformance[i-6].fourRanking = newPerformanceArray[i];
				}
			}
		
		}
		return fund;
	}
	
	
	
}
