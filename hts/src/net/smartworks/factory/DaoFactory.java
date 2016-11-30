/*	
 * file 		 : DaoFactory.java
 * created by    : kmyu
 * creation-date : 2016. 11. 15.
 */

package net.smartworks.factory;

import net.smartworks.dao.IDao;

public class DaoFactory {
	
	private static DaoFactory factory;

	private IDao dao;
	
	public synchronized static DaoFactory createInstance() {
		if(factory == null) 
			factory = new DaoFactory();
		return factory;
	}
	public static DaoFactory getInstance() {
		return factory;
	}
	public IDao getDao() {
		return dao;
	}
	public void setDao(IDao dao) {
		this.dao = dao;
	}
	
}

