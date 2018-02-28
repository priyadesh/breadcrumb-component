package com.jpmorgan.core.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class Breadcrumb extends WCMUsePojo{
	private static final Logger LOG = LoggerFactory.getLogger(Breadcrumb.class);
	private List<Page> navList = new ArrayList<Page>();

	@Override
	public void activate() throws Exception {
		LOG.debug(":: IN BREADCRUMB ::");
		SlingHttpServletResponse response = getResponse();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
		setBreadCrumbItems();
	}
	private void setBreadCrumbItems()
	{
		
		int level = 1;
		int endLevel = 0;
		int currentLevel = getCurrentPage().getDepth();
			while (level < currentLevel - endLevel){
				Page trailPage = getCurrentPage().getAbsoluteParent((int)level);
				if(!trailPage.isHideInNav()){
					this.navList.add(trailPage);
				}
				level++;
			}
	}
	public List<Page> getNavList()
	{
	return this.navList;
	}
}