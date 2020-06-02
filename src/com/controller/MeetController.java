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

import com.entity.Meet;
import com.entity.Notice;
import com.util.BaseDAO;

//会议
@Controller
@RequestMapping("/meet")
public class MeetController extends BaseController {
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Meet data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptName = (String) request.getSession().getAttribute("deptName");
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String a1 = request.getParameter("title");
		Date b1 = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] d = dao.findSingle(
				"SELECT * FROM meet WHERE title='" + a1 + "'" + " AND CURTIME='" + format.format(b1) + "'", null);
		if (d == null) {
			dao.operate("insert into meet values(null,?,?,?,?)",
					new Object[] { data.getTitle(), deptName, new Date(), user[1] });
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您今天已发布该会议，请勿重复提交！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		dao.operate("delete from meet where id=" + id, null);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Object[] data = dao.findSingle("select * from meet where id=" + id, null);
		request.setAttribute("record", data);
		return "meet/modify";
	}

	@RequestMapping("/update")
	public String update(Notice data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String a1 = request.getParameter("title");
		Date b1 = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] z1 = dao.findSingle(
				"SELECT * FROM meet WHERE title='" + a1 + "'" + " AND CURTIME='" + format.format(b1) + "'", null);
		Object[] z2 = dao.findSingle("SELECT * FROM meet WHERE title='" + a1 + "'" + " AND id='" + id + "'", null);
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
			dao.operate("update meet set title=?,curtime=? where id=?",
					new Object[] { data.getTitle(), new Date(), data.getId() });
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您今天已发布相同会议内容，无法修改！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	// 会议信息:所有人显示
	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
//	    if("主任".equals(role)){
		List all = dao.find("select * from meet", null);
		request.setAttribute("all", all);
//	    }else{
////	    	List all = dao.find("select * from meet where empno=?",new Object[]{user[1]});
//			List all = dao.find("select * from meet",new Object[]{user[1]});
//		    request.setAttribute("all", all);
//	    }
		return "meet/show";
	}

}
