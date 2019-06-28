package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TCheciDAO;
import com.dao.TGonggaoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class indexAction extends ActionSupport
{
	private TGonggaoDAO gonggaoDAO;
	private TCheciDAO checiDAO;
	
	
	
	public String index()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		
		
		String sql="from TCheci where del='no'";
	    List checiList=checiDAO.getHibernateTemplate().find(sql);
	    request.put("checiList", checiList);
		
		return ActionSupport.SUCCESS;
	}

	
	public TGonggaoDAO getGonggaoDAO()
	{
		return gonggaoDAO;
	}

	public void setGonggaoDAO(TGonggaoDAO gonggaoDAO)
	{
		this.gonggaoDAO = gonggaoDAO;
	}



	public TCheciDAO getCheciDAO()
	{
		return checiDAO;
	}


	public void setCheciDAO(TCheciDAO checiDAO)
	{
		this.checiDAO = checiDAO;
	}
}
