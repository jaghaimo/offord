package offord.dialog;

import java.util.List;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.characters.OfficerDataAPI;

public class ApplyHandler implements DialogHandler {

    private List<OfficerDataAPI> officers;
    private String personality;

    public ApplyHandler(List<OfficerDataAPI> officers, String personality) {
        this.officers = officers;
        this.personality = personality;
    }

    @Override
    public void handle(InteractionDialogAPI dialog) {
        dialog.getTextPanel().addPara("You swiftly pass the intructions and terminate the call.");
        for (OfficerDataAPI officer : officers) {
            officer.getPerson().setPersonality(personality);
        }
    }
}
