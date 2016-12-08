package com.qianhtj.task.dao.pvo;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class MarketPvoDao extends BaseDao{
	
	private String shcode = "000300.SH";
	
	public MarketPvoDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	public Object getLastMarketClose(String indexCode){
		//统一更新沪市300昨日收盘价
		StringBuffer selectSql = new StringBuffer(" select max(price_date) from pvo_market_indexes where index_code = ? ");
		Object[] maxDate = queryOne(selectSql.toString(),new Object[]{shcode});
		Object[] marketClose = null;
		if(maxDate != null && maxDate.length > 0 && maxDate[0] != null){
			selectSql = new StringBuffer(" select `close` from pvo_market_indexes where index_code = ? and price_date = ?  ");
			marketClose = queryOne(selectSql.toString(),new Object[]{shcode,maxDate[0]});
			return marketClose[0];
		}
		return null;
	}
}
