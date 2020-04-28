/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package pro192xa4.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import pro192xa4.business.AllowanceCalulator;
import pro192xa4.business.EmployeeManagement;
import pro192xa4.entity.EDegree;
import pro192xa4.entity.EPosition;
import pro192xa4.entity.Employee;
import pro192xa4.entity.Staff;
import pro192xa4.entity.Teacher;

/**
 *
 * @author cat huy thanh fx01399
 */
public class PRO192xA4 {

  static int count = 0;
  static Scanner scan = new Scanner(System.in);
  static EmployeeManagement empMan = new EmployeeManagement();

  public static float inputFloat() {
    do {
      try {
        return scan.nextFloat();
      } catch (Exception e) {
        System.out.print("    Error - type again: ");
        scan.next();
      }
    } while (true);
  }

  public static int inputInt() {
    do {
      try {
        return scan.nextInt();
      } catch (Exception e) {
        System.out.print("    Error - type again: ");
        scan.next();
      }
    } while (true);
  }

  // create an employee by inputing it's attribute values from keyboard
  static Employee createNewImployee() {
    count = empMan.listE.size();
    System.out.print(
        "Do you want to create a Staff or a Teacher (enter S for Staff, otherwise for Teacher)?");
    // accept Staff or Teacher details from keyboard
    String choice = scan.next();
    if (choice.equalsIgnoreCase("s")) {
      Staff s = new Staff();

      // input Staff details
      count++;
      System.out.print("ID: " + count + "\n");
      s.setId(count);
      scan.nextLine();
      System.out.print("Name: ");
      s.setFullName(scan.nextLine());
      System.out.print("Salary ratio: ");
      s.setSalaryRatio(inputFloat());
      scan.nextLine();
      System.out.print("Department: ");
      s.setDepartment(scan.nextLine());
      System.out.print("Position (1 = HEAD; 2 = VICE_HEAD; 3 = STAFF): ");
      int i = inputInt();
      while (i < 1 || i > 3) {
        System.out.println("Error - type again:");
        i = Integer.parseInt(scan.next());
      }
      if (i == 1)
        s.setPosition(EPosition.HEAD);
      if (i == 2)
        s.setPosition(EPosition.VICE_HEAD);
      if (i == 3)
        s.setPosition(EPosition.STAFF);
      System.out.print("Number of working days: ");
      s.setNoOfWorkingDay(inputInt());
      return s;
    } else {
      Teacher t = new Teacher();

      // inputs Teacher details
      count++;
      System.out.print("ID: " + count + "\n");
      t.setId(count);
      scan.nextLine();
      System.out.print("Name: ");
      t.setFullName(scan.nextLine());
      System.out.print("Salary ratio: ");
      t.setSalaryRatio(inputFloat());
      scan.nextLine();
      System.out.print("Faculty: ");
      t.setFaculty(scan.nextLine());
      System.out.print("Degree (1 = BACHELOR; 2 = DOCTOR; 3 = MASTER): ");
      int i = inputInt();
      while (i < 1 || i > 3) {
        System.out.println("Error - type again:");
        i = Integer.parseInt(scan.next());
      }
      if (i == 1)
        t.setDegree(EDegree.BACHELOR);
      if (i == 2)
        t.setDegree(EDegree.DOCTOR);;
      if (i == 3)
        t.setDegree(EDegree.MASTER);
      System.out.print("Number of teaching hours: ");
      t.setTeachingHours(inputInt());
      return t;
    }
  }

