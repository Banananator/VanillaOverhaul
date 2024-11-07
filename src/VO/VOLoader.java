package VO;

import mindustry.Vars;
import mindustry.mod.*;

public class VOLoader extends Mod{

	public VOLoader(){}

    @Override
    public void init(){
        VOSettings.loadUI();
    }

    @Override
    public void loadContent(){
        VOUnitChanges.load();
        VOBlockChanges.load();
        VOSettings.load();
        if(Vars.headless || VOSettings.getBool(VOSettings.ENABLE_BETTER_ENGINES))VOUnitChanges.loadNewEngines();
    }
}
