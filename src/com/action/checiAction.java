package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TCheciDAO;
import com.dao.TGonggaoDAO;
import com.model.TCheci;
import com.opensymphony.xwork2.ActionSupport;

public class checiAction extends ActionSupport
{
   private int checiId;
   private String shifazhan;
   private String daodazhan;
   private String piaoshijian;
   private String startshijian;
   private String endshijian;
   private int piaojia;
   private int piaoshu;
   
   private String message;
   private String path;
	
   private TCheciDAO checiDAO;
   private TGonggaoDAO gonggaoDAO;   
   
   private String startshijian1;//搜索发车时间断用的
   private String startshijian2;
   
   
   
   public String checiAdd()
   {
	   TCheci checi=new TCheci();
	   checi.setShifazhan(shifazhan);
	   checi.setDaodazhan(daodazhan);
	   checi.setPiaoshijian(piaoshijian);
	   checi.setStartshijian(startshijian);
	   checi.setEndshijian(endshijian);
	   checi.setPiaojia(piaojia);
	   checi.setPiaoshu(piaoshu);
	   checi.setDel("no");
	   checiDAO.save(checi);
	   this.setMessage("操作成功");
	   this.setPath("checiMana.action");
	   return "succeed";
   }
   
   
   public String checiMana()
   {
	   String sql="from TCheci where del='no' order by piaoshijian";
	   List checiList=checiDAO.getHibernateTemplate().find(sql);
	   Map request=(Map)ServletActionContext.getContext().get("request");
	   request.put("checiList", checiList);
	   return ActionSupport.SUCCESS;
   }
   
   
   public String checiDel()
   {
	   TCheci checi=checiDAO.findById(checiId);
	   checi.setDel("yes");
	   checiDAO.attachDirty(checi);
	   this.setMessage("操作成功");
	   this.setPath("checiMana.action");
	   return "succeed";
   }
   
   
   public String checiEditPre()
   {
	   TCheci checi=checiDAO.findById(checiId);
	   Map request=(Map)ServletActionContext.getContext().get("request");
	   request.put("checi", checi);
	   return ActionSupport.SUCCESS;
   }
   
   public String checiEdit()
   {
	   TCheci checi=checiDAO.findById(checiId);
	   checi.setShifazhan(shifazhan);
	   checi.setDaodazhan(daodazhan);
	   checi.setPiaoshijian(piaoshijian);
	   checi.setStartshijian(startshijian);
	   checi.setEndshijian(endshijian);
	   checi.setPiaojia(piaojia);
	   checi.setPiaoshu(piaoshu);
	   checiDAO.attachDirty(checi);
	   this.setMessage("操作成功");
	   this.setPath("checiMana.action");
	   return "succeed";
   }
   
   
   
   
   public String checiSerach()
   {
	   System.out.println("^^"+piaoshijian+"^^"+startshijian1+"^^"+startshijian2+"%%");
	   StringBuffer sql=new StringBuffer("from TCheci where del='no'");
	   if(!shifazhan.equals(""))
	   {
		   sql.append(" and shifazhan like '%"+shifazhan+"%'");
	   }
	   if(!piaoshijian.equals(""))
	   {
		   sql.append(" and piaoshijian='"+piaoshijian+"'");
	   }
	   if(!startshijian1.equals(""))
	   {
		   sql.append(" and startshijian > '"+startshijian1+"'");
	   }
	   if(!startshijian2.equals(""))
	   {
		   sql.append(" and startshijian < '"+startshijian2+"'");
	   }
	   
	   System.out.println(sql.toString()+"***");
	   
	   List checiList=checiDAO.getHibernateTemplate().find(sql.toString());
	   System.out.println(checiList.size()+"&&&");
	   Map request=(Map)ServletActionContext.getContext().get("request");
	   request.put("checiList", checiList);
	   
	    List gonggaoList=gonggaoDAO.findAll();
		if(gonggaoList.size()>5)
		{
			gonggaoList=gonggaoList.subList(0, 5);
		}
		request.put("gonggaoList", gonggaoList);
	   
	   return ActionSupport.SUCCESS;
	   
   }
   
   
   
   

	public TCheciDAO getCheciDAO()
	{
		return checiDAO;
	}
	
	public String getDaodazhan()
	{
		return daodazhan;
	}


	public void setDaodazhan(String daodazhan)
	{
		this.daodazhan = daodazhan;
	}


	public String getShifazhan()
	{
		return shifazhan;
	}


	public void setShifazhan(String shifazhan)
	{
		this.shifazhan = shifazhan;
	}


	public void setCheciDAO(TCheciDAO checiDAO)
	{
		this.checiDAO = checiDAO;
	}
	
	
	public int getCheciId()
	{
		return checiId;
	}
	
	public void setCheciId(int checiId)
	{
		this.checiId = checiId;
	}
	
	public String getEndshijian()
	{
		return endshijian;
	}
	
	public void setEndshijian(String endshijian)
	{
		this.endshijian = endshijian;
	}
	
	public TGonggaoDAO getGonggaoDAO()
	{
		return gonggaoDAO;
	}


	public void setGonggaoDAO(TGonggaoDAO gonggaoDAO)
	{
		this.gonggaoDAO = gonggaoDAO;
	}


	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public String getPiaoshijian()
	{
		return piaoshijian;
	}


	public void setPiaoshijian(String piaoshijian)
	{
		this.piaoshijian = piaoshijian;
	}


	public int getPiaojia()
	{
		return piaojia;
	}
	
	public void setPiaojia(int piaojia)
	{
		this.piaojia = piaojia;
	}
	
	public int getPiaoshu()
	{
		return piaoshu;
	}
	
	public void setPiaoshu(int piaoshu)
	{
		this.piaoshu = piaoshu;
	}
	
	public String getStartshijian()
	{
		return startshijian;
	}
	
	public void setStartshijian(String startshijian)
	{
		this.startshijian = startshijian;
	}


	public String getStartshijian1()
	{
		return startshijian1;
	}


	public void setStartshijian1(String startshijian1)
	{
		this.startshijian1 = startshijian1;
	}


	public String getStartshijian2()
	{
		return startshijian2;
	}


	public void setStartshijian2(String startshijian2)
	{
		this.startshijian2 = startshijian2;
	}
   
}
