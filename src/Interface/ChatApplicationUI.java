package Interface;
import MessageProcessing.Message;
import UpdateOperate.UpdateMessage;
import UserProcessing.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ChatApplicationUI extends JFrame implements Runnable{
    private JTextArea messageTextArea;
    private JTextField inputTextField;
    private JButton sendButton;
    private User self;
    private User friend;
    public ChatApplicationUI(User self, User friend) {
        setTitle(friend.getUsername());
        setLayout(new BorderLayout());
        setResizable(false);
        this.self = self;
        this.friend = friend;
        // Message display area
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        messageTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Input field and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputTextField = new JTextField();
        inputTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        inputPanel.add(inputTextField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Set Windows-like look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message message = new Message(self, friend, inputTextField.getText()+"\n", LocalDateTime.now(), false);
                self.toSendMessage(friend, message);
                inputTextField.setText("");
            }
        });
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    public void updateMessage(){
        messageTextArea.setText("");
        try {
            for(Message mess:self.getMessageRepository().getMessagesListByUsername(friend.getUsername())){
                messageTextArea.append(mess.getSenderUser().getUsername()+" : "+mess.getContent());
            }
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }
    @Override
    public void run() {
        ChatApplicationUI cui = new ChatApplicationUI(self, friend);
        UpdateMessage update = new UpdateMessage(cui);
        Thread upd = new Thread(update);
        upd.start();
        cui.setVisible(true);
    }
}
