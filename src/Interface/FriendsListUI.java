package Interface;

import UpdateOperate.UpdateFriendList;
import UserProcessing.User;
import UserProcessing.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FriendsListUI extends JFrame implements Runnable{
    private DefaultListModel<String> friendsListModel;
    private JList<String> friendsList;
    private JButton addButton;
    private JButton removeButton;
    private User me;
    private UserRepository userRepository;
    public FriendsListUI(User user, UserRepository userRepository) {
        setTitle("好友列表");
        setSize(300, 400);
        setLocationRelativeTo(null);

        me = user;
        this.userRepository = userRepository;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        friendsListModel = new DefaultListModel<>();
        friendsList = new JList<>(friendsListModel);
        friendsList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        friendsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friendsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = friendsList.locationToIndex(e.getPoint());
                    String selectedFriend = friendsListModel.getElementAt(index);
                    User select = me.getFriendRepository().getFriendsByUsername(selectedFriend);
                    ChatApplicationUI chatUI = new ChatApplicationUI(me, select);
                    Thread chat = new Thread(chatUI, "beginChat");
                    chat.start();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(friendsList);
        add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add friend");
        removeButton = new JButton("Remove friend");

        addButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        removeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendName = JOptionPane.showInputDialog("Enter friend's name:");
                if (friendName != null && !friendName.isEmpty()) {
                    User friend;
                    if((friend = userRepository.getUserByUsername(friendName)) != null){
                        me.addFriend(friend);
                        friendsListModel.addElement(friendName);
                    }
                    else {
                        JOptionPane.showMessageDialog(FriendsListUI.this,
                                "此用户不存在！",
                                "查找信息",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = friendsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectFriend = friendsListModel.getElementAt(selectedIndex);
                    me.getFriendRepository().getFriendsByUsername(selectFriend).getFriendRepository().removeFriend(me.getUsername());
                    me.getFriendRepository().removeFriend(selectFriend);
                    friendsListModel.removeElementAt(selectedIndex);
                }
            }
        });

    }
    public void updateFriendList(){
        for(String friend:me.getFriendRepository().getFriendsList().keySet()){
            User fr = me.getFriendRepository().getFriendsByUsername(friend);
            if(!friendsListModel.contains(friend)){
                this.friendsListModel.addElement(friend);
            }
        }
        for(int index = 0; index < friendsListModel.getSize(); index++){
            if(!me.getFriendRepository().hasFriend(friendsListModel.getElementAt(index))){
                friendsListModel.removeElementAt(index);
            }
        }
    }

    @Override
    public void run() {
        FriendsListUI friendsListUI = new FriendsListUI(me, userRepository);
        UpdateFriendList updateFriendList = new UpdateFriendList(friendsListUI);
        Thread updateList = new Thread(updateFriendList);
        updateList.start();
        friendsListUI.setVisible(true);
    }
}
