package com.qianhtj.task.dao.fund;

import com.qianhtj.task.bean.FundCompanyInfo;
import com.qianhtj.task.dao.BaseDao;
import com.qianhtj.task.dao.Context;
import com.qianhtj.task.dao.sys.IdDao;

import java.util.Date;

public class CompanyDao extends BaseDao{
	
	public CompanyDao(){
		super(Context.DATASOUREC_DATA);
	}
	
	public int saveOrUpdate(FundCompanyInfo info){
		StringBuffer updateSql = new StringBuffer("");
		StringBuffer selectSql = new StringBuffer("select count(1) from si_fund_company_info where company_id = ? ");
		Object[] companyExArray =  queryOne(selectSql.toString(),new Object[]{info.companyId});
		info.updateTime = new Date();
		if(companyExArray != null && companyExArray.length >0 && companyExArray[0] != null && ((Long)companyExArray[0]) != 0 ){
			updateSql = new StringBuffer("");
			updateSql.append("update si_fund_company_info set ");
			updateSql.append(" company_id=?,company_name=?,company_short_name=?,register_number=?,registered_capital=?,city=?,establish_date=?, ");
			updateSql.append("company_asset_size=?,company_profile=?,philosopy=?,team_profile=?,key_person=?,key_person_name=?,rep_product=?,rep_product_name=?,");
			updateSql.append("price_date=?,nav=?,inception_date=?,logo=?,key_person_logo=?,key_person_profile=?,seo_title=?,seo_keywords=?,seo_description=?,");
			updateSql.append("ret_incep=?,ret_ytd=?,pro_product_count=?,ret_ytd_average=?,ret_incep_average=?,update_time=? ");
			updateSql.append(" where company_id = '"+ info.companyId +"'");
			return update(updateSql.toString(), new Object[]{info.companyId,info.companyName,info.companyShortName,info.registerNumber,info.registeredCapital,
					info.city,info.establishDate,info.companyAssetSize,info.companyProfile,info.philosopy,info.teamProfile,info.keyPerson,info.keyPersonName,info.repProduct,info.repProductName,
					info.priceDate,info.nav,info.inceptionDate,info.logo,info.keyPersonLogo,info.keyPersonProfile,info.seoTitle,info.seoKeywords,info.seoDescription,
					info.retIncep,info.retYtd,info.proProductCount,info.retYtdAverage,info.retIncepAverage,info.updateTime
			});
		}else{//新增 
			//生成ID并插入
			IdDao idDao = IdDao.getInstancess();
			info.id = idDao.getCompanyId();
			updateSql.append("insert into si_fund_company_info(");
			updateSql.append(" company_id,company_name,company_short_name,register_number,registered_capital");
			updateSql.append(",city,establish_date,company_asset_size,company_profile,philosopy,team_profile,key_person,key_person_name,rep_product,rep_product_name,");
			updateSql.append("price_date,nav,inception_date,logo,key_person_logo,key_person_profile,seo_title,seo_keywords,seo_description, ");
			updateSql.append("ret_incep,ret_ytd,pro_product_count,id,ret_ytd_average,ret_incep_average,up_down,update_time)");
			updateSql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			return update(updateSql.toString(), new Object[]{info.companyId,info.companyName,info.companyShortName,info.registerNumber,info.registeredCapital,
					info.city,info.establishDate,info.companyAssetSize,info.companyProfile,info.philosopy,info.teamProfile,info.keyPerson,info.keyPersonName,info.repProduct,info.repProductName,
					info.priceDate,info.nav,info.inceptionDate,info.logo,info.keyPersonLogo,info.keyPersonProfile,info.seoTitle,info.seoKeywords,info.seoDescription,
					info.retIncep,info.retYtd,info.proProductCount,info.id,info.retYtdAverage,info.retIncepAverage,info.upDown,info.updateTime
			});
		}
		
	
	}
	
}
