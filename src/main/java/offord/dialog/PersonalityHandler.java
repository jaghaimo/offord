package offord.dialog;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;

public class PersonalityHandler implements DialogHandler {

    private PersonAPI person;
    private List<OfficerDataAPI> officers;
    private String[] personalities = { Personalities.TIMID, Personalities.CAUTIOUS, Personalities.STEADY,
            Personalities.AGGRESSIVE, Personalities.RECKLESS };

    public PersonalityHandler(OfficerDataAPI officer) {
        this(Arrays.asList(officer));
        person = officer.getPerson();
    }

    public PersonalityHandler(List<OfficerDataAPI> officers) {
        this.officers = officers;
    }

    @Override
    public void handle(InteractionDialogAPI dialog) {
        addText(dialog.getTextPanel());
        addOptions(dialog.getOptionPanel());
    }

    private void addText(TextPanelAPI text) {
        String name = "all your officers";
        String who = "they";
        if (person != null) {
            name = person.getNameString();
            who = person.getGender() == Gender.FEMALE ? "she" : "he";
        }
        text.addPara("You decide to call " + name + ".");
        text.addPara("How should " + who + " behave in combat?");
    }

    private void addOptions(OptionPanelAPI options) {
        for (String personality : personalities) {
            options.addOption("Be " + personality, new ApplyHandler(officers, personality));
        }
    }
}
