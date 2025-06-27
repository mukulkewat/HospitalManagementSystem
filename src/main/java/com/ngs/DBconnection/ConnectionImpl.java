package com.ngs.DBconnection;

import java.sql.Connection;

public class ConnectionImpl implements IConnection{

	@Override
	public Connection getConnection() {
		Connection connectionObject = SingletonConnection.getConnectionObject();
		return connectionObject;
	}

}
