package VO.custom;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.entities.Effect;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;

public class VOExplosionEffect extends Effect{
    /** Explosion mode. Automaticly sets up colors and affects values of auto-setup */
    public boolean flak = false, blast = true, pyra = false, plast = false, surge = false;
    /** Whether to draw wave. */
    public boolean drawWave = true;
    /** Overrides auto-setup colors if not null. */
    public Color waveColor = null, smokeColor = null, sparkColor = null, lightColor = null;
    /** Easy way to set the explosion type. Write {@code flak}, {@code blast},  {@code pyra} or {@code surge} here. */
    public String type = "";
    /** 
     * Values of explosion auto-setup. If {@code rad} is not 0, automaticly sets up all the values depending 
     * on radius ({@code rad}) and damage of the explosion ({@code power}, if set). Can't be negative. Set
     * {@code rad} to 0 to disable auto-setup.
    */
    public float rad = 25, power = 60;
    /** Explosion value. If not 0, overrides itself in auto-setup. If negative, effect will be inverted. */
    public float waveLife = 0f, waveStroke = 0f, waveRad = 0f;
    /** Explosion value. If not 0, overrides itself in auto-setup. If negative, effect will be inverted. */
    public float sparkLife = 0f, sparkStroke = 0f, sparkRad = 0f, sparkLen = 0f;
    /** Explosion value. If not 0, overrides itself in auto-setup. If negative, effect will be inverted. */
    public float smokeLife = 0f, smokeSize = 0f, smokeRad = 0f;
    /** Amount of particles. Set to 0 to disable particle. */
    public int smokes = -1, sparks = -1;

    public VOExplosionEffect(float rad, float power, String type){
        this.rad = rad;
        this.power = power;
        this.type = type;
        clip = 0;
        lifetime = 0;
        followParent = rotWithParent = false;
    }
    
    public VOExplosionEffect(float rad, float power){
        this(rad, power, "blast");
    }

    public VOExplosionEffect(float rad){
        this(rad, 0f, "blast");
    }

    public VOExplosionEffect(){
        this(0f, 0f, "blast");
    }

