package com.zhy.pattern.proxy.Custom;

import java.io.*;

public class GPClassLoader extends ClassLoader {
    private File classPathFile;

    public GPClassLoader(){
        String path = GPClassLoader.class.getResource("").getPath();
        classPathFile = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //jvm加载 class 文件

        String className = GPClassLoader.class.getPackage().getName() + "." + name;

        if(classPathFile!=null){
            File classFile = new File(classPathFile, name.replaceAll("\\.", "/")+".class");
            if (classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                     in = new FileInputStream(classFile);
                     out = new ByteArrayOutputStream();
                     byte[] buff = new byte[1024];
                     int len;
                     while((len = in.read(buff))!=-1){
                         out.write(buff,0,len);
                     }
                     //返回代理类一个字节码对象
                     return defineClass(className,out.toByteArray(),0,out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(out!=null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(in!=null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

        }

        return null;
    }
}
