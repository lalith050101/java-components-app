package implementation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class ImplementationFramework {

	private static ArrayList<Class<?>> interfaces = new ArrayList<Class<?>>();

	Object obj[];

	public static void setInterface(Class c) {
		interfaces.add(c);
	}

	public static Object getObject(Object[] objects) {
		Object obj = Proxy.newProxyInstance(objects[0].getClass().getClassLoader(), getInterfaces(),
				new MyInvocationHandler(objects));
		return obj;
	}

	public static Class[] getInterfaces() {
		Class[] classes = new Class[interfaces.size()];
		return interfaces.toArray(classes);
	}
}

class MyInvocationHandler implements InvocationHandler {
	Object obj[];

	public MyInvocationHandler(Object obj[]) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object r = null;
		for (Object o : obj) {
			if (o != null) {
				Method m[]=o.getClass().getDeclaredMethods();
				for(Method mm:m) {
					mm.setAccessible(true);
				}
				try {
					r = method.invoke(o, args);
				} catch (Exception e) {
				}
			}
		}
		return r;
	}

}
