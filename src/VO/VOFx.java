package VO;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

@SuppressWarnings("unused")
public class VOFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

    shootSmallFlameVariant = new Effect(32f, 80f, e -> {
        color(Pal.lightFlame, Pal.darkFlame, Color.gray, e.fin());

        randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 10f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 1.75f);
        });
    }),

    shootSmallFlameSmoke = new Effect(70f, 80f, e -> {
        color(Pal.darkFlame.cpy().a(0.5f), Color.gray.cpy().a(0f), e.fin());

        randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 10f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 2.2f);
        });
    }),

    shootMediumColor = new Effect(9, e -> {
        color(e.color, Color.gray, e.fin());
        float w = 1.1f + 6.5f * e.fout();
        Drawf.tri(e.x, e.y, w, 22f * e.fout(), e.rotation);
        Drawf.tri(e.x, e.y, w, 4f * e.fout(), e.rotation + 180f);
    }),

    greenBombPlus = new Effect(40f, 100f, e -> {
        color(Pal.heal);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 5f, 40f * e.fout(), i*90 + 45f);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2.5f, 15f * e.fout(), i*90 + 45f);
        }
    }),

    octDeathEffect = new Effect(120f, 300f, e -> {
        color(Pal.heal);
        stroke(Interp.pow10Out.apply(1, 0, e.fin()));

        float circleRad = 4f + e.finpow() * 156f;
        Lines.circle(e.x, e.y, circleRad);

        Drawf.light(e.x, e.y, circleRad * 2, Pal.heal, 0.8f * e.fin(Interp.pow10Out));

        color(Pal.heal);
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 5f, 40f * e.fout(), i*90 + 45f);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 2.5f, 15f * e.fout(), i*90 + 45f);
        }
    }),
    
    navanaxHit = new Effect(50f, 100f, e -> {
        e.scaled(7f, b -> {
            color(Pal.heal, b.fout());
            Fill.circle(e.x, e.y, 100f);
        });

        color(Pal.heal);
        stroke(e.fout() * 3f);
        Lines.circle(e.x, e.y, 100f);

        int points = 10;
        float offset = Mathf.randomSeed(e.id, 360f);
        for(int i = 0; i < points; i++){
            float angle = i* 360f / points + offset;
            //for(int s : Mathf.zeroOne){
            Drawf.tri(e.x + Angles.trnsx(angle, 100f), e.y + Angles.trnsy(angle, 100f), 6f, 50f * e.fout(), angle/* + s*180f*/);
            //}
        }

        Fill.circle(e.x, e.y, 12f * e.fout());
        color();
        Fill.circle(e.x, e.y, 6f * e.fout());
        Drawf.light(e.x, e.y, 100f * 1.6f, Pal.heal, e.fout());
    });
}
