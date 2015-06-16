package com.dreamwin.xunlei.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dreamwin.cclib.db.sober.ConnectionProvider;

public class ProxoolConnectionProvider implements ConnectionProvider {

	private String alias;

	public ProxoolConnectionProvider(String alias) {
		this.alias = alias;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(String.format("proxool.%s", this.alias));
	}
}
