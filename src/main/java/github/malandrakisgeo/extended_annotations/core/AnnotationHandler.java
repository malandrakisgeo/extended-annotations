package github.malandrakisgeo.extended_annotations.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
public class AnnotationHandler {

  public static boolean isChildAnnotation(Class < ? extends Annotation > possibleParent, Class < ? extends Annotation > possibleChild) {

    if (possibleChild.getAnnotation(possibleParent) == null) {
      return false;
    }

    try {
      return valuesOk(possibleParent.getName(), possibleChild.getName());
    } catch (Exception e) {
      return false;
    }

  }

  private static boolean valuesOk(String parent, String child) throws Exception {
    AtomicBoolean returned = new AtomicBoolean(true);

    var parentMethods = Arrays.stream(Class.forName(parent).getDeclaredMethods()).toList();
    var childMethods = Arrays.stream(Class.forName(child).getDeclaredMethods()).toList();
    HashMap < String, String > nameAndRetType = new HashMap < > ();

    childMethods.forEach(method -> {
      nameAndRetType.put(method.getName(), method.getReturnType().getName());
    });

    parentMethods.forEach(method -> {
      var childMethodName = nameAndRetType.get(method.getName());
      if (childMethodName == null || !childMethodName.equals(method.getReturnType().getName())) {
        returned.set(false);
      }
    });

    return returned.get();
  }

  public static Object getAsParent(Class < ? extends Annotation > parent, Annotation child) {
    if (!isChildAnnotation(parent, child.annotationType())) {
      return null;
    }
    var exists = child.annotationType().getDeclaredAnnotation(parent);
    try {

      var annotation = Proxy.newProxyInstance(
        Class.forName(exists.annotationType().getName()).getClassLoader(),
        new Class[] {
          exists.annotationType()
        },
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) {
            try {
              return Proxy.getInvocationHandler(child).invoke(child, method, args);
            } catch (Throwable e) {
              e.printStackTrace();
            }
            return null;
          }
        });
      return annotation;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
}
