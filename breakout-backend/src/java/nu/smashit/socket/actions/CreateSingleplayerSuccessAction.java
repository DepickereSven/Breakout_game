package nu.smashit.socket.actions;

public class CreateSingleplayerSuccessAction implements ResponseAction {

    public final String key;

    public CreateSingleplayerSuccessAction(String key) {
        this.key = key;
    }
}
