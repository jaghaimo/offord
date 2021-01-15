package offord.dialog;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.characters.FullName.Gender;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;

import offord.Dialog;

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
    public void handle(Dialog dialog) {
        addText(dialog);
        addOptions(dialog);
    }

    private void addText(Dialog dialog) {
        String name = "all your officers";
        String who = "they";
        if (person != null) {
            name = person.getNameString();
            who = person.getGender() == Gender.FEMALE ? "she" : "he";
        }
        dialog.addText("You decide to call " + name + ".");
        dialog.addText("How should " + who + " behave in combat?");
    }

    private void addOptions(Dialog dialog) {
        for (String personality : personalities) {
            dialog.addOption("Be " + personality, new ApplyHandler(officers, personality));
        }
    }
}
