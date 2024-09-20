package EO;

import mindustry.mod.*;

public class EOLoader extends Mod{

	public EOLoader(){}

    @Override
    public void init(){
        EOUnitChanges.load();
        EOBlockChanges.load();
    }
}
