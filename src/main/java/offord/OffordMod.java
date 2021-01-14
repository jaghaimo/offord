package offord;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class OffordMod extends BaseModPlugin {

    @Override
    public void onGameLoad(boolean newGame) {
        Global.getSector().getCharacterData().addAbility("offord_skill");
    }
}
