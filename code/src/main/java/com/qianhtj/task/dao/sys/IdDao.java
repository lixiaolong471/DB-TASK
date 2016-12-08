package com.qianhtj.task.dao.sys;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;

public class IdDao extends BaseDao {
	
	/** 阳光私募数据表ID  */
	protected static final String TABLE_FUND = "si_sunshine_private_fund";
	
	/** 阳光私募公司数据表ID */
	protected static final String COMPANY_FUND = "si_fund_company_info";
	
	
	private Lock lock = new ReentrantLock();
	
	private volatile static IdDao idDao;
	
	public static IdDao getInstancess(){
		if(idDao == null){
			idDao = new IdDao();
		}
		return idDao;
	}
	
	private IdDao() {
		super(Context.DATASOUREC_DATA);
	}
	
	public Long getAndSave(String type){
		Long maxId = 1L;
		StringBuffer selectSql = new StringBuffer("select keyvalue from sys_idtable where keyId=? ");
		StringBuffer updateSql = new StringBuffer("update sys_idtable set keyvalue = ? where keyId=? ");
		Object[] result = idDao.queryOne(selectSql.toString(), new Object[]{type});
		if(result != null && result.length > 0 && result[0] != null){
			maxId = (Long)result[0]+1;
		}
		idDao.update(updateSql.toString(), new Object[]{maxId,type});
		return  maxId;
	}
	/**
	 * 获取阳光私募产品ID
	 * @return
	 */
	public String getFundId(){
		lock.lock();
		try{
			return "HF"+getCode(getAndSave(TABLE_FUND).toString(),6,"0");
		}finally{
			lock.unlock();
		}		
	}
	
	/**
	 * 获取阳光私募公司ID
	 * @return
	 */
	public String getCompanyId(){
		lock.lock();
		try{
			return "CO"+getCode(getAndSave(COMPANY_FUND).toString(),6,"0");
		}finally{
			lock.unlock();
		}	
	}
	/**
	 * 格式化编码
	 * @param source
	 * @param length
	 * @param addStr
	 * @return
	 */
	private String getCode(String source,int length,String addStr){
		for(int i = source.length();i < length;i++){
			source = addStr+source; 
		}
		return source;
	}

}
