package UserProcessing;

import java.util.HashMap;

public class UserRepository {
    // 在数据库中保存用户信息的方法
    HashMap<String,User> userHashMap;

    public UserRepository(){
        userHashMap = new HashMap<String, User>();
    }
    public void saveUser(User user) {
        // 实现代码...
        userHashMap.put(user.getUsername(), user);
        System.out.println("保存用户成功");
    }

    // 根据用户名查询用户信息的方法
    public User getUserByUsername(String username) {
        // 实现代码...
        return userHashMap.get(username);
    }
}