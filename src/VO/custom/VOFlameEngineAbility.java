package VO.custom;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.*;
import mindustry.Vars;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class VOFlameEngineAbility extends Ability{

    public float effectInterval = 3f;
    public float x, y, rotation, width, length, cone = 15f;
    public int particles = 1;
    public boolean rotateEffect = false;

    public float lightStroke = 40f;
    public float oscScl = 1.2f, oscMag = 0.02f;
    public int divisions = 25;

    public boolean rotateFlare = false;
    public Interp lengthInterp = Interp.slope;

    public float[] lengthWidthPans = {
        1.12f, 1.3f, 0.32f,
        1f, 1f, 0.3f,
        0.8f, 0.9f, 0.2f,
        0.5f, 0.8f, 0.15f,
        0.25f, 0.7f, 0.1f,
    };

    protected float counter;

    public VOFlameEngineAbility(float x, float y, float width, float length, float rotation, float effectInterval, int particles, float cone){
        this.x = x; this.y = y;
        this.width = width;
        this.length = length;
        this.rotation = rotation;
        this.effectInterval = effectInterval;
        this.particles = particles;
        this.cone = cone;
        display = false;
    }

    @Override
    public void update(Unit unit){
        if(Vars.headless) return;

        counter += Time.delta;
        if((counter >= effectInterval) && !unit.inFogTo(Vars.player.team())){
            Tmp.v1.trns(unit.rotation - 90f, x, y);
            counter %= effectInterval;
            Effect effect = new Effect(30f, length * 2f, e -> {
                color(unit.team.color); stroke(e.fout() * (width / 3f));
                randLenVectors(e.id + 1, particles, (length / 2.5f) + length * e.finpow(), e.rotation, cone, (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * (length / 10f));
                });
            }).layer(109.9f);
            effect.at(Tmp.v1.x + unit.x, Tmp.v1.y + unit.y, unit.rotation + rotation);
        }
    }  

    @Override
    public void draw(Unit unit){
        float sin = Mathf.sin(Time.time, oscScl, oscMag);

        Color[] colors = {unit.team.color.cpy().a(0.5f), unit.team.color.cpy(), Color.white.cpy()};
        Tmp.v2.trns(unit.rotation - 90f, x, y);
        Draw.z(110);
        for(int i = 0; i < colors.length; i++){
            Draw.color(colors[i].write(Tmp.c1).mul(0.9f).mul(1f + Mathf.absin(Time.time, 1f, 1f)));
            Drawf.flame(Tmp.v2.x + unit.x, Tmp.v2.y + unit.y, divisions, rotation + unit.rotation,
                length * lengthWidthPans[i * 3] * (1f - sin),
                width * lengthWidthPans[i * 3 + 1] * (1f + sin),
                lengthWidthPans[i * 3 + 2]
            );
        }

        Tmp.v2.trns(rotation, length * 1.1f);
        Drawf.light(Tmp.v2.x + unit.x, Tmp.v2.y + unit.y, lightStroke, Pal.powerLight, 0.3f);
        Draw.reset();
    }
}
