package UpdateOperate;

import Interface.FriendsListUI;

import static java.lang.Thread.sleep;

public class UpdateFriendList implements Runnable{
    private FriendsListUI friendsListUI;
    public UpdateFriendList(FriendsListUI friendsListUI){
        this.friendsListUI = friendsListUI;
    }
    @Override
    public void run() {
        while (true){
            friendsListUI.updateFriendList();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
