package com.dao;
import com.entity.*;
import java.util.*;
import java.util.*;
public interface OffDAO {
   void add(Offs data);
   void del(int id);
   Offs findById(int id);
   void update(Offs data);
   List show();
   List query(Map params);
   List load(List params);
   List owner(String empno);
   List myEmp(String dept);
}
