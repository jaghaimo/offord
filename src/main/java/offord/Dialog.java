package offord;

import java.util.ArrayList;
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

    private final String PREV_PAGE = "Previous Page";
    private final String NEXT_PAGE = "Next Page";
    private final String CUT_THE_COMM = "Cut the comm link";
    private int currentPage = 0;
    private int optionsPerPage = 5;

    List<DialogOption> options = new ArrayList<>();

    private InteractionDialogAPI dialog;
    private List<OfficerDataAPI> officers;

    public Dialog(List<OfficerDataAPI> officers) {
        this.officers = officers;
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        this.dialog = dialog;
        optionSelected(null, new OfficerHandler(officers));
    }

    @Override
    public void advance(float arg0) {
    }

    @Override
    public void backFromEngagement(EngagementResultAPI result) {
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
    public void optionMousedOver(String optionText, Object optionData) {
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        if (optionData == CUT_THE_COMM) {
            dialog.dismiss();
            return;
        }
        if (optionData instanceof DialogHandler) {
            if (optionText != null) {
                dialog.getTextPanel().addPara(optionText, Misc.getButtonTextColor());
            }
            options.clear();
            DialogHandler handler = (DialogHandler) optionData;
            handler.handle(this);
        }
        changePage(optionData);
        showOptions();
    }

    public void addOption(String text, DialogHandler handler) {
        options.add(new DialogOption(text, handler));
    }

    public void addText(String text) {
        dialog.getTextPanel().addPara(text);
    }

    private void showOptions() {
        int maxPages = (int) Math.ceil((float) options.size() / (float) optionsPerPage);
        OptionPanelAPI optionPanel = dialog.getOptionPanel();
        optionPanel.clearOptions();
        clampCurrentPage(maxPages);
        addOptions(currentPage * optionsPerPage);
        addPagination(maxPages);
        optionPanel.addOption(CUT_THE_COMM, CUT_THE_COMM);
    }

    private void addOptions(int start) {
        int end = Math.min(start + optionsPerPage, options.size());
        OptionPanelAPI optionPanel = dialog.getOptionPanel();
        for (int i = start; i < end; i++) {
            DialogOption option = options.get(i);
            optionPanel.addOption(option.getText(), option.getHandler());
        }
    }

    private void addPagination(int maxPages) {
        if (maxPages > 1) {
            OptionPanelAPI optionPanel = dialog.getOptionPanel();
            optionPanel.addOption(PREV_PAGE, PREV_PAGE);
            optionPanel.addOption(NEXT_PAGE, NEXT_PAGE);
            if (currentPage <= 0) {
                optionPanel.setEnabled(PREV_PAGE, false);
            }
            if (currentPage >= maxPages - 1) {
                optionPanel.setEnabled(NEXT_PAGE, false);
            }
        }
    }

    private void changePage(Object optionData) {
        if (optionData == PREV_PAGE) {
            currentPage--;
        }
        if (optionData == NEXT_PAGE) {
            currentPage++;
        }
    }

    private void clampCurrentPage(int maxPages) {
        if (currentPage > maxPages - 1) {
            currentPage = maxPages - 1;
        }
        if (currentPage < 0) {
            currentPage = 0;
        }
    }
}
