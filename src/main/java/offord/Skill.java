package offord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.impl.campaign.abilities.BaseDurationAbility;

public class Skill extends BaseDurationAbility {

    @Override
    protected void activateImpl() {
        SectorAPI sector = Global.getSector();
        List<OfficerDataAPI> officers = getAllOfficers(sector);
        InteractionDialogPlugin dialog = new Dialog(officers);
        sector.getCampaignUI().showInteractionDialog(dialog, null);
    }

    @Override
    protected void applyEffect(float arg0, float arg1) {
    }

    @Override
    protected void cleanupImpl() {
    }

    @Override
    protected void deactivateImpl() {
    }

    private List<OfficerDataAPI> getAllOfficers(SectorAPI sector) {
        CampaignFleetAPI fleet = sector.getPlayerFleet();
        List<OfficerDataAPI> officers = new ArrayList<>();
        officers.addAll(getActiveOfficers(fleet));
        officers.addAll(getMothballedOfficers(fleet));
        return officers;
    }

    private List<OfficerDataAPI> getActiveOfficers(CampaignFleetAPI fleet) {
        return fleet.getFleetData().getOfficersCopy();
    }

    private List<OfficerDataAPI> getMothballedOfficers(CampaignFleetAPI fleet) {
        FleetDataAPI mothballed = fleet.getCargo().getMothballedShips();
        if (mothballed == null) {
            return Collections.<OfficerDataAPI>emptyList();
        }
        return mothballed.getOfficersCopy();
    }
}
