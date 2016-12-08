package com.qianhtj.task.dao.fund;

import com.qianhtj.task.bean.FundRiskadjretStats;
import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

import java.util.Date;

public class FundRiskadjretStatsDao extends BaseDao {
	
	public FundRiskadjretStatsDao(){
		super(Context.DATASOUREC_DATA);
	}
	
	
	public int empty(Object fundId){
		StringBuffer updateSql = new StringBuffer(" delete from si_fund_riskadjret_stats where fund_id = ? ");
		return update(updateSql.toString(), new Object[]{fundId});
	}
	
	public int save(FundRiskadjretStats peradjStats){
		StringBuffer updateSql = new StringBuffer("");
		updateSql.append("insert into si_fund_riskadjret_stats(");
		updateSql.append(" 1y,2y,3y,5y,`all`,fund_id,type) ");
		updateSql.append(" values(?,?,?,?,?,?,?) ");
		return update(updateSql.toString(),new Object[]{peradjStats.y1,peradjStats.y2,peradjStats.y3,
				peradjStats.y5,peradjStats.all,peradjStats.fundId,peradjStats.type});
	}
	
	public int saveOrUpdate(FundRiskadjretStats peradjStats){
		peradjStats.updateTime = new Date();
		StringBuffer selectSql = new StringBuffer("select count(fund_id) from si_fund_riskadjret_stats where fund_id = ? and type = ?  ");
		Object[] hisPerCount =  queryOne(selectSql.toString(),new Object[]{peradjStats.fundId,peradjStats.type});
		StringBuffer updateSql = new StringBuffer("");
		if(hisPerCount != null && hisPerCount.length >0 && hisPerCount[0] != null && ((Long)hisPerCount[0]) != 0){
			updateSql.append("update si_fund_riskadjret_stats set ");
			updateSql.append(" 1y=?,2y=?,3y=?,5y=?,`all`=?,update_time=? ");
			updateSql.append(" where fund_id = ? and type = ? ");
		}else{
			updateSql.append("insert into si_fund_riskadjret_stats(");
			updateSql.append(" 1y,2y,3y,5y,`all`,update_time,fund_id,type) ");
			updateSql.append(" values(?,?,?,?,?,?,?,?) ");
		}
		return update(updateSql.toString(),new Object[]{peradjStats.y1,peradjStats.y2,peradjStats.y3,
				peradjStats.y5,peradjStats.all,peradjStats.updateTime,peradjStats.fundId,peradjStats.type});
	}
	
}
