package MessageProcessing;

import UserProcessing.FriendRepository;
import UserProcessing.User;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageRepository {
    HashMap<User, ArrayList<Message>> messageList = new HashMap<User, ArrayList<Message>>();
    FriendRepository friendRepository;

    public MessageRepository(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public void saveMessage(Message message) {
        // 实现保存消息的代码
        if(messageList.get(message.getSenderUser()) == null){
            ArrayList<Message> messageArrayList = new ArrayList<Message>();
            messageList.put(message.getSenderUser(), messageArrayList);
        }
        messageList.get(message.getSenderUser()).add(message);
    }

    public ArrayList<Message> getMessagesListByUsername(String username) {
        // 实现根据用户名查询用户收到的消息的代码
        if(messageList.get(friendRepository.getFriendsByUsername(username)) == null){
            ArrayList<Message> messageArrayList = new ArrayList<Message>();
            messageList.put(friendRepository.getFriendsByUsername(username), messageArrayList);
        }
        return messageList.get(friendRepository.getFriendsByUsername(username));
    }

//    public void updateMessageStatus(Message message) {
//
//    }
}
