package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.OffDAO;
import com.entity.Offs;
import com.util.BaseDAO;

//请假管理
@Controller
@RequestMapping("/offs")
public class OffsController extends BaseController {
	@Resource
	OffDAO offDAO;
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(Offs data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptName = (String) request.getSession().getAttribute("deptName");
		String a1 = request.getParameter("empno");
		String b1 = request.getParameter("offDate");
		String c1 = request.getParameter("offTime");
		String d1 = request.getParameter("reason");
		Object[] d = dao.findSingle("SELECT * FROM offs WHERE empno='" + a1 + "'" + "AND offDate='" + b1 + "'"
				+ " AND offTime='" + c1 + "'" + "and reason='" + d1 + "'", null);

		if (d == null) {
			data.setPublish("待审");
			data.setDept(deptName);
			offDAO.add(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('当前时间段存在相同申请，无法提交！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}
	}

	@RequestMapping("/del")
	public String del(int id) {
		offDAO.del(id);
		return "redirect:show.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Offs record = offDAO.findById(id);
		request.setAttribute("record", record);
		return "offs/modify";
	}

	@RequestMapping("/yes")
	public String yes(int id, HttpServletRequest request) {
		Offs record = offDAO.findById(id);
		record.setPublish("通过");
		offDAO.update(record);
		return "redirect:show.do";
	}

	@RequestMapping("/no")
	public String no(int id, HttpServletRequest request) {
		Offs record = offDAO.findById(id);
		record.setPublish("驳回");
		offDAO.update(record);
		return "redirect:show.do";
	}

	@RequestMapping("/update")
	public String update(Offs data, int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String a1 = request.getParameter("empno");
		String b1 = request.getParameter("offDate");
		String c1 = request.getParameter("offTime");
		String d1 = request.getParameter("reason");
		Object[] d = dao.findSingle("SELECT * FROM offs WHERE empno='" + a1 + "'" + "AND offDate='" + b1 + "'"
				+ " AND offTime='" + c1 + "'" + "and reason='" + d1 + "'", null);
		Object[] dd = dao.findSingle("SELECT * FROM offs WHERE id='" + id + "'" + "AND offDate='" + b1 + "'"
				+ " AND offTime='" + c1 + "'" + "and reason='" + d1 + "'", null);

		if (dd != null) {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('您未作出修改！');");
//			out.println("if(confirm('您没有作出修改，是否提交?')==false)return false;");
			out.println("window.location.href='show.do';");
			out.println("</script>");
			return null;
		} else if (d == null) {
			offDAO.update(data);
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('当前时间段存在相同申请，无法提交！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return null;
		}

	}

	// 请假信息————主任：对所有人 普通员工 ：自己
	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
//		  String position =  (String)request.getSession().getAttribute("position");
//		  if("人事部主任".equals(position)){
//			List params = new ArrayList();
////			params.add("主任通过");
//			params.add("主任通过");
//			params.add("主任驳回");
//			List all = offDAO.load(params);
//		    request.setAttribute("all", all);
//		  }else if("普通员工".equals(role)){
//			 List all =  offDAO.owner((String)user[1]);
//			 request.setAttribute("all", all);
//		  }else{
//			  List all =  offDAO.myEmp((String)user[10]);
//			  request.setAttribute("all", all); 
//		  }

		if ("普通员工".equals(role)) {
			List all = offDAO.owner((String) user[1]);
			request.setAttribute("all", all);
		} else {
			List all = offDAO.show();
			request.setAttribute("all", all);
		}
		return "offs/show";
	}

}
