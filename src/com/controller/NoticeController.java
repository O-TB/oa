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

import com.entity.Notice;
import com.util.BaseDAO;

//公告
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Notice data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String a1 = request.getParameter("title");
		Date b1 = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] d = dao.findSingle(
				"SELECT * FROM notice WHERE title='" + a1 + "'" + " AND CURTIME='" + format.format(b1) + "'", null);
		if (d == null) {
			dao.operate("insert into notice values(null,?,?)", new Object[] { data.getTitle(), new Date() });
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您今天已发布该公告，请勿重复提交！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		dao.operate("delete from notice where id=" + id, null);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Object[] data = dao.findSingle("select * from notice where id=" + id, null);
		request.setAttribute("record", data);
		return "notice/modify";
	}

	@RequestMapping("/update")
	public String update(Notice data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String a1 = request.getParameter("title");
		Date b1 = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] z1 = dao.findSingle(
				"SELECT * FROM notice WHERE title='" + a1 + "'" + " AND CURTIME='" + format.format(b1) + "'", null);
//		System.out.println(id);
//		System.out.println(a1);
		Object[] z2 = dao.findSingle("SELECT * FROM notice WHERE title='" + a1 + "'" + "and id='" + id + "'", null);

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
			dao.operate("update notice set title=?,curtime=? where id=?",
					new Object[] { data.getTitle(), new Date(), data.getId() });
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您今天已发布相同公告内容，无法修改！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		List all = dao.find("select * from notice", null);
		request.setAttribute("all", all);
		return "notice/show";
	}

}
