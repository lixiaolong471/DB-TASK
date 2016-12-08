package com.qianhtj.task.dao.pvo;

import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class MarketPvoIndexesDao extends BaseDao {

	public MarketPvoIndexesDao() {
		super(Context.DATASOUREC_LOCATION);
	}
	
	
	
	public List<Object[]> findByStartDate(Date date){
		Object[] param = new Object[0];
		StringBuffer selectSql = new StringBuffer("select id,index_code,price_date,close ");
		selectSql.append("from pvo_market_indexes where index_code = '000300.SH' ");
		if(date != null){
			selectSql.append(" and updatetime >= ? ");
			param = new Object[]{date};
		}
		return query(selectSql.toString(),param);
	}
}
