package com.util;

import java.util.List;

//封装页面显示逻辑
public class Pagination
{
	// 总共的数据量
	private int total;
	// 每页显示多少条
	private int pageSize;
	// 共有多少页
	private int totalPage;
	// 当前是第几页
	private int index;
	// 当前页的数据
	private List data;
	// 连接路径
	private String path;
	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public int getPageSize()
	{
		return pageSize;
	}
	
	public int getTotalPage()
	{
		return (this.total - 1) / this.pageSize + 1;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public List getData()
	{
		return data;
	}
	
	public void setData(List data)
	{
		this.data = data;
	}
	
	public String getPageDisplay()
	{
		StringBuffer displayInfo = new StringBuffer();
		if (index == 0 || pageSize == 0)
		{
			displayInfo.append("没有分页的信息!");
		} else
		{
			displayInfo.append("<div>");
			displayInfo.append("共" + total + "条记录&nbsp;&nbsp;&nbsp;&nbsp;每页<span style='color:#FF0000'>" + pageSize
					+ "</span>条&nbsp;&nbsp;&nbsp;&nbsp;");
			displayInfo.append("第<span style='color:#FF0000'>" + index + "</span>页/共" + this.getTotalPage()
					+ "页&nbsp;&nbsp;&nbsp;&nbsp;");
			// 判断如果当前是第一页 则"首页"和"上一页"失去链接
			if (index == 1)
			{
				displayInfo.append("首页&nbsp;&nbsp;&nbsp;&nbsp;");
				displayInfo.append("上一页&nbsp;&nbsp;&nbsp;&nbsp;");
			} else
			{
				displayInfo.append("<a href='" + path + "index=1'>首页&nbsp;&nbsp;&nbsp;&nbsp;</a>");
				displayInfo.append(
						"<a href='" + path + "index=" + (index - 1) + "'>上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;");
			}
			// 判断如果当前是最后一页则"未页"和"下一页"失去链接
			if (index >= this.getTotalPage())
			{
				displayInfo.append("下一页&nbsp;&nbsp;&nbsp;&nbsp;");
				displayInfo.append("末页&nbsp;&nbsp;&nbsp;&nbsp;");
			} else
			{
				displayInfo.append("<a href='" + path + "index=" + (index + 1) + "'>下一页&nbsp;&nbsp;&nbsp;&nbsp;</a>");
				displayInfo.append(
						"<a href='" + path + "index=" + this.getTotalPage() + "'>末页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			displayInfo.append("</div>");
		}
		return displayInfo.toString();
	}
}
