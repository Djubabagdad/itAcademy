package taskAnnotation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class testAnnotationDemo {
    private static String name;
    private static List<Class<?>> list = new ArrayList<>();


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException,
            IOException, NoSuchMethodException, InstantiationException {
        propertiesStream();
        toList();
        operationSet();

    }

    private static void operationSet() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        for (Class<?> list1 : list) {
            if (list1.isAnnotationPresent(MyAnnotation.class)) {
                Field[] fields = list1.getDeclaredFields();
                Constructor constructor = list1.getConstructor();
                Object object = constructor.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(object, name);
                }
                System.out.println(object);
            }
        }
    }

    private static void toList() {
        list = new ArrayList<>();
        list.add(Person.class);
        list.add(SecondPerson.class);
    }

    private static void propertiesStream() throws IOException {
        Properties properties = new Properties();
        String propFileName = "Properties.properties";
        InputStream inputStream = testAnnotationDemo.class.getClassLoader().getResourceAsStream(propFileName);
        assert inputStream != null;
        properties.load(inputStream);
        name = properties.getProperty("name");
    }
}