package offord.dialog;

import java.util.List;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.characters.OfficerDataAPI;

public class OfficerHandler implements DialogHandler {

    private List<OfficerDataAPI> officers;

    public OfficerHandler(List<OfficerDataAPI> officers) {
        this.officers = officers;
    }

    @Override
    public void handle(InteractionDialogAPI dialog) {
        addText(dialog.getTextPanel());
        addOptions(dialog.getOptionPanel());
    }

    private void addText(TextPanelAPI text) {
        text.addPara("You decide to call your officers and instruct them how to behave in combat.");
        text.addPara("Who would you like to call?");
    }

    private void addOptions(OptionPanelAPI options) {
        options.addOption("Call all officers", new PersonalityHandler(officers));
        for (OfficerDataAPI officer : officers) {
            String person = officer.getPerson().getNameString();
            options.addOption(person, new PersonalityHandler(officer));
        }
    }
}
