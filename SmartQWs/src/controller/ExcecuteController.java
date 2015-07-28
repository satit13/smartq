package controller;

import java.sql.SQLException;
import java.sql.Statement;

import connect.QueueConnect;

public class ExcecuteController {
	
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private boolean isSuccess;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean execute(String dbName,String sql) {
		
		try {
			Statement stmt = ds.getStatement(dbName);
	
			stmt.execute(sql);
			if (stmt != null) {
				stmt.close();
			}
			isSuccess=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess=false;
		} finally {
			
			ds.close();
			
		}
		
		return isSuccess;
	}

}
