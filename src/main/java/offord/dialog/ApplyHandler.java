package offord.dialog;

import java.util.List;

import com.fs.starfarer.api.characters.OfficerDataAPI;

import offord.Dialog;

public class ApplyHandler implements DialogHandler {

    private List<OfficerDataAPI> officers;
    private String personality;

    public ApplyHandler(List<OfficerDataAPI> officers, String personality) {
        this.officers = officers;
        this.personality = personality;
    }

    @Override
    public void handle(Dialog dialog) {
        dialog.addText("You swiftly pass the intructions and terminate the call.");
        for (OfficerDataAPI officer : officers) {
            officer.getPerson().setPersonality(personality);
        }
    }
}
