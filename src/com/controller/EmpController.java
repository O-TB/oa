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
import com.dao.EmpDAO;
import com.entity.Emp;
import com.util.BaseDAO;

@Controller
@RequestMapping("/emp")
public class EmpController extends BaseController {
	@Resource
	EmpDAO empDAO;
	@Resource
	DeptDAO deptDAO;
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Emp data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String a1 = request.getParameter("empno");
		Object[] d = dao.findSingle("SELECT * FROM emp WHERE empno='" + a1 + "'", null);

		if (d == null) {
			data.setPwd("123456");
			empDAO.add(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('已存在该工号，请重新输入！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		empDAO.del(id);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		List deptList = deptDAO.show();
		Emp record = empDAO.findById(id);
		request.setAttribute("record", record);
		request.setAttribute("deptList", deptList);
		return "emp/modify";
	}

	@RequestMapping("/over")
	public String over(int id, HttpServletRequest request) {
		BaseDAO dao = new BaseDAO();
		dao.operate("update emp set state=? where id=?", new Object[] { "正式员工", id });
		return "redirect:show.do";
	}

	// 考勤登记 显示自己
	@RequestMapping("/load")
	public String load(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
//		if ("主任".equals(role)) {
//			List all = empDAO.show();
//			request.setAttribute("all", all);
//		} else {
		List all = empDAO.owner((String) user[1]);
		request.setAttribute("all", all);
//		}
		return "duty/load";
	}

	@RequestMapping("/update")
	public String update(Emp data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String a1 = request.getParameter("name");
		String b1 = request.getParameter("sex");
		String c1 = request.getParameter("tel");
		String d1 = request.getParameter("mail");
		String e1 = request.getParameter("birth");
		String f1 = request.getParameter("pwd");
		String g1 = request.getParameter("empno");
		String h1 = request.getParameter("state");
		String i1 = request.getParameter("empTime");
		Object[] z1 = dao.findSingle("SELECT * FROM emp WHERE name='" + a1 + "'" + " AND sex='" + b1 + "'"
				+ " AND tel='" + c1 + "'" + " AND mail='" + d1 + "'" + " AND birth='" + e1 + "'" + " AND pwd='" + f1
				+ "'" + " AND state='" + h1 + "'" + " AND empTime='" + i1 + "'", null);
		Object[] z2 = dao.findSingle("SELECT * FROM emp WHERE name='" + a1 + "'" + " AND sex='" + b1 + "'"
				+ " AND tel='" + c1 + "'" + " AND mail='" + d1 + "'" + " AND birth='" + e1 + "'" + " AND pwd='" + f1
				+ "'" + " AND empno='" + g1 + "'" + " AND state='" + h1 + "'" + " AND empTime='" + i1 + "'", null);
		Object[] z3 = dao.findSingle("SELECT * FROM emp WHERE empno='" + g1 + "'", null);

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
		} else if (z1 == null || z3 == null) {
			empDAO.update(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('已存在该工号！');");
//			out.println("if(confirm('您没有作出修改，是否提交?')==false)return false;");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;

		}

	}

	// 员工信息————管理员/主任；所有 普通员工：个人
	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
		if ("管理员".equals(role)) {
			List all = empDAO.show();
			request.setAttribute("all", all);
			return "emp/show";
		} else {
			List all = empDAO.owner((String) user[1]);
			request.setAttribute("all", all);
			return "emp/show";
		}

//		} else if("普通员工".equals(role)){
//			List all = empDAO.owner((String) user[1]);
//			request.setAttribute("all", all);
//			return "emp/show";
//		}else{
//			List all = empDAO.myEmp((String) user[10]);
//			request.setAttribute("all", all);
//			return "emp/show";
//		}
	}

	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		List all = empDAO.show();
		request.setAttribute("all", all);
		return "emp/view";
	}
}
