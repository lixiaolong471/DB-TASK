package com.qianhtj.task.dao.pvo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class CompanyPvoDao extends BaseDao {
	
	public CompanyPvoDao(){
		super(Context.DATASOUREC_LOCATION);
	}
	
	
	public List<Object[]> getList(Date startDate,Date endDate){
        List<Object> param = new ArrayList<>();
        StringBuffer selectSql = new StringBuffer("select company_id,company_name,company_short_name,register_number,registered_capital");
        selectSql.append(",city,establish_date,company_asset_size,company_profile,philosopy,team_profile,logo ");
        selectSql.append("from pvo_company_info where 1=1 ");
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

    public List<Object[]> getList(Date startDate){
        return getList(startDate,null);
    }
}
