package com.rj.j2ee.jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.Box;
import java.awt.TextField;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResultWindow {
	private ApplicationContext ctx;
	private JFrame frame;
	private ResultController rs;
	protected Student student;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultWindow window = new ResultWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public ResultWindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		ctx = new ClassPathXmlApplicationContext("result-context.xml");
		rs = ctx.getBean(ResultController.class);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.RED);
		frame.setBounds(100, 100, 1019, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(10, 2, 0, -2));

		JPanel headingPanel = new JPanel();
		frame.getContentPane().add(headingPanel);
		headingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblResults = new JLabel("Results");
		headingPanel.add(lblResults);
		lblResults.setForeground(Color.RED);
		lblResults.setBackground(Color.WHITE);
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 27));

		JPanel idPanel = new JPanel();
		frame.getContentPane().add(idPanel);
		idPanel.setForeground(Color.GREEN);
		idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblStudentId_1 = new JLabel("Student Id  ");
		lblStudentId_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStudentId_1.setBackground(SystemColor.activeCaption);
		idPanel.add(lblStudentId_1);

		final JComboBox<Integer> idComboBox = new JComboBox<Integer>();
		idComboBox.setMaximumRowCount(5);
		try {
			for (Integer item : rs.getStudentIds()) {
				idComboBox.addItem(item);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "No Student id's found");
		}
		idComboBox.setSelectedIndex(-1);
		idPanel.add(idComboBox);

		JPanel headindPanel2 = new JPanel();
		frame.getContentPane().add(headindPanel2);
		headindPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblDetails = new JLabel("Details");
		lblDetails.setForeground(SystemColor.activeCaptionBorder);
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDetails.setBackground(Color.WHITE);
		headindPanel2.add(lblDetails);

		JPanel namePanel = new JPanel();
		frame.getContentPane().add(namePanel);

		JLabel lblStudentName_1 = new JLabel("Student Name : ");
		namePanel.add(lblStudentName_1);
		lblStudentName_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStudentName_1.setBackground(SystemColor.activeCaption);

		final TextField stdName = new TextField();
		stdName.setFont(new Font("Arial", Font.BOLD, 16));
		namePanel.add(stdName);
		stdName.setEditable(false);
		stdName.setColumns(20);

		JPanel marksPanel1 = new JPanel();
		frame.getContentPane().add(marksPanel1);
		marksPanel1.setForeground(Color.BLACK);
		marksPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblStudentId = new JLabel("Marks 1  :        ");
		marksPanel1.add(lblStudentId);
		lblStudentId.setBackground(SystemColor.activeCaption);
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 18));

		final TextField stdMarks1 = new TextField();
		stdMarks1.setFont(new Font("Arial", Font.BOLD, 16));
		stdMarks1.setColumns(20);
		stdMarks1.setEditable(false);
		marksPanel1.add(stdMarks1);

		JPanel marksPanel3 = new JPanel();
		marksPanel3.setForeground(Color.BLACK);
		frame.getContentPane().add(marksPanel3);
		marksPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblSubject = new JLabel("Marks 2  :        ");
		lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubject.setBackground(SystemColor.activeCaption);
		marksPanel3.add(lblSubject);

		final TextField stdMarks3 = new TextField();
		stdMarks3.setFont(new Font("Arial", Font.BOLD, 16));
		stdMarks3.setEditable(false);
		stdMarks3.setColumns(20);
		marksPanel3.add(stdMarks3);

		JPanel marksPanel2 = new JPanel();
		frame.getContentPane().add(marksPanel2);
		marksPanel2.setForeground(Color.BLACK);
		marksPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblStudentName = new JLabel("Marks 3  :        ");
		lblStudentName.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStudentName.setBackground(SystemColor.activeCaption);
		marksPanel2.add(lblStudentName);

		final TextField stdMarks2 = new TextField();
		stdMarks2.setFont(new Font("Arial", Font.BOLD, 16));
		stdMarks2.setEditable(false);
		stdMarks2.setColumns(20);
		marksPanel2.add(stdMarks2);

		JPanel resPanel = new JPanel();
		frame.getContentPane().add(resPanel);

		JButton resultBtn = new JButton("Result");

		resPanel.add(resultBtn);
		resultBtn.setBackground(SystemColor.menu);

		JPanel stsPanel = new JPanel();
		stsPanel.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(stsPanel);

		JLabel lblPass = new JLabel("Result: ");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPass.setBackground(SystemColor.activeCaption);
		stsPanel.add(lblPass);

		final TextField stsText = new TextField();
		stsText.setForeground(UIManager.getColor("Button.background"));
		stsText.setEditable(false);
		stsText.setColumns(20);
		stsPanel.add(stsText);

		idComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					student = rs.getStudentDetails((Integer) idComboBox.getSelectedItem());
					stdName.setText(student.getStudentName());
					stdMarks1.setText(student.getSubject1().toString());
					stdMarks2.setText(student.getSubject2().toString());
					stdMarks3.setText(student.getSubject3().toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				frame.revalidate();
			}
		});

		resultBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stsText.setForeground(Color.BLACK);
				if (student.getResult() == Result.PASS) {
					stsText.setBackground(Color.GREEN);
					stsText.setText("PASS");

				}else
				{
					stsText.setBackground(Color.RED);
					stsText.setText("FAIL");
				}
					
			}
		});
		
	}

}