    public float maxx(float a, float b, float c, float d){
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    @Override
    public void init(){
        clip = maxx(clip, waveRad + waveStroke, sparkRad, smokeRad);

        if(type == "flak"){
            flak = true;
            blast = pyra = plast = surge = false;
        } else if(type == "blast"){
            blast = true;
            flak = pyra = plast = surge = false;
        } else if(type == "pyra"){
            pyra = true;
            flak = blast = plast = surge = false;
        } else if(type == "plast"){
            plast = true;
            flak = blast = pyra = surge = false;
        } else if(type == "surge"){
            surge = true;
            flak = blast = pyra = plast = false;
        }

        if(!flak && !blast && !pyra && !plast && !surge) blast = true;

        if(smokeColor == null) smokeColor = Color.gray;
        if(flak){
            if(waveColor == null) waveColor = Pal.bulletYellow;
            if(sparkColor == null) sparkColor = Pal.lighterOrange;
        } else if(blast){
            if(waveColor == null) waveColor = Pal.missileYellow;
            if(sparkColor == null) sparkColor = Pal.missileYellowBack;
        } else if(pyra){
            if(waveColor == null) waveColor = Pal.missileYellow;
            if(sparkColor == null) sparkColor = Pal.lightishOrange;
        } else if(plast){
            if(waveColor == null) waveColor = Pal.plastaniumFront;
            if(sparkColor == null) sparkColor = Pal.plastaniumBack;
        } else if(surge){
            if(waveColor == null) waveColor = Pal.bulletYellow;
            if(sparkColor == null) sparkColor = Pal.bulletYellowBack;
        }
        if(lightColor == null) lightColor = sparkColor;

        if(power < 0) power *= -1; if(rad < 0) rad *= -1;
//UnitTypes.zenith.weapons.get(0).bullet.despawnHit = false; UnitTypes.zenith.weapons.get(1).bullet.despawnHit = false;        

        if(rad > 0){
            float r = rad > 0 ? rad : -rad;
            float m = 0f;

            m = flak || plast ? 0.9f : pyra ? 1.2f : 1f;
            if(smokeLife == 0) smokeLife = (30f + (power > 0 ? ((r + power) / 30f) : (r / 20f))) * m;
            m = flak || plast ? 5f : surge ? 4f : 3f;
            if(waveLife == 0) waveLife = r / (m * (1 + rad / 120f));
            if(sparkLife == 0) sparkLife = lifetime - (lifetime / 3f + (rad / 40f));

            m = flak ? 1f : plast ? 2f : 0f;
            if(waveRad == 0) waveRad = r + 2 + m;
            m = blast || pyra ? 1f : 0f;
            if(waveStroke == 0) waveStroke = 2 + (power > 0 ? (power / 35f) : (r / 20f)) + m;

            m = flak ? 0.85f : plast ? 2f/3f : surge ? 1.5f : 1f;
            if(sparks < 0) sparks = round((5f + (power > 0 ? power / 20f + r / 60f : r / 20f)) * m);
            m = flak || blast ? 1f : pyra ? 1.1f : 1.25f;
            if(sparkRad == 0) sparkRad = (r + (4f + r / 20f)) * m;
            m = flak || blast ? 1f : pyra ? 0.8f : 2f;
            if(surge) if(sparkLen == 0) sparkLen = (1f + (power > 0 ? (r / 40f) + (power / 15f) : (r / 12.5f))) * m;
                else if(sparkLen == 0) sparkLen = (1f + (power > 0 ? (r / 15f) + (power / 50f) : (r / 12.5f))) * m;
            m = flak ? 1 : blast ? 1.3f : pyra ? 2.2f : 0.75f;
            if(sparkStroke == 0) sparkStroke = (1f + (power > 0 ? (r / 100f) + (power / 45f) : (r / 40f))) * m;

            m = blast ? 1.1f : pyra || plast ? 1.25f : 1f;
            if(smokes < 0) smokes = round((4f + (power > 0 ? power / 40f : r / 15f)) * m);
            if(smokeRad == 0) smokeRad = r > 8f ? r - 5f : r > 5f ? r - 3f : r;
            m = blast ? 1.25f : pyra ? 1.5f : 1f;
            if(smokeSize == 0) smokeSize = (1f + (power > 0 ? (r / 30f) + (power / 7f) : r / 10f)) * m;
        }

        if(lifetime == 0) lifetime = 30f;
        if(waveLife == 0) waveLife = 6f;
        if(sparkLife == 0) sparkLife = 20f;

        if(waveRad == 0) waveRad = 27f;
        if(waveStroke == 0) waveStroke = 1.5f;

        if(sparks < 0) sparks = 6;
        if(sparkRad == 0) sparkRad = 29f;
        if(sparkLen == 0) sparkLen = 4f;
        if(sparkStroke == 0) sparkStroke = 1f;

        if(smokes < 0) smokes = 5;
        if(smokeRad == 0) smokeRad = 20f;
        if(smokeSize == 0) smokeSize = 4.5f;
    }

    public int round(float value){
        int result = 0;
        float reminder = 0f;
        reminder = value % 1;
        if(reminder < 0.5f) result = (int)(value - reminder);
        else result = (int)(value + 1 - reminder);
        return result;
    }

    public float maxx(float a, float b, float c){
        return Math.max(a, Math.max(b, c));
    }


    @Override
    public void render(EffectContainer e){
        if(drawWave){
            color(waveColor);
            e.scaled(waveLife, i -> {
                stroke(waveStroke * (waveStroke > 0 ? i.fout() : i.fin()));
                Lines.circle(e.x, e.y, waveRad * (waveRad > 0 ? i.fin() : i.fout()));
            });
        }
        if(sparks > 0){
            color(sparkColor);
            e.scaled(sparkLife, i -> {
                stroke(sparkStroke * (sparkStroke > 0 ? i.fout() : i.fin()));
                randLenVectors(e.id + 1, sparks, sparkRad * (sparkRad > 0 ? i.finpow() : i.foutpow()), (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), sparkLen * (sparkLen > 0 ? i.fout() : i.fout()));
                });
            });
        }
        if(smokes > 0){
            color(smokeColor);
            e.scaled(smokeLife, i -> {
                randLenVectors(e.id, smokes, smokeRad * (smokeRad > 0 ? e.finpow() : e.foutpow()), (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, smokeSize * (smokeSize > 0 ? e.fout() : e.fin()));
                });
            });
        }
        float lightRad = 2f * (drawWave ? (waveRad > 0 ? waveRad : -waveRad) : sparks > 0 ? (sparkRad > 0 ? sparkRad : -sparkRad) : 0f);
        Drawf.light(e.x, e.y, lightRad, lightColor, 0.8f * e.fout());
    }
}
