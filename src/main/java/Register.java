import java.util.HashMap;
import java.util.Map;

public class Register {
    Map<String, Object> register = new HashMap<>();

    public Object get(String name) {
        return register.get(name);
    }

    public void add(String name, Object something) {
        register.put(name, something);
    }
}
