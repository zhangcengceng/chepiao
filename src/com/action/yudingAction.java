package com.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TCheciDAO;
import com.dao.TUserDAO;
import com.dao.TYudingDAO;
import com.model.TCheci;
import com.model.TUser;
import com.model.TYuding;
import com.opensymphony.xwork2.ActionSupport;

public class yudingAction extends ActionSupport
{
    private int yudingId;
    private int userId;
    private int checiId;
    private int yudingShumu;
    private String zhifufangshi;
    
    private TYudingDAO yudingDAO;
    private TCheciDAO checiDAO;
    private TUserDAO userDAO;
    private String message;
	private String path;
	
	public String yudingAdd()
	{
		TCheci checi=checiDAO.findById(checiId);
		if(checi.getPiaoshu()<yudingShumu)
		{
			return "msg";
		}
		
		
		TYuding yuding=new TYuding();
		Map session= ServletActionContext.getContext().getSession();
		TUser user=(TUser)session.get("user");
		yuding.setCheciId(checiId);
		yuding.setUserId(user.getUserId());
		yuding.setYudingShumu(yudingShumu);
		yuding.setZhifufangshi(zhifufangshi);
		yuding.setYudingShijian(new Date().toLocaleString());
		yuding.setYudingZhuantai(0);////0未受理。1受理。2删除
		yudingDAO.save(yuding);
		
		checiDAO.getHibernateTemplate().bulkUpdate("update TCheci set piaoshu=piaoshu-"+yudingShumu+" where id="+checiId);
		
		return "successAdd";
	}
	
	public String yudingMana()
	{
		 String sql="from TYuding where yudingZhuantai !=2";
		 List yudingList=yudingDAO.getHibernateTemplate().find(sql);
		 Map request=(Map)ServletActionContext.getContext().get("request");
		 request.put("yudingList", yudingList);
	     return ActionSupport.SUCCESS;
	}
	
	public String userYudingMy()
	{
		 Map session= ServletActionContext.getContext().getSession();
		 TUser user=(TUser)session.get("user");
		
		 String sql="from TYuding where yudingZhuantai !=2 and userId="+user.getUserId();
		 List yudingList=yudingDAO.getHibernateTemplate().find(sql);
		 Map request=(Map)ServletActionContext.getContext().get("request");
		 request.put("yudingList", yudingList);
	     return ActionSupport.SUCCESS;
	}
	
	
	public String yudingDel()
	{
		TYuding yuding=yudingDAO.findById(yudingId);
		yuding.setYudingZhuantai(2);
		yudingDAO.attachDirty(yuding);
		this.setMessage("操作成功");
		this.setPath("yudingMana.action");
		return "succeed";
	}
	
	public String yudingShouli()
	{
		TYuding yuding=yudingDAO.findById(yudingId);
		yuding.setYudingZhuantai(1);
		yudingDAO.attachDirty(yuding);
		
		
		int userId=yuding.getUserId();
		TUser user=userDAO.findById(userId);
		user.setUserOne6(user.getUserOne6()+1);
		userDAO.attachDirty(user);
		this.setMessage("操作成功");
		this.setPath("yudingMana.action");
		return "succeed";
	}
	
	
	public String chepiaoxinxi()
	{
		String sql="from TCheci where del='no' and id="+checiId;
	    List checiList=checiDAO.getHibernateTemplate().find(sql);
	    Map request=(Map)ServletActionContext.getContext().get("request");
	    request.put("checiList", checiList);
	    return ActionSupport.SUCCESS;
	}
	
	public String userxinxi()
	{
		TUser user=userDAO.findById(userId);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("user", user);
	    return ActionSupport.SUCCESS;
	}
	
	
	
	public String userDingdanMy()
	{
		 Map session= ServletActionContext.getContext().getSession();
		 TUser user=(TUser)session.get("user");
		 
		 String sql="from TYuding where userId="+user.getUserId();
		 List yudingList=yudingDAO.getHibernateTemplate().find(sql);
		 Map request=(Map)ServletActionContext.getContext().get("request");
		 request.put("yudingList", yudingList);
	     return ActionSupport.SUCCESS;
	}
	
	
	
	public int getCheciId()
	{
		return checiId;
	}
	public void setCheciId(int checiId)
	{
		this.checiId = checiId;
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
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	public TCheciDAO getCheciDAO()
	{
		return checiDAO;
	}



	public void setCheciDAO(TCheciDAO checiDAO)
	{
		this.checiDAO = checiDAO;
	}



	public TUserDAO getUserDAO()
	{
		return userDAO;
	}

	public void setUserDAO(TUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public String getZhifufangshi()
	{
		return zhifufangshi;
	}



	public void setZhifufangshi(String zhifufangshi)
	{
		this.zhifufangshi = zhifufangshi;
	}



	public TYudingDAO getYudingDAO()
	{
		return yudingDAO;
	}
	public void setYudingDAO(TYudingDAO yudingDAO)
	{
		this.yudingDAO = yudingDAO;
	}
	public int getYudingId()
	{
		return yudingId;
	}
	public void setYudingId(int yudingId)
	{
		this.yudingId = yudingId;
	}
	public int getYudingShumu()
	{
		return yudingShumu;
	}
	public void setYudingShumu(int yudingShumu)
	{
		this.yudingShumu = yudingShumu;
	}
	
}
