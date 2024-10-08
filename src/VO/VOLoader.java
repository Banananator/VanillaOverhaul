package VO;

import mindustry.mod.*;

public class VOLoader extends Mod{

	public VOLoader(){}

    @Override
    public void loadContent(){
        VOUnitChanges.load();
        VOBlockChanges.load();
    }
}
