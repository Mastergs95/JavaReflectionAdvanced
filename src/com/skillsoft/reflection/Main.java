package com.skillsoft.reflection;
import java.io.FileWriter;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

        /*Class<?> employeeClass= Class.forName("com.skillsoft.reflection.Employee");

        Employee employee = (Employee) employeeClass.getConstructor().newInstance();

        System.out.println(employee);

        System.out.println("*** Setting name and title ***");

        Field nameField = employeeClass.getField("name");
        nameField.set(employee,"Jason");

        Field titleField= employeeClass.getField("title");
        titleField.set(employee, "Analyst");

        System.out.println(employee);


        System.out.println("*** Setting salary ***");

        Field salaryField = employeeClass.getDeclaredField("salary");

        salaryField.setAccessible(true);
        salaryField.setDouble(employee,12500);

        System.out.println(employee);

        System.out.println("*** Setting employeeID ***");

        Field employeeIdField= employeeClass.getDeclaredField("employeeId");

        employeeIdField.setAccessible(true);
        employeeIdField.setInt(employee,1001);

        System.out.println(employee);*/

        /*System.out.println("public fields");

        Field[] fields= employeeClass.getFields();

        for(Field field:fields){
            System.out.println(field);
        }
        System.out.println();

        fields = employeeClass.getDeclaredFields();

        for(Field field:fields){
            System.out.println(field);

            int modifier=field.getModifiers();
            System.out.println(field.getName());
            System.out.println(Integer.toBinaryString(modifier));
            System.out.println(Modifier.toString(modifier));
        }
        System.out.println();

        Field salaryField=employeeClass.getDeclaredField("salary");

        System.out.println(salaryField);*/



        /*Field departmentField = employeeClass.getDeclaredField("department");
        Field committeesField = employeeClass.getDeclaredField("committees");
        Field employeeTypeField = employeeClass.getDeclaredField("employeeType");

        class Engineering extends Department{
            Engineering(){
                super("Engineering");
            }
        }

        departmentField.setAccessible(true);
        departmentField.set(employee, new Engineering());

        List<String> committeesList = new ArrayList<>();
        committeesList.add("Promotion");
        committeesList.add("Christmas");

        committeesField.set(employee,committeesList);

        employeeTypeField.setAccessible(true);
        employeeTypeField.set(employee, Employee.Type.CONTRACT);

        System.out.println(departmentField.get(employee));
        System.out.println(committeesField.get(employee));
        System.out.println(employeeTypeField.get(employee));*/

  Class<?> employeeClass= Class.forName("com.skillsoft.reflection.Employee");

  Employee employee = (Employee) employeeClass.getDeclaredConstructor().newInstance();

/*         System.out.println("accessing methods properties");

        Method[]methods=employeeClass.getDeclaredMethods();

        for(Method method:methods){
            if(isGetter(method)){
                System.out.println(method.getName() + " getter");

            }else{
                System.out.println(method.getName() + " setter");
            }
        }

        for(Method method:employeeClass.getDeclaredMethods()){
            if(isSetter(method)) {
                if (method.getName().equals("setName")) {
                    method.invoke(employee, "Nora Roberts");
                } else if (method.getName().equals("setTitle")) {
                    method.invoke(employee, "Accounts Director");
                } else if (method.getName().equals("setSalary")) {
                    method.invoke(employee, 50000);
                }
            }
        }*/

        Method saveMethod= employeeClass.getDeclaredMethod("save",
                String.class, String.class, String.class);

        saveMethod.setAccessible(true);

        saveMethod.invoke("<some connection string>", "<some username>", "<some username>");

        Method computeBonusInternal = employeeClass.getDeclaredMethod("computeBonusInternal",
                float.class);

        computeBonusInternal.setAccessible(true);

        System.out.println(computeBonusInternal.invoke(employee,0.1f));

        


    }

    private static boolean isGetter(Method method){

        if(!method.getName().startsWith("get")){
            return false;
        }

        if(!((method.getModifiers() & Modifier.PUBLIC)==Modifier.PUBLIC)){
            return false;
        }

        if(method.getReturnType().equals(void.class)){
            return false;
        }

        if(method.getParameterCount()!=0){
            return false;
        }
        return true;
    }

    private static boolean isSetter(Method method){

        if(!method.getName().startsWith("set")){
            return false;
        }

        if(!((method.getModifiers() & Modifier.PUBLIC)==Modifier.PUBLIC)){
            return false;
        }

        if(method.getReturnType().equals(void.class)){
            return false;
        }

        if(method.getParameterCount()!=1){
            return false;
        }
        return true;
    }





}
