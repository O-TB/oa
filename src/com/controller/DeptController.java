package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.DeptDAO;
import com.entity.Dept;
import com.util.BaseDAO;

//部门管理
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {
	@Resource
	DeptDAO deptDAO;
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Dept data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String a1 = request.getParameter("name");
		Object[] d = dao.findSingle("SELECT * FROM dept WHERE name='" + a1 + "'", null);
//		System.out.println(d);

		if (d == null) {
			deptDAO.add(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('该部门已存在！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		deptDAO.del(id);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Dept record = deptDAO.findById(id);
		request.setAttribute("record", record);
		return "dept/modify";
	}

	@RequestMapping("/update")
	public String update(Dept data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println(id);
		String a1 = request.getParameter("name");
		String b1 = request.getParameter("manager");
		Object[] d1 = dao.findSingle("SELECT * FROM dept WHERE name='" + a1 + "'", null);
		Object[] d2 = dao.findSingle("SELECT * FROM dept WHERE manager='" + b1 + "'", null);
		Object[] d3 = dao.findSingle(
				"SELECT * FROM dept WHERE name='" + a1 + "'" + "and id='" + id + "'" + "and manager='" + b1 + "'",
				null);
//		System.out.println(d2);

		if (d1 == null || d2 == null) {
			deptDAO.update(data);
			return "redirect:show.do";
		} else if (d3 != null) {
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
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('该部门信息已存在，无法更改！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		List all = deptDAO.show();
		request.setAttribute("all", all);
		return "dept/show";
	}

}
