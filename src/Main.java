import Interface.ChatApplicationUI;
import Interface.UserInterface;
import UserProcessing.UserController;
import UserProcessing.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserController userController = new UserController(userRepository);

        userController.registerUser("Asher", "123589");
        userController.registerUser("John", "123456");
        userController.registerUser("Jack", "123456");
//        userRepository.getUserByUsername("Asher").addFriend(userController.getUserRepository().getUserByUsername("John"));
        UserInterface initialInterface = new UserInterface(userController);

        Thread iif = new Thread(initialInterface);
        iif.start();

    }
}