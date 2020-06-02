package com.dao;
import com.entity.*;
import java.util.*;
public interface EmpDAO {
   void add(Emp data);
   void del(int id);
   Emp findById(int id);
   void update(Emp data);
   List show();
   List query(Map params);
   List owner(String empno);
   List myEmp(String dept);
}
