package com.util;
import java.sql.*;
import java.util.*;
import com.util.DBManager;

public class BaseDAO {
	Connection con;
	PreparedStatement pst;

	public void operate(String sql, Object[] params) {
		try {
			con = DBManager.getCon();
			pst = con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeCon(con);
		}
	}


	public List find(String sql, Object[] params) {
		List list = new ArrayList();
		try {
			con = DBManager.getCon();
			pst = con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			ResultSet rst = pst.executeQuery();
			int num = rst.getMetaData().getColumnCount();
			while(rst.next()){
				Object[] data = new Object[num];
				for(int i = 0;i<num;i++){
					data[i] = rst.getObject(i+1);
				}
				list.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeCon(con);
		}
		return list;
	}

	public Object[] findSingle(String sql, Object[] params) {
		Object[] data = null;
		try {
			con = DBManager.getCon();
			pst = con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i + 1, params[i]);
				}
			}
			ResultSet rst = pst.executeQuery();
			int num = rst.getMetaData().getColumnCount();
			if(rst.next()){
				data = new Object[num];
				for(int i = 0;i<num;i++){
					data[i] = rst.getObject(i+1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeCon(con);
		}
		return data;
	}

}
