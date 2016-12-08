package com.qianhtj.task.dao.fund;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;
import com.qianhtj.task.bean.FundPvoNav;

import java.util.Date;

public class FundNavDao extends BaseDao {
	
	public FundNavDao(){
		super(Context.DATASOUREC_DATA);
	}
	
	public int empty(Object id){
		StringBuffer updateSql = new StringBuffer(" delete from si_fund_his_nav where fund_id = ? ");
		return update(updateSql.toString(), new Object[]{id});
	}
	
	public int save(FundPvoNav nav){
		StringBuffer updateSql = new StringBuffer(" insert into si_fund_his_nav(nav,cumulative_nav,rise_fall,fund_id,price_date) values(?,?,?,?,?) ");
		return update(updateSql.toString(), new Object[]{nav.nav,nav.cumulativeNav,nav.riseFall,nav.fundId,nav.priceDate});
	}
	
	
	public int saveOrUpdate(FundPvoNav nav){
		nav.updateTime = new Date();
		StringBuffer selectSql = new StringBuffer("select count(1) from si_fund_his_nav where fund_id=? and price_date=?");
		StringBuffer updateSql = new StringBuffer();
		Object[] existsData = queryOne(selectSql.toString(), new Object[]{nav.fundId,nav.priceDate});
		if(existsData!=null && existsData[0] != null && ((Long)existsData[0]).intValue() > 0){
			updateSql.append(" update si_fund_his_nav set nav=?,cumulative_nav=?,rise_fall=?,update_time=? where fund_id = ? and price_date =? ");
		}else{
			updateSql.append(" insert into si_fund_his_nav(nav,cumulative_nav,rise_fall,update_time,fund_id,price_date) values(?,?,?,?,?,?) ");
		}
		return update(updateSql.toString(), new Object[]{nav.nav,nav.cumulativeNav,nav.riseFall,nav.updateTime,nav.fundId,nav.priceDate});
	}
	
}
