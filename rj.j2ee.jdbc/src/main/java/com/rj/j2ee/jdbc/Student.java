package com.rj.j2ee.jdbc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("prototype")
enum  Result{
	PASS,FAIL;
}
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private String studentName;
	private Integer subject1;
	private Integer subject2;
	private Integer subject3;

	public Integer getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public Integer getSubject1() {
		return subject1;
	}
	public Result getResult() {
		if(subject1 < 35 || subject2 < 35 || subject3 < 35)
			return Result.FAIL;
		else 
			return Result.PASS;
			
	}

	public Integer getSubject2() {
		return subject2;
	}

	public Integer getSubject3() {
		return subject3;
	}

	public Student(int studentId, String studentName, int subject1, int subject2, int subject3) {
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
		return "Student{" + "studentId=" + studentId + ", studentName='" + studentName + '\'' + ", subject1=" + subject1
				+ ", subject2=" + subject2 + ", subject3=" + subject3 + '}';
	}
}
