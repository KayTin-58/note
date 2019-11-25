package zhang.com;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/9/23
 */
public class Storage {
    private String key;
    private String value;
    private ConcurrentHashMap<String, Map<String, String>> map = new ConcurrentHashMap<>();

    public void set(String key, String value) {
        this.key = key;
        this.value = value;
        String[] strs = key.split(".");
        for (int i = 0; i < strs.length; i++) {
            // 这里还没想完整 稍等

        }
    }


    public String get(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(key).append(":").append(map.get(key));
        return sb.toString();
    }


}
