package Interface;
import UserProcessing.UserController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements Runnable{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private UserController userController;
    private boolean Login;

    public UserInterface( UserController c) {
        setTitle("ZR_社交平台");
        setPreferredSize(new Dimension(300, 200));
        setLocation(250,300);
        setLogin(false);
        this.userController = c;
        try {
            // Set Windows Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JLabel usernameLabel = new JLabel("用户名:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(15);

        loginButton = new JButton("登录");
        signupButton = new JButton("注册");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(signupButton, gbc);

        getContentPane().add(panel);
        pack();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // 登录逻辑
                if(userController.loginUser(username, password)){
                    JOptionPane.showMessageDialog(UserInterface.this,"登录成功！",
                            "登录信息",
                            JOptionPane.INFORMATION_MESSAGE);
                    setLogin(true);

                    FriendsListUI friendsListUI = new FriendsListUI(userController.getUserRepository().getUserByUsername(username), userController.getUserRepository());
                    Thread friends = new Thread(friendsListUI, "openFriendsList");
                    friends.start();
                }
                else {
                    JOptionPane.showMessageDialog(UserInterface.this,"用户名或密码错误！",
                            "Login Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    setLogin(false);
                }

            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String information;
                //注册逻辑
                if(userController.registerUser(username, password)){
                    information = "注册成功！";
                }
                else {
                    information = "注册失败，用户名已经被使用";
                }
                JOptionPane.showMessageDialog(UserInterface.this,
                        information,
                        "Sign Up Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    public boolean isLogin() {
        return Login;
    }

    public void setLogin(boolean login) {
        Login = login;
    }

    @Override
    public void run() {
        UserInterface ui = new UserInterface(userController);
        ui.setVisible(true);
    }

}
