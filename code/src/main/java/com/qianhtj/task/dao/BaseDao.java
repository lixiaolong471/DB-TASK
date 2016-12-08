package com.qianhtj.task.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;


public class BaseDao {
	
	private static Logger logger = Logger.getLogger("BaseDao");
	
	private BasicDataSource dataSource;
	
	public BaseDao(String name){
		setDatasource(name);
	}
	
	public BaseDao(){
	}
	
	public void setDatasource(String name){
		dataSource = Context.getDataSourceByName(name);		
	}

	public Connection getConnection(){
		try {
//			logger.info("datasource info "+name+":"+dataSource.getNumActive());
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Object[]> query(String sql, Object[] params){
		Connection conn = this.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			if(null != params && params.length>0){
				for(int i=0;i<params.length;i++){
					stmt.setObject(i+1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			return this.getListResult(rs);
		} catch (SQLException e) {
			logger.info("===query fail===" + sql);
			e.printStackTrace();
		} finally {
			this.release(rs, stmt, conn);
		}
		return null;
	}
	
	public Object[] queryOne(String sql, Object[] params){
		Connection conn = this.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			if(null != params && params.length>0){
				for(int i=0;i<params.length;i++){
					stmt.setObject(i+1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			return this.getSingleResult(rs);
		} catch (SQLException e) {
			logger.info("===queryOne fail===" + sql);
			throw new RuntimeException("查询出错", e);
		} finally {
			this.release(rs, stmt, conn);
		}
	}
	
	public List<Object[]> queryForBatch(String sql, List<Object[]> list){
		Connection conn = this.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		List<Object[]> resultList = null;
		try {
			if(null != list && list.size() > 0){
				resultList = new ArrayList<Object[]>();
				stmt = conn.prepareStatement(sql);
				
				int size = list.size();
				for(int i=0; i<size; i++){
					Object[] params = list.get(i);
					if(null != params && params.length>0){
						for(int j=0;j<params.length;j++){
							stmt.setObject(i+1, params[i]);
						}
					}
					rs = stmt.executeQuery();
					resultList.addAll(this.getListResult(rs));
				}
			}
			return resultList;
		} catch (SQLException e) {
			logger.info("===queryForBatch fail===" + sql);
			e.printStackTrace();
			return resultList;
		} finally {
			this.release(rs, stmt, conn);
		}
	}
	
	public int update(String sql, Object[] params){
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		int result = 0;
		try {
			stmt = conn.prepareStatement(sql);
			if(null != params && params.length>0){
				for(int i=0;i<params.length;i++){
					stmt.setObject(i+1, params[i]);
				}
			}
			result = stmt.executeUpdate();
//			conn.commit();
			return result;
		} catch (SQLException e) {
			logger.info("===update fail===" + sql);
			e.printStackTrace();
			return 0;
		} finally {
			this.closeStatement(stmt);
			this.closeConnection(conn);
		}
	}
	
	public int[] executeBatchSql(List<String> sqls){
		Connection conn = this.getConnection();
		Statement stmt = null;
		
		try {
			if(null != sqls && sqls.size() > 0){
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				for(String sql : sqls){
					stmt.addBatch(sql);
				}
				int[] isUpdates = stmt.executeBatch();
				conn.commit();
				return isUpdates;
			}
			return null;
		} catch (SQLException e) {
			logger.info("===executeBatchSql fail===");
			e.printStackTrace();
			return null;
		} finally{
			this.closeStatement(stmt);
			this.closeConnection(conn);
		}
	}
	
	public int[] updateForBatch(String sql, List<Object[]> list){
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		try {
			if(null != list && list.size() > 0){
				int size = list.size();
				conn.setAutoCommit(false);
				stmt = conn.prepareStatement(sql);
				
				for (int i=0;i<size;i++) {
					Object[] params = list.get(i);
					if (null != params && params.length > 0) {
						for (int j = 0; j < params.length; j++) {
							stmt.setObject(j + 1, params[j]);
						}
						stmt.addBatch();
					}
					if((i+1)%100 == 0){
						stmt.executeBatch();
						conn.commit();
					}
				}
				int[] isUpdate = stmt.executeBatch();
				conn.commit();
				return isUpdate;
			}
			return null;
		} catch (SQLException e) {
			logger.info("===updateForBatch fail===" + sql);
			e.printStackTrace();
			return null;
		} finally{
			this.closeStatement(stmt);
			this.closeConnection(conn);
		}
		
	}
	
	public boolean executeDdl(String sql){
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			return stmt.execute();
		} catch (SQLException e) {
			logger.info("===executeDdl fail===" + sql);
			e.printStackTrace();
			return false;
		} finally {
			this.closeStatement(stmt);
			this.closeConnection(conn);
		}
	}
	
	private List<Object[]> getListResult(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		List<Object[]> list = new ArrayList<Object[]>();
		while(rs.next()){
			Object[] objects = new Object[count];
			for(int i=0;i<count;i++){
				try{
					objects[i] = rs.getObject(i+1);
				}catch(Exception e){
					objects[i] = null;
					logger.info("warning： Wrong type of data");
				}
			}
			list.add(objects);
		}
		return list;
	}
	
	private Object[] getSingleResult(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		if(rs.next()){
			Object[] objects = new Object[count];
			for(int i=0;i<count;i++){
				objects[i] = rs.getObject(i+1);
			}
			return objects;
		}
		return null;
	}
	
	private void release(ResultSet rs, Statement stmt, Connection conn) {
		this.closeResultSet(rs);
		this.closeStatement(stmt);
		this.closeConnection(conn);
	}
	
	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private void closeResultSet(ResultSet rs){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.info("===close resultSet fail===");
				e.printStackTrace();
			}
			rs = null;
		}
	}
	
	private void closeStatement(Statement stmt){
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.info("===close statement fail===");
				e.printStackTrace();
			}
			stmt = null;
		}
	}
	


}
