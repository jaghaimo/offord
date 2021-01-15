package offord.dialog;

import java.util.List;

import com.fs.starfarer.api.characters.OfficerDataAPI;

import offord.Dialog;

public class OfficerHandler implements DialogHandler {

    private List<OfficerDataAPI> officers;

    public OfficerHandler(List<OfficerDataAPI> officers) {
        this.officers = officers;
    }

    @Override
    public void handle(Dialog dialog) {
        addText(dialog);
        addOptions(dialog);
    }

    private void addText(Dialog dialog) {
        dialog.addText("You decide to call your officers and instruct them how to behave in combat.");
        dialog.addText("Who would you like to call?");
    }

    private void addOptions(Dialog dialog) {
        dialog.addOption("Call all officers", new PersonalityHandler(officers));
        for (OfficerDataAPI officer : officers) {
            String person = officer.getPerson().getNameString();
            dialog.addOption(person, new PersonalityHandler(officer));
        }
    }
}
