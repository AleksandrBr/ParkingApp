package com.by.alex.parking.service;

import com.by.alex.parking.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private UserService testService = new UserServiceImpl();

    public static ServiceFactory getInstance() {
	return instance;
    }

    public UserService getUserService() {
	return testService;
    }

}
