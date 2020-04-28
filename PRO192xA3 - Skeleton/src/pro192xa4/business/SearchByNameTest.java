package pro192xa4.business;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import pro192xa4.business.EmployeeManagement;
import pro192xa4.entity.Employee;

class SearchByNameTest {

  @Test
  void testSearchByName() {
    EmployeeManagement empTest = new EmployeeManagement();
    File file = new File(empTest.getInfo());
    empTest.docFile(file);
    // ArrayList store expect result
    ArrayList<Employee> exp = new ArrayList<>();
    
    //test case 1: if method return many result
    exp.add(empTest.listE.get(1));
    assertEquals(exp.size(), empTest.searchByName("Hoàng").size());
    assertEquals(exp.get(0), empTest.searchByName("Hoàng").get(0));
    exp.clear();
    
    //test case 2: 
    assertEquals(4, empTest.searchByName("H").size());
    
    //test case 3: 0 result
    assertEquals(0, empTest.searchByName("Hoàng Hải").size());
  }
}
