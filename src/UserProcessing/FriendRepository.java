package UserProcessing;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendRepository {
    private HashMap<String, User> friendsList = new HashMap<>(); // 好友列表

    public User getFriendsByUsername(String username){
        return friendsList.get(username);
    }

    public void addFriend(User user){
        friendsList.put(user.getUsername(), user);
    }

    public HashMap<String, User> getFriendsList() {
        return friendsList;
    }

    public void removeFriend(String name){
        friendsList.remove(name);
    }

    public boolean hasFriend(String name){
        if(friendsList.get(name) == null){
            return false;
        }
        else {
            return true;
        }
    }
}
