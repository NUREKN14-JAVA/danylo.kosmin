package kn.kosmin.db;

public class DaoFactoryImpl extends DaoFactory {
	public UserDao getUserDao(){
		UserDao result = null;
		try {
			System.out.println("11");
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			System.out.println("22");
			result = (UserDao) clazz.newInstance();
			System.out.println("33");
			result.setConnectionFactory(getConnectionFactory());
			System.out.println("44");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("eeee");
		}
		return result;
	}
}
