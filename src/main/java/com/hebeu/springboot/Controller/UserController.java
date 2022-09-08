package com.hebeu.springboot.Controller;


import com.hebeu.springboot.Pojo.User;
import com.hebeu.springboot.Pojo.UserfindByMul;
import com.hebeu.springboot.Service.UserService;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//参数绑定 登录 注册 表单数据  怎么传到方法中  分几种情况：
//简单类型  int double String   自动按名称传递 表单上 name  username 方法参数绑定的
//pojo类型 一个规则的类  封装规则 private   get  set 
//@requestParam(name="")   @PathVariable("")   HttpSession
@Controller
@RequestMapping(value="/user")//窄化处理
public class UserController{
	//方法名称随意 方法参数 返回值  方法个数不受限制
	//ModelAndView  String---视图名字  model  转向  void
	
	@Autowired(required=true)
	private UserService userservice;
	
	@RequestMapping(value="/index")
	public ModelAndView handleRequest(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView  mav= new ModelAndView();
		mav.addObject("msg", "这是第二个springmvc程序");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/findAll")
	public String  findAll(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		return "findAll";
	}
	
	@RequestMapping(value="/findAll.action")
	public String  ShowfindAll(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		List user=userservice.findALL();
		request.setAttribute("user", user);
		//request.getRequestDispatcher("/admin/findAll.jsp").forward(request,response);
		return "findAll";
	}
	
	@RequestMapping(value="/findByMul")
	public String  findByMul(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		return "findByMul";
	}
	
	@RequestMapping(value="findByMul.action")
	public String  ShowfindByMul(Model model, HttpServletRequest request, String username , String sex , String email)
			throws Exception{
		String minage=request.getParameter("minage");
		String maxage=request.getParameter("maxage");
		UserfindByMul userfindbymul=new UserfindByMul(username, sex, minage, maxage, email);
		List user=userservice.findByMul(userfindbymul);
		model.addAttribute("username",username);
		model.addAttribute("sex",sex);
		model.addAttribute("minage",minage);
		model.addAttribute("maxage",maxage);
		model.addAttribute("email",email);
		model.addAttribute("user",user);
		return "findByMul";
	}
	
	@RequestMapping(value="/login")
	public String  login(String username,String password)
			throws Exception{
		
		return "login";
	}
	
	@RequestMapping(value="/login.action")
	public String  dologin(String username, String password, User user, Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		System.out.println("接收前端==="+username);
			user.setUser(username);
		 user.setPassword(password);
		 model.addAttribute("user", user);
		 User n=userservice.login(user);
		 HttpSession session=request.getSession();
	     session.setAttribute("user", username);
		 
		 if(n!=null) {
		 		String remember=request.getParameter("remember");
		 		/*                       cookie的使用                           */
		 		//1.创建cookie
		 		String usernames= URLEncoder.encode(username,"utf-8");//因为cookie不能识别中文，所以要重新编码
		 		Cookie cookie1=new Cookie("name",usernames);
		 		Cookie cookie2=new Cookie("password",password);
		 		//2.时间周期
		 		if("yes".equals(remember)) {
		 		cookie1.setMaxAge(60*60*24);//存一天
		 		cookie2.setMaxAge(60*60*24);
		 		}
		 		else {
		 			cookie1.setMaxAge(0);
			 		cookie2.setMaxAge(0);	
		 		}
		 		//3.空间范围 指定cookie的作用范围，默认范围为当前servlet，设置为项目较好
		 		cookie1.setPath("/springweb/");
		 		cookie2.setPath("/springweb/");
		 		//服务范围为当前服务器
		 		//cookie1.setPath("/");
		 		//cookie2.setPath("/");
		 		//4.发送给前端（客户端）
		 		response.addCookie(cookie1);
		 		response.addCookie(cookie2); 		
		 		/*session的使用*/
		 		
		 		return "index";
		 	}
		 	else {
		 		//resp.sendRedirect("https://www.bilibili.com");//重定向
		 		request.setAttribute("error",username);
		 		return "login";
		 	}
	}
	
	@RequestMapping(value="/register")
	public String  register()
			throws Exception{
		return "register";
	}
	
	@RequestMapping(value="/register.action")
	public String  doregister(HttpServletRequest req,HttpServletResponse res)
			throws Exception{
		String username=req.getParameter("用户名");
		String password=req.getParameter("密码");
		String newpassword=req.getParameter("确认密码");
		String sex=req.getParameter("sex");
		String[]  hobby=req.getParameterValues("like");
		String hobby1=",";
		for(int i=0;i<hobby.length;i++)
			hobby1+=hobby[i]+",";
		String  email=req.getParameter("邮箱");
		String age=req.getParameter("age");
//		System.out.println(hobby);
		int age1=Integer.parseInt(age);
		String year=req.getParameter("year");
		String month=req.getParameter("month");
		String day=req.getParameter("day");
		String time=year+"-"+month+"-"+day;
	//	System.out.println(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate=new Date();
		try {
			utilDate = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(utilDate);
		}
		Date date= new java.sql.Date(utilDate.getTime());
		User user = new User();
		user.setTime((java.sql.Date) date);
		user.setUser(username);
		user.setPassword(password);
		user.setAge(age1);
		user.setEmail(email);
		user.setHobby(hobby1);
		user.setSex(sex);
	//	System.out.println("RegisterServlet:"+user.getUser()+" "+user.getPassword()+" "+user.getAge()+" "+user.getSex()+" "+user.getTime()+" "+user.getHobby());
		//数据处理
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		req.setAttribute("sex", sex);
		req.setAttribute("email", email);
		req.setAttribute("age", age);
		req.setAttribute("hobby", hobby);
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		req.setAttribute("day", day);
		
	//	UserService userservice=new UserServiceImpl();
			if(password.equals(newpassword)) {//判断两次密码是否输入正确	
		int n=userservice.save(user);
		if(n>0){//账号没有重复
			return "login";
		}
			else {
				req.setAttribute("error","2");
				return "register";
			}
		}
		else {//两次密码不一样
			req.setAttribute("error","1");
			return "register";
		}
	}
	
	@RequestMapping(value="/findalldelete.action")
	public String  delete(String user)
			throws Exception{
		try {
			userservice.deleteUser(user);
			System.out.println("删除成功");
			return "forward:findAll.action";
		}catch (Exception e) {
			System.out.println("删除失败");
			return "forward:findAll.action";
		}
			
	}
	
	@RequestMapping(value="/showupdateuser.action/{id}" ,method= RequestMethod.GET)
	public String  showupdateuser(@PathVariable("id") String id1, Model model)
			throws Exception{
		System.out.println("123"+id1);
		UserfindByMul us1=new UserfindByMul(id1, "0", "", "", "");
		List user1=userservice.findByMul(us1);
		User user2=(User) user1.get(0);
		model.addAttribute("user", user2);
		return "updateUser";
	}
	
	@RequestMapping(value="/updateuser.action")
	public String  updateuser(Model model,HttpServletRequest req,HttpServletResponse res)
			throws Exception{
		String username=req.getParameter("用户名");
		String password=req.getParameter("密码");
		String newpassword=req.getParameter("确认密码");
		String sex=req.getParameter("sex");
		String[]  hobby=req.getParameterValues("like");
		String hobby1=",";
		for(int i=0;i<hobby.length;i++)
			hobby1+=hobby[i]+",";
		String  email=req.getParameter("邮箱");
		String age=req.getParameter("age");
//		System.out.println(hobby);
		int age1=Integer.parseInt(age);
		String year=req.getParameter("year");
		String month=req.getParameter("month");
		String day=req.getParameter("day");
		String time=year+"-"+month+"-"+day;
	//	System.out.println(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate=new Date();
		try {
			utilDate = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(utilDate);
		}
		Date date= new java.sql.Date(utilDate.getTime());
		User user = new User();
		user.setTime((java.sql.Date) date);
		user.setUser(username);
		user.setPassword(password);
		user.setAge(age1);
		user.setEmail(email);
		user.setHobby(hobby1);
		user.setSex(sex);
		//UserService us= new UserServiceImpl();
		try {
			userservice.updateUser(user);
			System.out.println("修改成功");
			return "forware:findAll.action";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("修改失败");
			return "forward:showupdateuser.action";
		}
	}
	
	//退出登录
	@RequestMapping("/logout")
	   public String logout(HttpSession session) throws Exception {
	       // session 过期
	       session.invalidate();
	       return "login";
	}
}
