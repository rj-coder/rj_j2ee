package com.rj.j2ee.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.*;

@Component
public class StudentImpl {
    private static String studentName;
    private static int studentId;
    private static int subject1;
    private static int subject2;
    private static int subject3;


    
    private Scanner scanner;

    public static void main(String[] args) throws IOException {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        StudentImpl si = ctx.getBean(StudentImpl.class);    	
        si.scanner = new Scanner(System.in);
        System.out.println("enter student id: ");
        studentId = si.scanner.nextInt();
        si.scanner.nextLine();
        System.out.println("enter student name: ");
        studentName = si.scanner.next();
        System.out.println("enter subject 1 marks: ");
        subject1 = si.scanner.nextInt();
        System.out.println("enter subject 2 marks: ");
        subject2 = si.scanner.nextInt();
        System.out.println("enter subject 3 marks: ");
        subject3 = si.scanner.nextInt();
        					
        Student student = ctx.getBean(Student.class,studentId,studentName,subject1,subject2,subject3);
        System.out.println("Total Marks: "+student.getTotalMarks());
        FileOutputStream fOut;
        fOut = new FileOutputStream("abc.txt");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(student);
        oOut.close();
        fOut.close();

    }

}
