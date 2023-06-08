package MessageProcessing;

import UserProcessing.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    private User senderUser;
    private User receiverUser;
    private String content;
    private LocalDateTime sendTime;
    private boolean isRead;

    public Message(User senderUser, User receiverUser, String content, LocalDateTime sendTime, boolean isRead) {
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.content = content;
        this.sendTime = sendTime;
        this.isRead = isRead;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public User getSenderUsername() {
        return senderUser;
    }

    public void setSenderUsername(User senderUsername) {
        this.senderUser = senderUsername;
    }

    public User getReceiverUsername() {
        return receiverUser;
    }

    public void setReceiverUsername(User receiverUsername) {
        this.receiverUser = receiverUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }


}
