package com.by.alex.parking.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
	private final static String DB_NAME = "jdbc:mysql://localhost:3306/parkingappdb?useSSL=false";
	private final static String USER_NAME = "root";
	private final static String PASS = "root";
	private static final ConnectionPool instance = new ConnectionPool();
	private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(5);

	private ConnectionPool() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Connected");
			for (int index = 0; index < pool.remainingCapacity(); index++) {
				pool.add(DriverManager.getConnection(DB_NAME, USER_NAME, PASS));
			}
			System.out.println("Connection Installed");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws InterruptedException {
		return pool.take();
	}

	public void returnConnection(Connection connection) {

		if (connection == null) {
			return;
		}

		try {
			connection.setAutoCommit(true);
			connection.setReadOnly(false);
			pool.put(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public void closePool(){

		for (Connection con : pool) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Connection Closed");
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

}