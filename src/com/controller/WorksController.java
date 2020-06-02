package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.WorksDAO;
import com.entity.Works;
import com.util.BaseDAO;

//考勤异常
@Controller
@RequestMapping("/works")
public class WorksController extends BaseController {
	@Resource
	WorksDAO worksDAO;
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Works data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String a1 = request.getParameter("empno");
		String b1 = request.getParameter("workDate");
		String c1 = request.getParameter("reason");
		System.out.println(a1);
		System.out.println(b1);
		System.out.println(c1);
		Object[] d = dao.findSingle("SELECT * FROM works WHERE empno='" + a1 + "'" + "AND workDate='" + b1 + "'"
				+ " AND reason='" + c1 + "'", null);
		if (d == null) {
			data.setState("待审");
			worksDAO.add(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('当前日期已存在相同申请，无法提交！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		worksDAO.del(id);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Works record = worksDAO.findById(id);
		request.setAttribute("record", record);
		return "works/modify";
	}

	@RequestMapping("/yes")
	public String yes(int id, HttpServletRequest request) {
		String deptName = (String) request.getSession().getAttribute("deptName");
		Works record = worksDAO.findById(id);
		if (!"办公室".equals(deptName)) {
			record.setState("主任通过");
		} else {
			record.setState("主任通过");
		}
		worksDAO.update(record);
		return "redirect:show.do";
	}

	@RequestMapping("/no")
	public String no(int id, HttpServletRequest request) {
		String deptName = (String) request.getSession().getAttribute("deptName");
		Works record = worksDAO.findById(id);
		if (!"办公室".equals(deptName)) {
			record.setState("主任驳回");
		} else {
			record.setState("主任驳回");
		}
		worksDAO.update(record);
		return "redirect:show.do";
	}

	@RequestMapping("/update")
	public String update(Works data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String a1 = request.getParameter("empno");
		String b1 = request.getParameter("workDate");
		String c1 = request.getParameter("reason");
		System.out.println(a1);
		System.out.println(b1);
		System.out.println(c1);
		Object[] z1 = dao.findSingle("SELECT * FROM works WHERE empno='" + a1 + "'" + "AND workDate='" + b1 + "'"
				+ " AND reason='" + c1 + "'", null);
		Object[] z2 = dao.findSingle(
				"SELECT * FROM works WHERE id='" + id + "'" + "AND workDate='" + b1 + "'" + " AND reason='" + c1 + "'",
				null);
		if (z2 != null) {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您未作出修改！');");
//			out.println("if(confirm('您没有作出修改，是否提交?')==false)return false;");
			out.println("window.location.href='show.do';");
			out.println("</script>");
			return null;
		} else if (z1 == null) {
			worksDAO.update(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('当前日期已存在该申请，无法修改！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	// 考勤异常信息————管理员/主任；所有 普通员工：个人
	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
//		   String position =  (String)request.getSession().getAttribute("position");
//		   if("主任".equals(position)){
//				List params = new ArrayList();
//				params.add("主任通过");
//				params.add("主任通过");
//				params.add("主任驳回");
//				List all = worksDAO.load(params);
//			    request.setAttribute("all", all);
//			}else if("普通员工".equals(role)){
//				 List all =  worksDAO.owner((String)user[1]);
//				 request.setAttribute("all", all);
//			}else{
//				  List all =  worksDAO.myEmp((String)user[10]);
//				  request.setAttribute("all", all); 
//			}

		if ("普通员工".equals(role)) {
			List all = worksDAO.owner((String) user[1]);
			request.setAttribute("all", all);
		} else {
			List all = worksDAO.show();
			request.setAttribute("all", all);
		}
		return "works/show";
	}

}
