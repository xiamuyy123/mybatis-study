package com.yq;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavassistTest {
    @Test
    public void test2() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.yq.dao.impl.AccountDaoImpl");

        CtClass ctInterface = pool.makeInterface("com.yq.dao.AccountDao");

        ctClass.addInterface(ctInterface);
        String methodCode = "    public void delete(){\n" +
                "        System.out.println(\"delete\");\n" +
                "    }";

        CtMethod method = CtMethod.make(methodCode, ctClass);

        ctClass.addMethod(method);
        Class<?> clazz = ctClass.toClass();

        Method insert = clazz.getDeclaredMethod("delete");
        Object o = clazz.newInstance();
        insert.invoke(o,null);
    }
    @Test
    public void test1() throws CannotCompileException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.yq.dao.impl.AccountDaoImpl");
        String methodCode = "    public void insert(){\n" +
                "        System.out.println(\"123\");\n" +
                "    }";
        CtMethod method = CtMethod.make(methodCode, ctClass);

        ctClass.addMethod(method);
        ctClass.toClass();

        Class<?> clazz = Class.forName("com.yq.dao.impl.AccountDaoImpl");
        Method insert = clazz.getDeclaredMethod("insert");
        Object o = clazz.newInstance();
        insert.invoke(o,null);
    }
    public void insert(){
        System.out.println("123");
    }
}
