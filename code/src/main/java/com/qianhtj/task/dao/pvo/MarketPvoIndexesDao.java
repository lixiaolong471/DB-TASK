package com.qianhtj.task.dao.pvo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class MarketPvoIndexesDao extends BaseDao {

	public MarketPvoIndexesDao() {
		super(Context.DATASOUREC_LOCATION);
	}
	
	
	
	public List<Object[]> findByStartDate(Date date){
		return findByStartDate(date,null);
	}

    public List<Object[]> findByStartDate(Date startDate,Date endDate){
        List<Object> param = new ArrayList<>();
        StringBuffer selectSql = new StringBuffer("select id,index_code,price_date,close ");
        selectSql.append("from pvo_market_indexes where index_code = '000300.SH' ");
        if(startDate != null){
            selectSql.append(" and updatetime >= ? ");
            param.add(startDate);
        }
        if(endDate != null){
            selectSql.append(" and updatetime <= ? ");
            param.add(endDate);
        }
        return query(selectSql.toString(),param.toArray());
    }
}