  // display a list of employee
  static void display(ArrayList<Employee> listE) {
    System.out.println("Results:");
    System.out.printf("%-14s%-4s%-16s%-12s%-12s%-10s%-12s%-16s%-8s\n", "Staff/Teacher ", "ID",
        "Name", "Fac/Dept", "Deg/Pos", "Sal Ratio", "Allowance", "T.Hours/W.Days", "Salary");
    for (Employee e : listE) {
      if (e instanceof Staff) {
        System.out.printf("%-14s%-4s%-16s%-12s%-12s%-10s%-12s%-16s%-8s\n", "Staff", e.getId(),
            e.getFullName(), ((Staff) e).getDepartment(), ((Staff) e).getPosition(),
            e.getSalaryRatio(), e.getAllowance(), ((Staff) e).getNoOfWorkingDay(), e.getSalary());
      }
      if (e instanceof Teacher) {
        System.out.printf("%-14s%-4s%-16s%-12s%-12s%-10s%-12s%-16s%-8s\n", "Teacher", e.getId(),
            e.getFullName(), ((Teacher) e).getFaculty(), ((Teacher) e).getDegree(),
            e.getSalaryRatio(), e.getAllowance(), ((Teacher) e).getTeachingHours(), e.getSalary());
      }
    }
  }

  public static void main(String[] args) {
    // create employee management object
    // read data from file
    File file = new File(empMan.getInfo());
    if (file.exists()) {
      empMan.docFile(file);
    } else {
      // if path of file's false input file's path
      System.out.print("Load data failed! Type file's location: ");
      try {
        empMan.setPathFile(scan.nextLine());
        empMan.info();
        file = new File(empMan.getInfo());
        if (!file.exists()) {
          System.out.println("File's not existed, auto creat new File");
          file.createNewFile();
        }
        empMan.docFile(file);
      } catch (Exception e) {
        System.out.println("Something was wrong!");
      }
    }

    // menu
    boolean keepRunning = true;
    while (keepRunning) {
      System.out.println("University Staff Management 1.0");
      System.out.println("\t1.Add staff");
      System.out.println("\t2.Search staff by name");
      System.out.println("\t3.Search staff by department/faculty");
      System.out.println("\t4.Display all staff");
      System.out.println("\t5.Edit");
      System.out.println("\t6.Exit");
      System.out.print("Select function (1,2,3,4 5 or 6): ");
      int choice = inputInt();
      switch (choice) {
        case 1:// add staff/teacher
          Employee emp = createNewImployee();
          float allowance = AllowanceCalulator.calculateAllowance(emp);
          emp.setAllowance(allowance);
          empMan.addEmployee(emp);
          break;
        case 2:// search by name
          scan = new Scanner(System.in);
          System.out.print("Type name to search: ");
          String name = scan.nextLine();
          ArrayList<Employee> foundByName = empMan.searchByName(name);
          display(foundByName);
          break;
        case 3:// search by dept
          System.out.print("\tEnter dept/fac to search: ");
          scan = new Scanner(System.in);
          String dept = scan.nextLine();
          ArrayList<Employee> foundByDept = empMan.searchByDept(dept);
          display(foundByDept);
          break;
        case 4:// display all
          ArrayList<Employee> listE = empMan.listAll();
          display(listE);
          break;
        case 5:// edit by ID
          System.out.println("\t1.Edit by ID");
          System.out.println("\t2.Edit by name");
          System.out.print("\t Select: ");
          int choiceEdit = inputInt();
          while (choiceEdit > 2 || choiceEdit < 1) {
            choiceEdit = inputInt();
          }
          scan.nextLine();
          switch (choiceEdit) {
            case 1:
              System.out.print("\tEnter ID to edit: ");
              int idEdit = inputInt();
              ArrayList<Employee> foundByIdEdit = empMan.searchById(idEdit);
              if (foundByIdEdit.size() != 0) {
                display(foundByIdEdit);
              }
              empMan.editById(idEdit);
              break;
            case 2:
              System.out.print("\tEnter name to edit: ");
              String nameEdit = scan.nextLine();
              ArrayList<Employee> foundByNameEdit = empMan.searchByName(nameEdit);
              if (foundByNameEdit.size() > 1) {
                display(foundByNameEdit);
                System.out.print("Choose employee ID to edit ");
                int choiceId = inputInt();
                empMan.editById(choiceId);
              } else if (foundByNameEdit.size() == 1) {
                display(foundByNameEdit);
                empMan.editByName(nameEdit);
              } else {
                System.out.println("Name's not existed");
              }
              break;
          }
          break;
        case 6:// exit
          keepRunning = false;
      }
    }
  }
}
