package com.dao;
import com.entity.*;

import java.util.*;
public interface WorksDAO {
   void add(Works data);
   void del(int id);
   Works findById(int id);
   void update(Works data);
   List show();
   List query(Map params);
   List load(List params);
   List owner(String empno);
   List myEmp(String dept);
}
