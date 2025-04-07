package VO;

import mindustry.Vars;
import mindustry.mod.*;

public class VOLoader extends Mod{

    public static String name(String name){
		return "bettervanillamod" + "-" + name;
	}

	public VOLoader(){}

    @Override
    public void init(){
        VOSettings.loadUI();
    }

    @Override
    public void loadContent(){
        VOUnitChanges.loadDefault();
        VOBlockChanges.load();
        VOSettings.load();

        if(Vars.headless || VOSettings.getBool(VOSettings.ENABLE_BETTER_ENGINES))VOUnitChanges.loadNewEngines();

        if(Vars.headless || VOSettings.getBool(VOSettings.ENABLE_UNIT_STAT_OVERRIDES)){
            VOUnitChanges.loadOverrides();
            VOBlockChanges.loadNew();
        }
    }
}