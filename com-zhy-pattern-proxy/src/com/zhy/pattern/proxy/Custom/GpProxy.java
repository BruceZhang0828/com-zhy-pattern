package com.zhy.pattern.proxy.Custom;

import com.zhy.pattern.proxy.jdk.Person;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义动态代理的过程
 */
public class GpProxy  {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader loader,

                     Class<?>[] interfaces, GPInvocationHandler h){
        try {
        //1.动态生成源代码.java
        String source = generateSrc(interfaces);
        //2.java文件输出磁盘
        String filePath = GpProxy.class.getResource("").getPath();
        System.out.println(filePath);
        File f = new File(filePath+"$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(source);
            fw.flush();
            fw.close();

            //3.将.java文编译为.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();
            //4.编译生成的.class文件加载到jvm中
            Class proxyClass = loader.findClass("$Proxy0");

            Constructor constructor = proxyClass.getConstructor(GPInvocationHandler.class);
            //f.delete();//jvm加载完成对象之后 将编译之后的文件进行删除

            //5.返回字节码文件重组之后的代理对象

            return constructor.newInstance(h);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return  null;
    }

    /**
     * 生成.java文件
     * @param interfaces
     * @return
     */
    private static String  generateSrc(Class<?>[] interfaces) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("package com.zhy.pattern.proxy.Custom;"+ln);
        stringBuffer.append("import com.zhy.pattern.proxy.jdk.Person;"+ln);
        stringBuffer.append("import java.lang.reflect.Method;"+ln);
        stringBuffer.append("public final class $Proxy0 implements "+interfaces[0].getName()+" {"+ln);
            stringBuffer.append("GPInvocationHandler h;"+ln);
            stringBuffer.append(" public $Proxy0(GPInvocationHandler h){"+ln);
                stringBuffer.append("this.h = h;"+ln);
            stringBuffer.append("}"+ln);
            //实现接口下的所有的方法
            for (Method m:interfaces[0].getMethods()){
                stringBuffer.append("public "+m.getReturnType().getName()+" "+m.getName()+"()"+"{"+ln);
                    stringBuffer.append("try{"+ln);
                        stringBuffer.append("Method m = "+interfaces[0].getName()+".class.getMethod(\""+m.getName()+"\",new Class[]{});"+ln);
                        //方法执行....
                        stringBuffer.append("this.h.invoke(this,m,null);"+ln);
                    stringBuffer.append("}catch(Throwable t){"+ln);
                    stringBuffer.append("t.printStackTrace();" + ln);
                    stringBuffer.append("}"+ln);

                stringBuffer.append("}"+ln);
            }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
