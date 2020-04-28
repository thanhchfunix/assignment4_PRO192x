/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package pro192xa4.entity;
/**
 *
 * @author hp
 */
public class Teacher extends Employee {

  private String faculty;// faculty
  private EDegree degree;// Degree
  private int teachingHours;// Teaching hours

  public Teacher() {
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public EDegree getDegree() {
    return degree;
  }

  public void setDegree(EDegree degree) {
    this.degree = degree;
  }

  public int getTeachingHours() {
    return teachingHours;
  }

  public void setTeachingHours(int teachingHours) {
    this.teachingHours = teachingHours;
  }

  // salary = sal ratio * 730 + allowance + số gi�? dạy * 45
  @Override
  public float getSalary() {
    float sal;
    sal = this.getSalaryRatio() * 730 + this.getAllowance() + this.teachingHours * 45;
    return sal;
  }

  @Override
  public String toString() {
    return "Teacher, "+ this.getId() + ", " + this.getFullName() + ", " + this.getFaculty() + ", "
        + this.getDegree() + ", " + this.getSalaryRatio() + ", " + this.getTeachingHours() + ", " 
        + this.getAllowance()+", "+ this.getSalary();
  }

}
