package UserProcessing;
import MessageProcessing.Message;
import MessageProcessing.MessageRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    // 用户核心信息
    private String username; // 用户名
    private String password; // 密码


    // 用户个人资料
    private String personalName; // 个人名称
    private String personalProfile; // 个人简介
    private Image personalImage; // 个人头像


    // 用户其他信息
    private FriendRepository friendRepository = new FriendRepository();// 好友库
    private MessageRepository messageRepository = new MessageRepository(friendRepository); // 消息库


    // 构造函数、Getter和Setter方法...
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFriend(User friend) {
        friendRepository.addFriend(friend);
        friend.getFriendRepository().addFriend(this);
    }

    public String getPersonalName() {
        return personalName;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public Image getPersonalImage() {
        return personalImage;
    }

    public void setPersonalImage(Image personalImage) {
        this.personalImage = personalImage;
    }

    public FriendRepository getFriendRepository() {
        return friendRepository;
    }

    public void setFriendRepository(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public User searchFriend(String userName) {
        return friendRepository.getFriendsByUsername(userName);
    }

    public void toSendMessage(User friend, Message message){
        friend.messageRepository.saveMessage(message);
        this.messageRepository.getMessagesListByUsername(friend.getUsername()).add(message);
    }


}