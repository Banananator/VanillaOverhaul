package EO.audiovisual;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.units.UnitAssembler.*;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

@SuppressWarnings("unused")
public class DuoFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

    shootTiny = new Effect(8, e -> {
        color(Pal.lighterOrange, Pal.lightOrange, e.fin());
        float w = 0.5f + 2.5f * e.fout();
        Drawf.tri(e.x, e.y, w, 7.5f * e.fout(), e.rotation);
        Drawf.tri(e.x, e.y, w, 1.5f * e.fout(), e.rotation + 180f);
    }),

    shootTinySmoke = new Effect(20f, e -> {
        color(Pal.lighterOrange, Color.lightGray, Color.gray, e.fin());

        randLenVectors(e.id, 5, e.finpow() * 3f, e.rotation, 10f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 0.75f);
        });
    });
}
