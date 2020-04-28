package pro192xa4.entity;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import pro192xa3.entity.Staff;
import pro192xa3.entity.Teacher;

public class GetSalaryTest {

  @Test
  public void testGetSalary() {
    //test case 1: salary of teacher - expect result 3974
    Teacher t = new Teacher();
    t.setSalaryRatio(1.3f);
    t.setTeachingHours(45);
    t.setAllowance(1000);
    assertEquals(3974, t.getSalary());
    
    //test case 2: salary of Staff - expect result 17500
    Staff s = new Staff();
    s.setSalaryRatio(20);
    s.setNoOfWorkingDay(30);
    s.setAllowance(2000);
    assertEquals(17500, s.getSalary());
  }

}
