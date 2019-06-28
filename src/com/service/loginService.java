package com.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.dao.TAdminDAO;
import com.dao.TCheciDAO;
import com.dao.TUserDAO;
import com.model.TAdmin;
import com.model.TCheci;
import com.model.TUser;

public class loginService
{
	private TAdminDAO adminDAO;
	private TUserDAO userDAO;
	private TCheciDAO checiDAO;
	
	
	public TAdminDAO getAdminDAO()
	{
		return adminDAO;
	}
	public void setAdminDAO(TAdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}
	public TUserDAO getUserDAO()
	{
		return userDAO;
	}
	public void setUserDAO(TUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	public TCheciDAO getCheciDAO()
	{
		return checiDAO;
	}
	public void setCheciDAO(TCheciDAO checiDAO)
	{
		this.checiDAO = checiDAO;
	}
	public String login(String userName,String userPw,int userType)
	{
		System.out.println("userType"+userType);
		try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result="no";
		
		if(userType==0)//系统管理员登陆
		{
			String sql="from TAdmin where userName=? and userPw=?";
			Object[] con={userName,userPw};
			List adminList=adminDAO.getHibernateTemplate().find(sql,con);
			if(adminList.size()==0)
			{
				 result="no";
			}
			else
			{
				 WebContext ctx = WebContextFactory.get(); 
				 HttpSession session=ctx.getSession(); 
				 TAdmin admin=(TAdmin)adminList.get(0);
				 session.setAttribute("userType", 0);
	             session.setAttribute("admin", admin);
	             result="yes";
			}
		}
		if(userType==1)
		{
			
		}
		if(userType==2)
		{
			
		}
		return result;
	}

    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		admin.setUserPw(userPwNew);
		
		adminDAO.getHibernateTemplate().update(admin);
		session.setAttribute("admin", admin);
		
		return "yes";
    }
    
    
    public String userPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		 
		TUser user=(TUser)session.getAttribute("user");
		user.setUserPw(userPwNew);
		
		userDAO.getHibernateTemplate().update(user);
		session.setAttribute("user", user);
		
		return "yes";
    }
    
    
    public String yingfujine(int userId,int checiId,int yudingShumu)
    {
    	StringBuffer s=new StringBuffer("");
    	
    	TUser user=userDAO.findById(userId);
    	int jifen=user.getUserOne6();
    	
    	int jine=yudingShumu*(checiDAO.findById(checiId).getPiaojia());//不打折的价格
    	Float yinfujine=0F;
    	if(jifen>0 && jifen<=5)
    	{
    		s.append("会员等级：普通会员(8折)");
    		yinfujine=jine*0.8F;
    	}
    	if(jifen>5 && jifen<10)
    	{
    		s.append("会员等级：白银会员(6折)");
    		yinfujine=jine*0.6F;
    	}
    	if(jifen>=10)
    	{
    		s.append("会员等级：黄金会员(5折)");
    		yinfujine=jine*0.5F;
    	}
    	
    	s.append(",应付金额：");
    	s.append(yinfujine.toString());
    	return s.toString();
    }

}
