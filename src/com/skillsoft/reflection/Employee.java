package com.skillsoft.reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Required
public class Employee {

    enum Type{
        FULLTIME,
        CONTRACT
    }


    private static final Random employeeIdGenerator=new Random();

    @Required
    private int employeeId;

    @Required
    public String name="unknown";

    @Required
    public String title="unknown";

    @Required
    @InRange(minValue = 10000,maxValue = 1000000)
    private double salary=0.0;

    @Deprecated
    private String department="unknown";

    public volatile List<String> committees;

    protected Type employeeType=Type.FULLTIME;

     private Employee(){
    }

    public static Employee createEmployee(@Required String name, @Required String title,
                                          @Required @InRange(minValue = 10000, maxValue =1000000)double salary){
         Employee employee=new Employee();

         employee.employeeId=Math.abs(employeeIdGenerator.nextInt());
         employee.name=name;
         employee.title=title;
         employee.salary=salary;

         return employee;
    }
    protected Employee(String name){
        this();
        this.name=name;
    }

    public Employee(String name,String title){
        this(name);
        this.title=title;
    }

    public Employee(String name, String title,double salary){
        this(name,title);

        this.salary=salary;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Required
    public int getEmployeeId() {
        return employeeId;
    }

    public void setName( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    private double computeBonusInternal(float bonusPercentage){
        return salary*bonusPercentage;
    }

    public double computeTotalSalary(float bonusPercentage){
        return salary+computeTotalSalary(bonusPercentage);
    }

    public void incrementSalary(float percentageIncrement){
         salary=salary+percentageIncrement*salary;
    }

    public boolean save()
    throws IllegalAccessException{
         System.out.println("save() with no arguments to the default location");
         return true;
    }

    public boolean save(String filename)
    throws IllegalAccessException,InterruptedException{
         System.out.println("save(filename): " + filename);

         return true;
    }

    public boolean save(String connectionString, String username,String password)
        throws IllegalAccessException,InterruptedException, ExecutionException {
         System.out.println("save(connectionString, username, password");

         return true;
    }

    @Deprecated
    @SuppressWarnings("unchwcked")
    public void printEmployeeDetails(){
         System.out.println(employeeId+name);

         List someList=new ArrayList<>();
         someList.add("element in unchecked list");
    }

    @Deprecated
    public String getDepartment() {
        return department;
    }

    @Deprecated(forRemoval = true,since="v9")
    public void setDepartment(String department){
         this.department=department;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Title: %s, Salary: %f",
                employeeId,name,title,salary);
    }
}
