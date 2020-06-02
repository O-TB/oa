package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Wages;

public interface WagesDAO {
	void add(Wages data);

	void del(int id);

	Wages findById(int id);

	void update(Wages data);

	List show();

	List query(Map params);

	List owner(String empno);
}
