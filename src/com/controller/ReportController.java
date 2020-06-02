package com.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Report;
import com.util.BaseDAO;

//文档共享
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
	BaseDAO dao = new BaseDAO();

	@RequestMapping("/add")
	public String add(MultipartFile file, Report data, HttpServletRequest request) {
		if (file != null && !file.isEmpty()) {

			try {
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
				dao.operate("insert into report values(null,?,?,?,?,?)", new Object[] { data.getEmpno(), data.getName(),
						data.getTitle(), file.getOriginalFilename(), new Date() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:show.do";
//		

	}

	@RequestMapping("/del")
	public String del(int id) {
		dao.operate("delete from report where id=" + id, null);

		return "redirect:show.do";
	}

	@RequestMapping("/delmy")
	public String delmy(int id) {
		dao.operate("delete from report where id=" + id, null);

		return "redirect:showmy.do";
	}

	@RequestMapping("/findById")
	public String findById(int id, HttpServletRequest request) {
		Object[] record = dao.findSingle("select * from report where id=" + id, null);
		request.setAttribute("record", record);
//		int a = (int) record[0];
//		System.out.println(a);
		return "report/modify";
	}

	@RequestMapping("/update")
	public String update(MultipartFile file, Report data, HttpServletRequest request) {
		if (file != null && !file.isEmpty()) {
			try {
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
//				System.out.println(filePath);//输出路径
//				System.out.println(data.getId());//输出Id
				dao.operate("update report set title=?,doc=? where id=?",
						new Object[] { data.getTitle(), file.getOriginalFilename(), data.getId() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			dao.operate("update report set empno=?,name=?,title=?,doc=? where id=?", new Object[] { data.getEmpno(),
					data.getName(), data.getTitle(), file.getOriginalFilename(), data.getId() });
		}
		return "redirect:showmy.do";
	}

	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
//		String role = (String) request.getSession().getAttribute("role");
//		if("主任".equals(role)){
		List all = dao.find("select * from report", null);
		request.setAttribute("all", all);
//		}else{
//			List all = dao.find("select * from report where empno=?",new Object[]{user[1]});
//		    request.setAttribute("all", all);
//		}
		return "report/show";

	}

	@RequestMapping("/showmy")
	public String showmy(HttpServletRequest request) {
		Object[] user = (Object[]) request.getSession().getAttribute("loginUser");
//		String role = (String) request.getSession().getAttribute("role");
//		if("主任".equals(role)){
//			List all = dao.find("select * from report",null);
//		    request.setAttribute("all", all);
//		}else{
//			List all = dao.find("select * from report",null);
		List all = dao.find("select * from report where empno=?", new Object[] { user[1] });
		request.setAttribute("all", all);
//		}
		return "report/showmy";
	}

	@RequestMapping("/downLoad")
	@ResponseBody
	public void downLoad(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("========" + fileName);
		ServletContext application = req.getServletContext();
		// 文件路径
		String path = application.getRealPath("/upload/");
		File file = new File(path, fileName);
		// 设置响应类型 ==》 告诉浏览器当前是下载操作，我要下载东西
		resp.setContentType("application/x-msdownload");
		// 设置下载时文件的显示类型(即文件名称-后缀) ex：txt为文本类型
//		resp.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"; fileName*=utf-8''" + fileName);
//		resp.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
//		resp.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
		// 下载文件：将一个路径下的文件数据转到一个输出流中，也就是把服务器文件通过流写(复制)到浏览器端
		Files.copy(file.toPath(), resp.getOutputStream());// Files.copy(要下载的文件的路径,响应的输出流)
	}
}
