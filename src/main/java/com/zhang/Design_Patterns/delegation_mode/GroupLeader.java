package com.zhang.Design_Patterns.delegation_mode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 *
 * @author zb 2019/05/26 22:13
 */
public class GroupLeader implements Staff {
    Map<String, Staff> map = new ConcurrentHashMap<>();

    public GroupLeader() {
        map.put("前端", new StaffA());
        map.put("后台", new StaffB());
        map.put("大数据", new StaffC());
    }


    @Override
    public Object excute(String command) {
        map.get(command).excute(command);
        return null;
    }
}
