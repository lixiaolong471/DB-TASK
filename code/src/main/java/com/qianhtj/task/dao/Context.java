package com.qianhtj.task.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class Context {
	private static Logger logger = Logger.getLogger(Context.class);
	public static final String JDBC_USER = ".user";
	public static final String JDBC_PASSWORD = ".password";
	public static final String JDBC_URL = ".url";
	public static final String JDBC_DRIVERCLASS = ".driverClass";
	public static final String JDBC_INITSIZE = ".initsize";
	public static final String JDBC_MAXACTIVE = ".maxactive";
	public static final String JDBC_MINIDLE = ".minidle";
	public static final String JDBC_MAXIDLE = ".maxidle";
	
	public static final String IMG_ADDRESS = "img.address";
	public static final String GET_POOL_KEY = "get.size";
	public static final String SAVE_POOL_KEY = "save.size";
	/**
	 * 本地拍拍网数据源
	 */
	public static final String DATASOUREC_LOCATION = "location";
	/**
	 * 本地网站数据源
	 */
	public static final String DATASOUREC_DATA = "data";
	
	
	
	public static int getDataSourceInitSize(String name){
		return config.getInt(name + JDBC_INITSIZE);
	}
	
	public static int getDataSourceMaxSize(String name){
		return config.getInt(name + JDBC_MAXACTIVE);
	}

    public static String getDriverClassName(String name){
        return config.getString(name + JDBC_DRIVERCLASS);
    }

    public static String getUsername(String name){
        return config.getString(name + JDBC_USER);
    }

    public static String getPassword(String name){
        return config.getString(name + JDBC_PASSWORD);
    }

    public static String getUrl(String name){
        return config.getString(name + JDBC_URL);
    }


    public static void setDriverClassName(String name,String value){
        config.setProperty(name + JDBC_DRIVERCLASS,value);
    }

    public static void setUsername(String name,String value){
        config.setProperty(name + JDBC_USER,value);
    }

    public static void setPassword(String name,String value){
        config.setProperty(name + JDBC_PASSWORD,value);
    }

    public static void setUrl(String name,String value){
        config.setProperty(name + JDBC_URL,value);
    }


    public static PropertiesConfiguration config = new PropertiesConfiguration();
	
	public static final Map<String,BasicDataSource> DATASOURCE_CACHE = new HashMap<String,BasicDataSource>();
	
	
	public static BasicDataSource getDataSourceByName(String name){
		BasicDataSource datasource = null;
		if((datasource = DATASOURCE_CACHE.get(name)) == null){
			datasource = createDateSource(name);
			DATASOURCE_CACHE.put(name, datasource);
		}
		return datasource;
	}
	
	/**
	 * 修改数据库配置
	 * @param name
	 */
	public static void reSetDataSourceByName(String name){
		
		//修改配置
		
		BasicDataSource datasource = DATASOURCE_CACHE.get(name);
		try {
            if(datasource != null){
                datasource.close();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		datasource = createDateSource(name);
		DATASOURCE_CACHE.put(name, datasource);
	}
	
	private static BasicDataSource createDateSource(String name){
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(config.getString(name+JDBC_DRIVERCLASS));
		datasource.setUsername(config.getString(name + JDBC_USER));
		datasource.setPassword(config.getString(name + JDBC_PASSWORD));
		datasource.setUrl(config.getString(name + JDBC_URL));
		datasource.setInitialSize(config.getInt(name + JDBC_INITSIZE));
		datasource.setMaxActive(config.getInt(name + JDBC_MAXACTIVE));
		datasource.setMinIdle(config.getInt(name + JDBC_MINIDLE));
		datasource.setMaxIdle(config.getInt(name + JDBC_MAXIDLE));
		datasource.setMaxWait(60*1000);
		datasource.setValidationQuery("select 1");
		return datasource;
	}
	
	/**
	 * 加载配置
	 *
	 */
	public static void loadConfig() {
		try {
			//String = "file:D:\\Developer\\svn\\Develop\\05-Coding\\website\\trunk\\WEB-TASK\\config.properties";
			String name = "file:config.properties";
			config = new PropertiesConfiguration(name);
		} catch (ConfigurationException e) {
			logger.error(e.getMessage());
		}

	}

    /**
     * 保存配置
     */
	public static void saveConfig(){
        try {
            config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

	
	public static String getImgPath(){
		return config.getString(IMG_ADDRESS).toString();
	}
	
	public static int getGetPool(){
		return config.getInt(GET_POOL_KEY);
	}
	
	public static int getSavePool(){
		return config.getInt(SAVE_POOL_KEY);
	}

    public static void setSavePool(String value){
        config.setProperty(SAVE_POOL_KEY,value);
    }

    public static void setGetPool(String value){
        config.setProperty(GET_POOL_KEY,value);
    }

    public static void setImgPath(String value){
        config.setProperty(IMG_ADDRESS,value);
    }
}
