package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.DutyDAO;
import com.entity.Duty;
import com.util.BaseDAO;
import com.util.UtilDAO;

//考勤管理
@Controller
@RequestMapping("/duty")
public class DutyController extends BaseController {
	@Resource
	DutyDAO dutyDAO;
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		BaseDAO dao = new BaseDAO();
		Object[] emp = dao.findSingle("select * from emp where id=" + id, null);
		Date a = new Date();
		// 转型new一个日期格式对象，指定格式为（年-月-日）
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Object[] b = dao.findSingle("SELECT * FROM duty WHERE dutyDate='" + format.format(a) + "'", null);
		if (b == null) {
			Duty data = new Duty();
			data.setEmpno((String) emp[1]);
			data.setName((String) emp[2]);
			Date cur = new Date();
			data.setDutyDate(cur);
			data.setStart(UtilDAO.gen(cur));
			dutyDAO.add(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您今天已经进行过上班打卡啦！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	@RequestMapping("/over")
	public String over(int id, HttpServletRequest request) {
		BaseDAO dao = new BaseDAO();
		Object[] emp = dao.findSingle("select * from emp where id=" + id, null);

		Map map = new HashMap();
		map.put("empno", (String) emp[1]);
		map.put("dutyDate", UtilDAO.genDate(new Date()));
		List<Duty> list = dutyDAO.query(map);
		if (list != null && list.size() > 0) {
			Duty data = list.get(0);
			data.setOver(UtilDAO.gen(new Date()));
			dutyDAO.update(data);
		}

		return "redirect:show.do";
	}

	@RequestMapping("/del")
	public String del(int id) {
		dutyDAO.del(id);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Duty record = dutyDAO.findById(id);
		request.setAttribute("record", record);
		return "duty/modify";
	}

	@RequestMapping("/update")
	public String update(Duty data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String a = request.getParameter("start");
		String b = request.getParameter("over");
		String c = request.getParameter("dutyDate");
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);

		Object[] z = dao.findSingle("SELECT * FROM duty WHERE id='" + id + "'" + "AND start='" + a + "'" + "AND over='"
				+ b + "'" + "AND dutyDate='" + c + "'", null);

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
			dutyDAO.update(data);
			return "redirect:show.do";
		}

	}

	// 考勤信息_______主任:所有人 普通员工:自己
	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
		if ("主任".equals(role)) {
			List all = dutyDAO.show();
			request.setAttribute("all", all);
		} else {
			List all = dutyDAO.owner((String) user[1]);
			request.setAttribute("all", all);
		}
		return "duty/show";
	}

}
