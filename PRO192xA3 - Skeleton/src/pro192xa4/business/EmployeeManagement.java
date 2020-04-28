/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package pro192xa4.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import pro192xa4.entity.EDegree;
import pro192xa4.entity.EPosition;
import pro192xa4.entity.Employee;
import pro192xa4.entity.Staff;
import pro192xa4.entity.Teacher;
import pro192xa4.ui.PRO192xA4;

/**
 *
 * @author hp
 */
public class EmployeeManagement {

  public static Scanner sc = new Scanner(System.in);
  public static String pathFile = "data.txt";

  public String getPathFile() {
    return pathFile;
  }

  public void setPathFile(String str) {
    pathFile = str;
  }

  // store path file data.txt in info.txt
  public void info() {
    File info = new File("info.txt");
    BufferedWriter bw = null;
    FileWriter fw = null;
    try {
      if (!info.exists()) {
        info.createNewFile();
      }
      fw = new FileWriter("info.txt");
      bw = new BufferedWriter(fw);
      bw.write(pathFile);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bw != null) {
          bw.close();
        }
        if (fw != null) {
          fw.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  // get path of file data.txt
  public String getInfo() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("info.txt"));
      int i;
      String line = "";
      while ((i = br.read()) != -1) {
        line += (char) i;
      }
      return line;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return pathFile;
    } catch (IOException e) {
      e.printStackTrace();
      return pathFile;
    } finally {
      try {
        if (br != null)
          br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  // store all staff/teacher
  public ArrayList<Employee> listE;

  public EmployeeManagement() {
    listE = new ArrayList<>();
  }

  public void addEmployee(Employee emp) {
    // add emp to listE
    listE.add(emp);
    ghiFile(emp);
  }

  // write data to file
  public void ghiFile(Employee emp) {
    File file = new File(getInfo());
    BufferedWriter bw = null;
    FileWriter fw = null;
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      fw = new FileWriter(file.getAbsoluteFile(), true);
      bw = new BufferedWriter(fw);
      bw.write(emp.toString() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bw != null) {
          bw.close();
        }
        if (fw != null) {
          fw.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  // read data from file
  public void docFile(File file) {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(getInfo()));
      String line;
      String reg1 = "^Staff,\\s\\d*,\\s[a-zA-Z][^#&<>\"~;$^%{},?]{1,20},\\s[a-zA-Z]"
          + "[^#&<>\"~;$^%{},?]{1,20},\\s[A-Z][^#&<>\"~;$^%{},?]{1,20},"
          + "\\s\\d*[.]\\d*,\\s\\d*,\\s\\d*[.]\\d*,\\s\\d*[.]\\d*";
      String reg2 = "^Teacher,\\s\\d*,\\s[a-zA-Z][^#&<>\"~;$^%{},?]{1,20},\\s[a-zA-Z]"
          + "[^#&<>\"~;$^%{},?]{1,20},\\s[A-Z][^#&<>\"~;$^%{},?]{1,20},"
          + "\\s\\d*[.]\\d*,\\s\\d*,\\s\\d*[.]\\d*,\\s\\d*[.]\\d*";
      while ((line = br.readLine()) != null) {
        String line1 = line.trim();
        if (line1.matches(reg1) || line1.matches(reg2)) {
          if (line1.substring(0, 5).equals("Staff")) {

            Staff s = new Staff();
            String[] str = line1.split(", ");
            s.setId(Integer.parseInt(str[1]));
            s.setFullName(str[2]);
            s.setDepartment(str[3]);
            if (str[4].equals("HEAD")) {
              s.setPosition(EPosition.HEAD);
            }
            if (str[4].equals("VICE_HEAD")) {
              s.setPosition(EPosition.VICE_HEAD);
            }
            if (str[4].equals("STAFF")) {
              s.setPosition(EPosition.STAFF);
            }
            s.setSalaryRatio(Float.parseFloat(str[5]));
            s.setAllowance(Float.parseFloat(str[7]));
            s.setNoOfWorkingDay(Integer.parseInt(str[6]));
            listE.add(s);
          }
          if (line1.substring(0, 7).equals("Teacher")) {
            Teacher t = new Teacher();
            String[] str = line1.split(", ");
            t.setId(Integer.parseInt(str[1]));
            t.setFullName(str[2]);
            t.setFaculty(str[3]);
            if (str[4].equals("BACHELOR")) {
              t.setDegree(EDegree.BACHELOR);
            }
            if (str[4].equals("MASTER")) {
              t.setDegree(EDegree.MASTER);
            }
            if (str[4].equals("DOCTOR")) {
              t.setDegree(EDegree.DOCTOR);
            }
            t.setSalaryRatio(Float.parseFloat(str[5]));
            t.setAllowance(Float.parseFloat(str[7]));
            t.setTeachingHours(Integer.parseInt(str[6]));
            listE.add(t);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null)
          br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  // search by staff/teacher's name
  public ArrayList<Employee> searchByName(String name) {
    // store all matching staff or teacher
    ArrayList<Employee> empFound = new ArrayList<>();
    for (Employee employee : listE) {
      if (employee.getFullName().toLowerCase().contains(name.toLowerCase())) {
        empFound.add(employee);
      }
    }
    return empFound;
  }

  // search by staff/teacher's department/faculty
  public ArrayList<Employee> searchByDept(String dept) {
    ArrayList<Employee> empFound = new ArrayList<>();
    for (Employee employee : listE) {
      if (employee instanceof Staff) {
        if (((Staff) employee).getDepartment().toLowerCase().contains(dept.toLowerCase())) {
          empFound.add(employee);
        }
      } else if (employee instanceof Teacher) {
        if (((Teacher) employee).getFaculty().toLowerCase().contains(dept.toLowerCase())) {
          empFound.add(employee);
        }
      }
    }
    return empFound;
  }

  // search employee by id and display
  public ArrayList<Employee> searchById(int id) {
    ArrayList<Employee> empFound = new ArrayList<>();
    for (Employee employee : listE) {
      if (employee.getId() == id) {
        empFound.add(employee);
      }
    }
    return empFound;
  }

  // edit employee by ID
  public void editById(int id) {
    boolean isExist = false;
    for (int i = 0; i < listE.size(); i++) {
      if (listE.get(i).getId() == id) {
        isExist = true;
        edit(i);
      }
    }
    File file = new File(getInfo());
    file.delete();
    for (Employee employee : listE) {
      ghiFile(employee);
    }
    if (!isExist) {
      System.out.println("Id's not existed");
    }
  }

  // edit by name - search employee by name and edit
  public void editByName(String nameEdit) {
    for (int i = 0; i < listE.size(); i++) {
      if (listE.get(i).getFullName().equals(nameEdit)) {
        edit(i);
      }
    }
    File file = new File(getInfo());
    file.delete();
    for (Employee employee : listE) {
      ghiFile(employee);
    }
  }

  // edit employee index i in list
  public void edit(int i) {
    if (listE.get(i) instanceof Staff) {
      Staff s = (Staff) listE.get(i);
      System.out.print("Edit Name: ");
      s.setFullName(sc.nextLine());
      System.out.print("Edit Department: ");
      s.setDepartment(sc.nextLine());
      System.out.print("");
      System.out.print("Position (1 = HEAD; 2 = VICE_HEAD; 3 = STAFF): ");
      int j = PRO192xA4.inputInt();
      while (j < 1 || j > 3) {
        System.out.println("Error - type again:");
        j = PRO192xA4.inputInt();
      }
      if (j == 1)
        s.setPosition(EPosition.HEAD);
      if (j == 2)
        s.setPosition(EPosition.VICE_HEAD);
      if (j == 3)
        s.setPosition(EPosition.STAFF);
      System.out.print("Sal Ratio: ");
      s.setSalaryRatio(PRO192xA4.inputFloat());
      System.out.print("Number of working days: ");
      s.setNoOfWorkingDay(PRO192xA4.inputInt());
      listE.set(i, s);
    }
    if (listE.get(i) instanceof Teacher) {
      Teacher t = (Teacher) listE.get(i);
      System.out.print("Edit Name: ");
      t.setFullName(sc.nextLine());
      System.out.print("Edit Faculty: ");
      t.setFaculty(sc.nextLine());
      System.out.print("Position (1 = BACHELOR; 2 = DOCTOR; 3 = MASTER): ");
      int j = PRO192xA4.inputInt();
      while (j < 1 || j > 3) {
        System.out.println("Error - type again:");
        j = PRO192xA4.inputInt();
      }
      if (j == 1)
        t.setDegree(EDegree.BACHELOR);
      if (j == 2)
        t.setDegree(EDegree.DOCTOR);
      if (j == 3)
        t.setDegree(EDegree.MASTER);
      System.out.print("Sal Ratio: ");
      t.setSalaryRatio(PRO192xA4.inputFloat());
      System.out.print("Number of teaching hours: ");
      t.setTeachingHours(PRO192xA4.inputInt());
      listE.set(i, t);
    }
  }

  // sort the list employee
  public ArrayList<Employee> listAll() {
    // sort the list of staff/teacher before return
    Collections.sort(listE);
    return listE;
  }
}
