package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.util.BaseDAO;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/login")
	public String login(String name, String pwd, String role, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if ("管理员".equals(role)) {
			Object[] user = dao.findSingle("select * from userinfo where name=? and pwd=?", new Object[] { name, pwd });
			if (user != null) {
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("loginUser", user);
				return "index";
			} else {

				response.setContentType("text/html;charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
				out.println("<script>");
				out.println("alert('用户Id或密码不存在，请重新输入！');");
				out.println("history.go(-1);");
				out.println("</script>");

//				JOptionPane.showMessageDialog(null, "用户ID或密码不存在，请重新输入！", "警告！", JOptionPane.INFORMATION_MESSAGE);
				return "login";
			}
		} else {
			Object[] user = dao.findSingle("select * from emp where empno=? and pwd=?", new Object[] { name, pwd });
			if (user != null) {
				request.getSession().setAttribute("role", user[8]);
				request.getSession().setAttribute("deptName", user[10]);
				request.getSession().setAttribute("position", user[10] + "" + user[8]);
				request.getSession().setAttribute("loginUser", user);
				return "index";
			} else {

				response.setContentType("text/html;charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();// 冲刷出流，将所有缓冲的数据强制发送到目的地。
				out.println("<script>");
				out.println("alert('用户Id或密码不存在，请重新输入！');");
				out.println("history.go(-1);");
				out.println("</script>");

//				JOptionPane.showMessageDialog(null, "用户ID或密码不存在，请重新输入！", "警告!", JOptionPane.INFORMATION_MESSAGE);
				return "login";
			}
		}

	}

}
