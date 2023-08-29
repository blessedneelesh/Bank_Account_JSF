package com.cestar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cestar.model.BankAccount;

public class AccountDao {
	public Connection setUpConnection() {
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Loaded the embedded driver.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String user="root";
		String pwd="";
		String url="jdbc:mysql://localhost:3307/bankaccount";
		
		try {
			con=DriverManager.getConnection(url, user, pwd);
			System.out.println("successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public ArrayList<BankAccount> SelectAllEmployee(){
		ArrayList <BankAccount> allAccount=new ArrayList<>(); 
		// setup connection by calling the method setUpConnection()
		
				Connection con=setUpConnection();
				
				// Write the sql query needed as a string
				
				String sql="select * from account";
				
				// For select query we use Statement interface.
				Statement stmt;
				
				try {
					stmt = con.createStatement();
					
					ResultSet rs=stmt.executeQuery(sql);
					
					while(rs.next()) {
						BankAccount emp_from_rs= new BankAccount(rs.getInt("accountId"),rs.getString("accountType"),rs.getString("name"),rs.getString("contact"),rs.getFloat("balance"),rs.getString("createdDate"));
						allAccount.add(emp_from_rs);
					}
					System.out.println(allAccount);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return allAccount;
	}

	public int insertRec(BankAccount acc_from_servlet) {
		System.out.println(acc_from_servlet);
		int status=0;
		Connection con=setUpConnection();
		String sql="insert into account (accountId, accountType,name,contact,balance,createdDate) values(?,?,?,?,?,?)";
		
		try {
		
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,acc_from_servlet.getAccountId());
			pstmt.setString(2, acc_from_servlet.getAccountType());
			pstmt.setString(3, acc_from_servlet.getName());
			pstmt.setString(4, acc_from_servlet.getContact());
			pstmt.setFloat(5, acc_from_servlet.getBalance());
			pstmt.setString(6, acc_from_servlet.getCreatedDate());
			
			status=pstmt.executeUpdate();
			
			if(status>0) {
				System.out.println("record Inserted!!");
			}else {
				System.out.println("TRY again");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return status;
	}
	


	public BankAccount getAccById(int eid) {
		BankAccount acc_from_rs=null;
		Connection con=setUpConnection();
		String sql="select * from account where accountId=?";
		System.out.println(eid);
		try {
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,eid);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
				acc_from_rs= new BankAccount(rs.getInt("accountId"),rs.getString("accountType"),rs.getString("name"),rs.getString("contact"),rs.getFloat("balance"),rs.getString("createdDate"));
			}
		System.out.println(acc_from_rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc_from_rs;
	}

	public int updateAcc(int oid, BankAccount acc) {
		int status=0;
		Connection con=setUpConnection();
		String sql="update account set accountId=?,accountType=?,name=?,contact=?,balance=?,createdDate=? where accountId=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, acc.getAccountId());
			pstmt.setString(2, acc.getAccountType());
			pstmt.setString(3, acc.getName());
			pstmt.setString(4, acc.getContact());
			pstmt.setFloat(5, acc.getBalance());
			pstmt.setString(6, acc.getCreatedDate());
			pstmt.setInt(7, oid);
			
	status=pstmt.executeUpdate();
			
			if(status>0) {
				System.out.println("record Updated!!");
			}else {
				System.out.println("TRY again");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return status;
	}
	
	public int delete(int id) {
		Connection con = setUpConnection();
		int status =0;
		String sql="delete from account where accountId=?";
		
		try {
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1,id);
			
			status=stmt.executeUpdate();
			
			if(status>0) {
				System.out.println("record deleted!!");
			}else {
				System.out.println("TRY again");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
}
