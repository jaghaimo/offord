package offord.dialog;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;

public class DismissHandler implements DialogHandler {

    @Override
    public void handle(InteractionDialogAPI dialog) {
        dialog.dismiss();
    }
}
