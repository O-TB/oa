package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Apply;
import com.util.BaseDAO;

//办公申请
@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController {
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Apply data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptName = (String) request.getSession().getAttribute("deptName");
		String a1 = request.getParameter("empno");
		String b1 = request.getParameter("title");
		Date c1 = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(a1);
//		System.out.println(b1);
//		System.out.println(format.format(c1));
//		Object[] a = dao.findSingle("select * from apply where empno='" + a1 + "'", null);
//		Object[] b = dao.findSingle("select * from apply where title='" + b1 + "'", null);
//		Object[] c = dao.findSingle("select * from apply where curtime='" + format.format(c1) + "'", null);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
		Object[] d = dao.findSingle("SELECT * FROM apply WHERE empno='" + a1 + "'" + "AND title='" + b1 + "'"
				+ " AND CURTIME='" + format.format(c1) + "'", null);
//		System.out.println(d);

		if (d == null) {
			dao.operate("insert into apply values(null,?,?,?,?,?,?,?)", new Object[] { data.getEmpno(), data.getName(),
					data.getTitle(), new Date(), "调动", "待审", deptName });
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您今天已存在相同申请，无法提交！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		dao.operate("delete from apply where id=" + id, null);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Object[] record = dao.findSingle("select * from apply where id=" + id, null);
		request.setAttribute("data", record);
		return "apply/modify";
	}

	@RequestMapping("/yes")
	public String yes(int id, HttpServletRequest request) {
		String deptName = (String) request.getSession().getAttribute("deptName");
		if (!"办公室".equals(deptName)) {
			dao.operate("update apply set state=? where id=" + id, new Object[] { "主任通过" });
		} else {
			dao.operate("update apply set state=? where id=" + id, new Object[] { "主任通过" });
		}
		return "redirect:show.do";
	}

	@RequestMapping("/no")
	public String no(int id, HttpServletRequest request) {
		String deptName = (String) request.getSession().getAttribute("deptName");
		if (!"办公室".equals(deptName)) {
			dao.operate("update apply set state=? where id=" + id, new Object[] { "主任驳回" });
		} else {
			dao.operate("update apply set state=? where id=" + id, new Object[] { "主任驳回" });
		}
		return "redirect:show.do";
	}

	@RequestMapping("/update")
	public String update(Apply data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String b1 = request.getParameter("title");
		Date c1 = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] z1 = dao.findSingle(
				"SELECT * FROM apply WHERE title='" + b1 + "'" + " AND CURTIME='" + format.format(c1) + "'", null);
		Object[] z2 = dao.findSingle("SELECT * FROM apply WHERE id='" + id + "'" + "AND title='" + b1 + "'", null);
//		System.out.println(d);
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
			dao.operate("update apply set empno=?,name=?,title=?,curtime=?,state=? where id=?", new Object[] {
					data.getEmpno(), data.getName(), data.getTitle(), new Date(), data.getState(), data.getId() });
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您今天已存在相同申请，无法修改！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
//	    String position =  (String)request.getSession().getAttribute("position");
//	    if("人事部主任".equals(position)){
//			List all = dao.find("select * from apply where type=? and (state=? or state=? or state=?) or dept=?",new Object[]{"调动","主任通过","主任通过","主任驳回","人事部"});
//			request.setAttribute("all", all);
//	     }else 
		if ("普通员工".equals(role)) {
			List all = dao.find("select * from apply where type=? and empno=?", new Object[] { "调动", user[1] });
			request.setAttribute("all", all);
		} else {
			List all = dao.find("select * from apply", null);
//			List all = dao.find("select apply.* from apply where type=? and dept=? ",new Object[]{"调动",user[10]});
			request.setAttribute("all", all);
		}
		return "apply/show";
	}

}
