package com.qianhtj.task.dao.pvo;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class FundPvoPerformanceRankingNewDao extends BaseDao {
	
	public FundPvoPerformanceRankingNewDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public Object getMaxDate(Object fundId){
		StringBuffer selectSql = new StringBuffer("select max(end_date) from pvo_fund_performance_ranking_new where fund_id = ? ");
		Object[] newPerDateArray =  queryOne(selectSql.toString(),new Object[]{fundId});
		if(newPerDateArray !=null && newPerDateArray.length >0 && newPerDateArray[0] != null){
			return newPerDateArray[0];
		}
		return null;
	}
	
	public Object[] getPerformanceRanking(Object fundId,Object maxDate){
		StringBuffer selectSql = new StringBuffer("select absrank_ret_ytd,absrank_ret_1m,absrank_ret_3m,absrank_ret_6m,absrank_ret_1y,absrank_ret_3y,");
		selectSql.append("perrank_ret_ytd,perrank_ret_1m,perrank_ret_3m,perrank_ret_6m,perrank_ret_1y,perrank_ret_3y ");
		selectSql.append(" from pvo_fund_performance_ranking_new where fund_id = ? and end_date=? ");
		Object[] newPerformanceArray =  queryOne(selectSql.toString(),new Object[]{fundId,maxDate});
		return newPerformanceArray;
	}
}
