package offord;

import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.util.Misc;

import offord.dialog.DialogHandler;
import offord.dialog.OfficerHandler;

public class Dialog implements InteractionDialogPlugin {

    private InteractionDialogAPI dialog;
    private List<OfficerDataAPI> officers;

    public Dialog(List<OfficerDataAPI> officers) {
        this.officers = officers;
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        this.dialog = dialog;
        dialogHandler(new OfficerHandler(officers));
    }

    @Override
    public void advance(float arg0) {
    }

    @Override
    public void backFromEngagement(EngagementResultAPI arg0) {
    }

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }

    @Override
    public void optionMousedOver(String arg0, Object arg1) {
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        dialog.getTextPanel().addPara(optionText, Misc.getButtonTextColor());
        if (optionData instanceof DialogHandler) {
            DialogHandler handler = (DialogHandler) optionData;
            dialogHandler(handler);
            return;
        }
        dialog.dismiss();
    }

    private void dialogHandler(DialogHandler handler) {
        OptionPanelAPI options = dialog.getOptionPanel();
        options.clearOptions();
        handler.handle(dialog);
        options.addOption("Cut the comm link", null);
    }
}
