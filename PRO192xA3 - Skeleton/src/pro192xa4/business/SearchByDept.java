package pro192xa4.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import pro192xa4.entity.Employee;

class SearchByDept {

  @Test
  void testSearchByDept() {
    EmployeeManagement empTest = new EmployeeManagement();
    File file = new File(empTest.getInfo());
    empTest.docFile(file);
    // expect result
    ArrayList<Employee> exp = new ArrayList<>();
    
    //test case 1: if method return many result
    exp.add(empTest.listE.get(0));
    exp.add(empTest.listE.get(1));
    assertEquals(exp.size(), empTest.searchByDept("IT").size());
    assertEquals(exp.get(0), empTest.searchByDept("IT").get(0));
    assertEquals(exp.get(1), empTest.searchByDept("IT").get(1));
    exp.clear();
    
    //test case 2:
    exp.add(empTest.listE.get(2));
    exp.add(empTest.listE.get(3));
    assertEquals(exp.size(), empTest.searchByDept("To").size());
    assertEquals(exp.get(0), empTest.searchByDept("To").get(0));
    assertEquals(exp.get(1), empTest.searchByDept("To").get(1));
    
    //test case 3: 1 result
    exp.clear();
    assertEquals(0, empTest.searchByDept("Văn").size());
  }
}
