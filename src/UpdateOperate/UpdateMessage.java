package UpdateOperate;

import Interface.ChatApplicationUI;

import static java.lang.Thread.sleep;

public class UpdateMessage implements Runnable{
    private ChatApplicationUI chatUI;
    public UpdateMessage(ChatApplicationUI chatApplicationUI){
        chatUI = chatApplicationUI;
    }
    @Override
    public void run() {
        while (true){
            chatUI.updateMessage();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
