package com.qianhtj.task.get.fund;

import com.qianhtj.task.bean.SunshinepublicFund;

public class SeoInfoProecess implements FundProcess<SunshinepublicFund> {

	@Override
	public SunshinepublicFund exec(SunshinepublicFund fund) {
		fund.seoTitle = fund.fundShortName+"、"+fund.fundShortName+"净值—天玑金服私募";
		fund.seoKeywords = fund.fundName+"、"+fund.fundName+"净值、"+fund.fundName+"排名、"+fund.fundName+"业绩回报、"+fund.fundName+"走势图";
		fund.seoDescription = "天玑金服阳光私募频道帮助您选择最优秀的阳光私募基金产品，并完成阳光私募购买手续，支持7*24小时在线咨询，提供"+fund.fundName+"净值、"+fund.fundName+"排名、私募产品数据库";
		return fund;
	}

}
