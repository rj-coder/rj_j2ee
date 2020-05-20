package com.rj.j2ee.jdbc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("prototype")
public class Student implements Serializable {
    private int studentId;
    private String studentName;
    private int subject1;
    private int subject2;
    private int subject3;

    public Student(int studentId, String studentName, int subject1, int subject2, int subject3) {
    	System.out.println("hello in construct");
        this.studentId = studentId;
        this.studentName = studentName;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
    }

    public float getAVGMarks() {
        return (subject1 + subject1 + subject3) / 3.0f;
    }

    public int getTotalMarks() {
        return subject1 + subject2 + subject3;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", subject1=" + subject1 +
                ", subject2=" + subject2 +
                ", subject3=" + subject3 +
                '}';
    }
}
