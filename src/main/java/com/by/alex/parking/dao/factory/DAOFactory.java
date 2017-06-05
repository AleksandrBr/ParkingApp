package com.by.alex.parking.dao.factory;

import com.by.alex.parking.dao.UserDAO;
import com.by.alex.parking.dao.impl.UserDAO_Impl;

public class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactory();
	private final UserDAO userDAO = new UserDAO_Impl();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}
