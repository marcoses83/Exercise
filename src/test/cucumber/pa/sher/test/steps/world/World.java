package pa.sher.test.steps.world;

import java.util.HashMap;

public class World {
    private HashMap<String, Object> contextValues = new HashMap<String, Object>();

    public void addValue (String key, Object value) {
        contextValues.put(key, value);
    }

    public Object getValue (String key) {
        return contextValues.get(key);
    }
}
