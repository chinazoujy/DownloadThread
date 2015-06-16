package com.dreamwin.xunlei.db.dao;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;

import com.dreamwin.cclib.db.sober.Sober;
import com.dreamwin.cclib.db.sober.SoberException;


public class SoberFactory {

	private static Logger logger = Logger.getLogger(SoberFactory.class);

	private static Sober sober;

	public static Sober getSober() {
		return sober;
	}

	public static boolean initailize(String packageName, String proxoolAlias, boolean isShowSql) {
		try {
			PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("proxool.properties").getFile());
			sober = new Sober(packageName, new ProxoolConnectionProvider(proxoolAlias));			
			sober.setShowSql(isShowSql);

			return true;
		} catch (SoberException e) {
			logger.error("", e);
			return false;
		} catch (ProxoolException e) {
			logger.error("", e);
			return false;
		}
	}
}
