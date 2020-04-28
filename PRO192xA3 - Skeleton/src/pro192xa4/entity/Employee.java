/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package pro192xa4.entity;

/**
 *
 * @author hp
 */
public abstract class Employee implements Comparable<Employee> {

  private int id;// Id : duy nhất và không trùng giữa các nhân viên
  private String fullName;
  private float salaryRatio;// hệ số lương

  private float allowance;// phụ cấp

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public float getSalaryRatio() {
    return salaryRatio;
  }

  public void setSalaryRatio(float salaryRatio) {
    this.salaryRatio = salaryRatio;
  }

  public float getAllowance() {
    return allowance;
  }

  public void setAllowance(float allowance) {
    this.allowance = allowance;
  }

  public abstract float getSalary();

  @Override
  public int compareTo(Employee emp) {
    return this.fullName.compareTo(emp.fullName);
  }
}
