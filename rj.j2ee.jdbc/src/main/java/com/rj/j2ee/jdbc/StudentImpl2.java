package com.rj.j2ee.jdbc;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

@Component
public class StudentImpl2 {
	public static void main(String[] args) throws Exception {
		FileInputStream fIn;
		fIn = new FileInputStream("abc.txt");
		ObjectInputStream oIn = new ObjectInputStream(fIn);
		Student student = (Student) oIn.readObject();
		System.out.println(student);
		System.out.println("Total Marks: " + student.getTotalMarks());
		oIn.close();
		fIn.close();

	}

}
