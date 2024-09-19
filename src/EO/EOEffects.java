package EO;

import arc.math.*;
import arc.math.geom.*;
import mindustry.entities.*;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;

public class EOEffects {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

    greenBombPlus = new Effect(40f, 100f, e -> {
        color(Pal.heal);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 5f, 70f * e.fout(), i*90 + 45f);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2.5f, 25f * e.fout(), i*90 + 45f);
        }
    });
}
