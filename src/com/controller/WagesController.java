package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.WagesDAO;
import com.entity.Wages;
import com.util.BaseDAO;

//薪资
@Controller
@RequestMapping("/wages")
public class WagesController extends BaseController {
	@Resource
	WagesDAO wagesDAO;
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Wages data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String a1 = request.getParameter("empno");
		Date b1 = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] d = dao.findSingle(
				"SELECT * FROM wages WHERE empno='" + a1 + "'" + " AND CURTIME='" + format.format(b1) + "'", null);
		if (d == null) {
			data.setPublish((String) user[1]);
			data.setCurtime(new Date());
			wagesDAO.add(data);
//			System.out.println(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('该员工已经进行过登记，请重新选择！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		wagesDAO.del(id);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Wages record = wagesDAO.findById(id);
		request.setAttribute("record", record);
		return "wages/modify";
	}

	@RequestMapping("/update")
	public String update(Wages data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String a = request.getParameter("perf");
		String b = request.getParameter("base");
		Object[] z = dao.findSingle(
				"SELECT * FROM wages WHERE id='" + id + "'" + "AND perf='" + a + "'" + "AND base='" + b + "'", null);

		if (z != null) {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您未作出修改！');");
//			out.println("if(confirm('您没有作出修改，是否提交?')==false)return false;");
			out.println("window.location.href='show.do';");
			out.println("</script>");
			return null;
		} else {
			wagesDAO.update(data);
			return "redirect:show.do";
		}

	}

	// 薪资信息——————主任：对所有 普通员工：自己
	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
//		String deptName = (String) request.getSession().getAttribute("deptName");
//		  if("人事部".equals(deptName) || "财务部".equals(deptName)){
		if ("主任".equals(role)) {
			List all = wagesDAO.show();
			request.setAttribute("all", all);
		} else {
			List all = wagesDAO.owner((String) user[1]);
			request.setAttribute("all", all);
		}
		return "wages/show";
	}

}
