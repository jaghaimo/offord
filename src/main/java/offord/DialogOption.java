package offord;

import offord.dialog.DialogHandler;

public class DialogOption {

    private String text;
    private DialogHandler handler;

    public DialogOption(String text) {
        this.text = text;
    }

    public DialogOption(String text, DialogHandler handler) {
        this.text = text;
        this.handler = handler;
    }

    public String getText() {
        return text;
    }

    public DialogHandler getHandler() {
        return handler;
    }
}
