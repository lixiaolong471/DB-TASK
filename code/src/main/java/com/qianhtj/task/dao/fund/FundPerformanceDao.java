package com.qianhtj.task.dao.fund;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.bean.FundHisPerformance;
import com.qianhtj.task.dao.Context;

import java.util.Date;

public class FundPerformanceDao extends BaseDao {
	
	public FundPerformanceDao(){
		super(Context.DATASOUREC_DATA);
	}
	
	public int empty(Object fundId){
		StringBuffer updateSql = new StringBuffer(" delete from si_fund_his_performance where fund_id = ? ");
		return update(updateSql.toString(), new Object[]{fundId});
	}
	
	public int save(FundHisPerformance hisPerfor){
		StringBuffer updateSql = new StringBuffer("");
		updateSql.append("insert into si_fund_his_performance(");
		updateSql.append(" fund_performance,three_csi,average,absrank,four_ranking,fund_id,ret_type) ");
		updateSql.append(" values(?,?,?,?,?,?,?) ");
		return update(updateSql.toString(),new Object[]{hisPerfor.fundPerformance,
				hisPerfor.threeCsi,hisPerfor.average,hisPerfor.absrank,hisPerfor.fourRanking,hisPerfor.fundId,hisPerfor.retType});
	}
	
	public int saveOrUpdate(FundHisPerformance hisPerfor){
		hisPerfor.updateTime = new Date();
		StringBuffer selectSql = new StringBuffer("select count(fund_id) from si_fund_his_performance where fund_id= ? and ret_type = ?  ");
		Object[] hisPerCount =  queryOne(selectSql.toString(),new Object[]{hisPerfor.fundId,hisPerfor.retType});
		StringBuffer updateSql = new StringBuffer("");
		if(hisPerCount != null && hisPerCount.length >0 && hisPerCount[0] != null && ((Long)hisPerCount[0]) != 0){
			updateSql.append("update si_fund_his_performance set ");
			updateSql.append(" fund_performance=?,three_csi=?,average=?,absrank=?,four_ranking=?,update_time=? ");
			updateSql.append(" where fund_id = ? and ret_type = ? ");
		}else{
			updateSql.append("insert into si_fund_his_performance(");
			updateSql.append(" fund_performance,three_csi,average,absrank,four_ranking,update_time,fund_id,ret_type) ");
			updateSql.append(" values(?,?,?,?,?,?,?,?) ");
		}
		return update(updateSql.toString(),new Object[]{hisPerfor.fundPerformance,
				hisPerfor.threeCsi,hisPerfor.average,hisPerfor.absrank,hisPerfor.fourRanking,hisPerfor.updateTime,hisPerfor.fundId,hisPerfor.retType});
	}
	

	
	
}
