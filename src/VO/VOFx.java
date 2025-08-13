package VO;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.Interp.PowOut;
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

    hitBulletBigColor = new Effect(13, e -> {
        color(Color.white, e.color, e.fin());
        stroke(0.5f + e.fout() * 1.5f);

        randLenVectors(e.id, 8, e.finpow() * 30f, e.rotation, 50f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 4 + 1.5f);
        });
    }),

    greenBombPlus = new Effect(180f, 150f, e -> {
        float fillCircleRad = new PowOut(15).apply(5f, 80f, e.fin());
        color(Pal.heal.cpy().a(0.15f), Pal.heal.cpy().a(0f), e.fin());
        Fill.circle(e.x, e.y, fillCircleRad);

        e.scaled(60f, i -> {
            float circleRad = Interp.pow5Out.apply(5f, 80f, i.fin());
            color(Pal.heal);
            stroke(Interp.pow3In.apply(2.5f, 0, i.fin()));
            Lines.circle(e.x, e.y, circleRad);

            Drawf.light(e.x, e.y, circleRad * 2, Pal.heal, 0.8f * i.fin(Interp.pow5Out));

            float spikeLen = Interp.pow3In.apply(110f, 0f, i.fin());
            for(int ii = 0; ii < 4; ii++){
                Drawf.tri(e.x, e.y, 7f, spikeLen, ii*90);
            }

            color(Color.white);
            for(int ii = 0; ii < 4; ii++){
                Drawf.tri(e.x, e.y, 4f, spikeLen * (80/180), ii*90);
            }
        });

        e.scaled(40f, i -> {
            float sparkLen = Interp.pow3Out.apply(0f, 95f, i.fin());
            color(Pal.heal); stroke(i.fout() * 3.5f);
            randLenVectors(e.id + 1, 24, 15f + sparkLen, (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 13f);
            });
        });
    }),

    octDeathEffect = new Effect(360f, 300f, e -> {
        float fillCircleRad = new PowOut(15).apply(5f, 160f, e.fin());
        color(Pal.heal.cpy().a(0.2f), Pal.heal.cpy().a(0f), e.fin());
        Fill.circle(e.x, e.y, fillCircleRad);

        e.scaled(120f, i -> {
            color(Pal.heal);
            stroke(Interp.pow3In.apply(4.5f, 0, i.fin()));

            float circleRad = Interp.pow5Out.apply(5f, 160f, i.fin());
            Lines.circle(e.x, e.y, circleRad);

            Drawf.light(e.x, e.y, circleRad * 2, Pal.heal, 0.8f * i.fin(Interp.pow10Out));

            float spikeLen = Interp.pow3In.apply(190f, 0f, i.fin());
            for(int ii = 0; ii < 4; ii++){
                Drawf.tri(e.x, e.y, 14f, spikeLen, ii*90);
            }

            color(Color.white);
            for(int ii = 0; ii < 4; ii++){
                Drawf.tri(e.x, e.y, 8f, spikeLen * (80/190), ii*90);
            }
        });

        e.scaled(90f, i -> {
            float sparkLen = Interp.pow3Out.apply(0f, 180f, i.fin());
            color(Pal.heal); stroke(i.fout() * 5f);
            randLenVectors(e.id + 1, 37, 25f + sparkLen, (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 16f);
            });
        });
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
    }),

    shootBreach = new Effect(30f, 16f, e ->{
        color(Color.white, e.color, e.fin());
        stroke(e.fout() * 1.3f + 0.7f);
        randLenVectors(e.id, 10, 60f * e.fin(), e.rotation, 10f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7.5f + 1f);
        });
    }),
    
    disperseExplosion = new Effect(80f, 160f, e -> {
        color(Color.valueOf("ea8878").lerp(Pal.redLight, 0.5f));
        e.scaled(20, a -> {
            stroke(a.fout());
            float circleRad = 4f + a.finpow() * 20f;
            Lines.circle(e.x, e.y, circleRad);

            rand.setSeed(e.id);
            for(int i = 0; i < 9; i++){
                float angle = rand.random(360f);
                float lenRand = rand.random(0.5f, 1f);
                Lines.lineAngle(e.x, e.y, angle, a.foutpow() * 20f * rand.random(1f, 0.6f) + 2f, a.finpow() * 30f * lenRand + 6f);
            }
        });

        float intensity = 1f;

        color(e.color, 0.7f);
        for(int i = 0; i < 4; i++){
            rand.setSeed(e.id*2 + i);
            float lenScl = rand.random(0.5f, 1f);
            int fi = i;
            e.scaled(e.lifetime * lenScl, b -> {
                randLenVectors(e.id + fi - 1, b.fin(Interp.pow10Out), (int)(2.9f * intensity), 22f * intensity, (x, y, in, out) -> {
                    float fout = b.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                    float rad = fout * ((2f + intensity) * 2.35f);

                    Fill.circle(b.x + x, b.y + y, rad);
                    Drawf.light(b.x + x, b.y + y, rad * 1.5f, e.color, 0.5f);
                });
            });
        }
    });
}
