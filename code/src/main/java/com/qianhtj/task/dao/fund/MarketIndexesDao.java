package com.qianhtj.task.dao.fund;

import com.qianhtj.task.bean.HisMarket;
import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

import java.util.Date;

public class MarketIndexesDao extends BaseDao{

	public MarketIndexesDao() {		
		super(Context.DATASOUREC_DATA);
	}

	
	public int saveOrUpdate(HisMarket market){
		market.updateTime = new Date();
		StringBuffer selectSql = new StringBuffer("select count(1) from si_his_market where id = ? ");
		Object[] marketExArray = queryOne(selectSql.toString(),new Object[]{market.id});
		StringBuffer updateSql = new StringBuffer("");
		if(marketExArray != null && marketExArray.length > 0 && ((Long)marketExArray[0])> 0 ){
			updateSql.append(" update si_his_market set code=?,price_date=?,dclose=?,update_time=? where id=? ");
		}else{
			updateSql.append(" insert into si_his_market(code,price_date,dclose,update_time,id) values(?,?,?,?,?) ");
		}
		return update(updateSql.toString(), new Object[]{market.code,market.priceDate,market.dclose,market.updateTime,market.id});
	}
}
