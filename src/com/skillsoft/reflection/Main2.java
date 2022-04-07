package com.skillsoft.reflection;

import java.io.FilterOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main2 {

    private static void printParameter(
            Class<?>[] parameterTypes, Annotation[][]parameterAnnotations){

        int index=0;
        for(Class<?> parameterType:parameterTypes){
            System.out.println(parameterType);

            Annotation[]parameterAnnotation=parameterAnnotations[index];
            for(Annotation annotation:parameterAnnotation){
                System.out.println(annotation);
            }
            index++;
        }
    }

    private static void checkEmployeeObjectForValidity(Employee employee)
            throws IllegalAccessException{

        boolean valid=true;
        Field[]fields=employee.getClass().getDeclaredFields();

        for(Field field:fields) {
            Annotation[] annotations = field.getAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof Required) {
                    field.setAccessible(true);

                    if (field.get(employee) == null) {
                        valid = false;
                        System.out.println("Field is null" + field.getName());

                    }
                }

                if (annotation instanceof InRange) {
                    InRange inRange = (InRange) annotation;

                    double value = field.getDouble(employee);

                    if (value < inRange.minValue() || value > inRange.maxValue()) {
                        valid = false;
                        System.out.println(String.format("Field is not in range: %s (%.1f %.1f)",
                                field.getName(), inRange.minValue(), inRange.maxValue()));
                    }
                }
            }
        }
            if(valid){
                System.out.println("All fields are valid!" + employee);
            }else{
                System.out.println("Please fix issues with invalid fields " + employee);
            }

    }

    public static void main(String[]args)
            throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {




        Employee employee =new Employee("John", "VP",120000);
        checkEmployeeObjectForValidity(employee);

        Employee employee2 =new Employee("Abbie", "Manager",5000);
        checkEmployeeObjectForValidity(employee2);




    }
}
