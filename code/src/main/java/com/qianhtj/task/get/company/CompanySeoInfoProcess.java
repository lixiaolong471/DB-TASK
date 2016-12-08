package com.qianhtj.task.get.company;

import com.qianhtj.task.bean.FundCompanyInfo;
import com.qianhtj.task.get.fund.FundProcess;

public class CompanySeoInfoProcess implements FundProcess<FundCompanyInfo> {

	@Override
	public FundCompanyInfo exec(FundCompanyInfo info) {
		info.seoTitle=info.companyShortName+"、阳光私募、阳光私募基金—天玑金服";
		info.seoKeywords=info.companyShortName+"、"+info.companyShortName+"排名、"+info.companyShortName+"私募、"+info.companyShortName+"阳光私募、天玑私募理财、私募在线理财、";
		info.seoDescription="天玑金服阳光私募公司频道帮助您选择最优秀的阳光私募基金公司，并评估"+info.companyShortName+"公司的收益率，支持7*24小时在线咨询，提供"+info.companyShortName+"最新研报、"+info.companyShortName+"走访报告、"+info.companyShortName+"排名、"+info.companyShortName+"产品数据库";
		return info;
	}

}
