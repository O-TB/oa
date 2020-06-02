package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.util.BaseDAO;
import com.util.UtilDAO;

//销假
@Controller
@RequestMapping("/offsApply")
public class OffsApplyController extends BaseController {
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object[] offsId = dao.findSingle("SELECT offsId FROM offs_apply WHERE offsId=" + id, null);
//		int a = (int) offsId[0];
//		int a = Integer.parseInt(request.getParameter("offsId[0]").toString());
//		System.out.println(offsId);
		if (offsId == null) {
			dao.operate("insert into offs_apply values(null,?,?,?)", new Object[] { UtilDAO.genDate(), id, "待审" });
			return "redirect:show.do";
		} else {
			response.setContentType("text/html;charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
			out.println("<script>");
			out.println("alert('已经存在该销假请求，请耐心等待！');");
			out.println("history.go(-1);");
			out.println("</script>");
			return "offs/show";
		}

	}

	@RequestMapping("/del")
	public String del(int id) {
		dao.operate("delete from offs_apply where id=" + id, null);
		return "redirect:show.do";
	}

	@RequestMapping("/yes")
	public String yes(int id, int offsId, HttpServletRequest request) {
		dao.operate("update offs_apply set state=? where id=" + id, new Object[] { "通过" });
		dao.operate("update offs set offTime=? where id=" + offsId, new Object[] { UtilDAO.genDate() });
		return "redirect:show.do";
	}

	@RequestMapping("/no")
	public String no(int id, HttpServletRequest request) {
		dao.operate("update offs_apply set state=? where id=" + id, new Object[] { "驳回" });
		return "redirect:show.do";
	}

	// 销假审核——————普通员工：自己 主任：所有人
	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
		String role = (String) request.getSession().getAttribute("role");
		if ("普通员工".equals(role)) {
			List all = dao.find(
					"select offs_apply.id,offs.empno,offs.name,offs_apply.curtime,offs_apply.state,offs.reason,offs_apply.offsId from offs,offs_apply where offs.id=offs_apply.offsId and offs.empno=?",
					new Object[] { (String) user[1] });
			request.setAttribute("all", all);
		} else {
			List all = dao.find(
					"select offs_apply.id,offs.empno,offs.name,offs_apply.curtime,offs_apply.state,offs.reason,offs_apply.offsId from offs,offs_apply where offs.id=offs_apply.offsId",
					null);
//			List all = dao.find("select offs_apply.id,offs.empno,offs.name,offs_apply.curtime,offs_apply.state,offs_apply.offsId from offs,offs_apply,emp where offs.id=offs_apply.offsId and emp.empno=offs.empno and emp.dept=?", new Object[]{(String)user[10]});
			request.setAttribute("all", all);
		}
		return "offs_apply/show";

	}

}
