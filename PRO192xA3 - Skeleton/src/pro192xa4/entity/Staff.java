/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro192xa4.entity;
/**
 *
 * @author hp
 */
public class Staff extends Employee{
    private String department;//phòng ban
    private int noOfWorkingDay;//số ngày công
    private EPosition position;//chức vụ

    public Staff() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNoOfWorkingDay() {
        return noOfWorkingDay;
    }

    public void setNoOfWorkingDay(int noOfWorkingDay) {
        this.noOfWorkingDay = noOfWorkingDay;
    }

    public EPosition getPosition() {
        return position;
    }

    public void setPosition(EPosition position) {
        this.position = position;       
    }
    //sal=Hệ số lương*730+ phụ cấp + số ngày công*30;
    @Override
    public float getSalary() {
        float sal;     
        sal = this.getSalaryRatio()*730 +this.getAllowance()+this.noOfWorkingDay*30;
        return sal;
    }
    
    @Override
    public String toString(){
      return "Staff, "+this.getId() + ", " + this.getFullName() + ", " + this.getDepartment() + ", "
          + this.getPosition() + ", " + this.getSalaryRatio() + ", " + this.getNoOfWorkingDay() + ", "
          + this.getAllowance()+ ", "+this.getSalary();
    }
    
}
