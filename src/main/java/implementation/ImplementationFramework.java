package implementation;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ImplementationFramework {

	private static List<Class<?>> interfaces = new ArrayList<Class<?>>();

	Object obj[];

	public static void setInterface(Class c) {
		interfaces.add(c);
	}

	public static void setInterfaces(Class[] c) {
		interfaces = Arrays.asList(c);
//		for(Class cl: c) {
//			interfaces.add(cl);
//		}
	}

	public static Object getObject(Object o, Object[] objects) {
		Object obj = Proxy.newProxyInstance(o.getClass().getClassLoader(), getInterfaces(),
				new MyInvocationHandler(objects));
		return obj;
	}

	public static Class[] getInterfaces() {
		Class[] classes = new Class[interfaces.size()];
		return interfaces.toArray(classes);
	}
}

class MyInvocationHandler implements InvocationHandler, Serializable {
	Object obj[];

	public MyInvocationHandler(Object obj[]) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Object r = null;
		for (Object o : obj) {
			if (o != null) {
				Method m[] = o.getClass().getDeclaredMethods();
				for (Method mm : m) {
					mm.setAccessible(true);
				}
				try {
					r = method.invoke(o, args);
					break;
				} catch (Exception e) {
				}
			}
		}
		return r;
	}

}
