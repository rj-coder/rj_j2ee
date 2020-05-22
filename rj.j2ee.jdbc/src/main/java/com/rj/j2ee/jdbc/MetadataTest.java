package com.rj.j2ee.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MetadataTest {
	@Autowired
	private Connection con;
	@Autowired
	private Statement st;
	@Autowired
	private ResultSet rs;

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context2.xml");
		MetadataTest metaDataTst = ctx.getBean(MetadataTest.class);
		metaDataTst.showStudentsData();
		System.out.println(metaDataTst.con);
		System.out.println(metaDataTst.st);
		System.out.println(metaDataTst.rs);
	}
	public void showStudentsData() throws SQLException {
		
		
		
	}
}

	 
