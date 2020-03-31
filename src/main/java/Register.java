import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register {
    Map<String, Object> register = new HashMap<>();
    Map<Field, Object> fieldsToInject = new HashMap<>();

    public Object get(String name) {
        return register.get(name);
    }

    public void add(String name, Object something) {
        if (register.containsKey(name)) {
            throw new RuntimeException();
        }
        for (Field field : something.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                fieldsToInject.put(field, something);
            }
        }

        register.put(name, something);
    }

    public void add(Object something) {
        add(something.getClass().getName(), something);
    }

    public <T> T get(Class<T> type) {
        return (T) get(type.getName());
    }

    void inject() {
        for (Field field : fieldsToInject.keySet()) {
            Object something = fieldsToInject.get(field);
            Object injection = this.get(field.getType());
            field.setAccessible(true);
            try {
                field.set(something, injection);
            } catch (IllegalAccessException e) {
                throw new  RuntimeException(e);
            }
        }
    }
}
