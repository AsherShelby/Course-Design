package UserProcessing;

public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 用户注册的方法
    public boolean registerUser(String username, String password) {
        // 检查用户名是否已存在
        if (userRepository.getUserByUsername(username) != null) {
            System.out.println("用户名已存在");
            return false;
        }

        // 创建新用户对象
        User user = new User(username, password);
        user.setUsername(username);
        user.setPassword(password);
        // 设置其他用户信息...

        // 保存用户信息到数据库
        userRepository.saveUser(user);

        System.out.println("用户注册成功！");

        return true;
    }

    // 用户登录的方法
    public boolean loginUser(String username, String password) {
        // 查询用户信息
        User user = userRepository.getUserByUsername(username);

        // 检查用户是否存在及密码是否匹配
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("用户名或密码错误！");
            return false;
        }

        System.out.println("用户登录成功！");
        // 处理登录成功后的逻辑...
        return true;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}