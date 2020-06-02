package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Dept;

public interface DeptDAO {
	void add(Dept data);

	void del(int id);

	Dept findById(int id);

	void update(Dept data);

	List show();

	List query(Map params);
}
