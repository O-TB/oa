package com.dao;
import com.entity.*;

import java.util.*;
public interface DutyDAO {
   void add(Duty data);
   void del(int id);
   Duty findById(int id);
   void update(Duty data);
   List show();
   List query(Map params);
   List owner(String empno);
}