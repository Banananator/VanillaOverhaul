package VO.custom;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.entities.Effect;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;

import arc.Core;

public class VOExplosionEffect extends Effect{
    /** Explosion mode. Automaticly sets up colors and affects values of auto-setup. */
    public boolean flak = false, blast = true, pyra = false, plast = false, surge = false;
    /** Explosion mode. Automaticly sets up colors and affects values of auto-setup, doesn't work without {@code power} being set up. */
    public boolean sap = false;
    /** Whether to draw wave. */
    public boolean drawWave = true;
    /** Overrides auto-setup colors if not null. */
    public Color[] waveColor = null, smokeColor = null, sparkColor = null, lightColor = null;
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
    /** Whether to draw light on smoke particles. Set to 0 for {@code false} or >0 for {@code true}. */
    public int drawSmokeLight = -1;
    /** Values of smoke's light, if drawn. */
    public float smokeLightScl = 2f, smokeLightOpacity = 0.6f;
    /** Explosion value interpolation. If not {@code null}, overrides itself in auto-setup. */
    public Interp waveRadInterp = null, waveStrokeInterp = null, sparkRadInterp = null, sparkSizeInterp = null, smokeRadInterp = null, smokeSizeInterp = null;
    /** Whether to interpolate colors. */
    public boolean interpColor = false;
    /** Amount of particles. Set to 0 to disable particle. */
    public int smokes = -1, sparks = -1;
    /** Texture of the smokes. */
    public String smokeTex = "circle";
    /** Rotation of the smoke, if has a non-circle region. */
    public float smokeRot = 0f, smokeBaseRot = 0f;

    public static final Color col = new Color();

    /** Creates an advanced explosion effect.
     * @param rad is used for basic auto-setup.
     * @param power is added to {@code rad} for advanced auto-setup. Is usually the explosion's damage.     
     * @param type sets the explosion type. */
    public VOExplosionEffect(float rad, float power, String type){
        this.rad = rad;
        this.power = power;
        this.type = type;
        clip = 0;
        lifetime = 0;
        followParent = rotWithParent = false;
    }
    
    /** Creates an advanced explosion effect.
     * @param rad is used for basic auto-setup.
     * @param power is added to {@code rad} for advanced auto-setup. Is usually the explosion's damage.  */
    public VOExplosionEffect(float rad, float power){
        this(rad, power, "blast");
    }

    /** Creates an advanced explosion effect.
     * @param rad is used for basic auto-setup. */
    public VOExplosionEffect(float rad){
        this(rad, 0f, "blast");
    }

    /** Creates an advanced explosion effect.
     * @param type sets the explosion type. */
    public VOExplosionEffect(String type){
        this(0f, 0f, type);
    }

    /** Creates an advanced explosion effect. */
    public VOExplosionEffect(){
        this(0f, 0f, "blast");
    }

    public float maxx(float a, float b, float c, float d){
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    @Override
    public void init(){
        clip = maxx(clip, waveRad + waveStroke, sparkRad + sparkLen, smokeRad + smokeSize);

        if(type == "flak"){
            flak = true;
            blast = pyra = plast = surge = sap = false;
        } else if(type == "blast"){
            blast = true;
            flak = pyra = plast = surge = sap = false;
        } else if(type == "pyra"){
            pyra = true;
            flak = blast = plast = surge = sap = false;
        } else if(type == "plast"){
            plast = true;
            flak = blast = pyra = surge = sap = false;
        } else if(type == "surge"){
            surge = true;
            flak = blast = pyra = plast = sap = false;
        } else if(type == "sap"){
            sap = true;
            flak = blast = pyra = plast = surge = false;
        }

        if(!flak && !blast && !pyra && !plast && !surge && !sap) blast = true;

        if(!sap && smokeColor == null) smokeColor = new Color[]{Color.gray};
        if(flak){
            if(waveColor == null) waveColor = new Color[]{Pal.bulletYellow};
            if(sparkColor == null) sparkColor = new Color[]{Pal.lighterOrange};
        } else if(blast){
            if(waveColor == null) waveColor = new Color[]{Pal.missileYellow};
            if(sparkColor == null) sparkColor = new Color[]{Pal.missileYellowBack};
        } else if(pyra){
            if(waveColor == null) waveColor = new Color[]{Pal.missileYellow};
            if(sparkColor == null) sparkColor = new Color[]{Pal.lightishOrange};
        } else if(plast){
            if(waveColor == null) waveColor = new Color[]{Pal.plastaniumFront};
            if(sparkColor == null) sparkColor = new Color[]{Pal.plastaniumBack};
        } else if(surge){
            if(waveColor == null) waveColor = new Color[]{Pal.bulletYellow};
            if(sparkColor == null) sparkColor = new Color[]{Pal.bulletYellowBack};
        } else if(sap){
            if(waveColor == null) waveColor = new Color[]{Pal.sapBullet};
            if(sparkColor == null) sparkColor = new Color[]{Pal.sapBulletBack};
            if(smokeColor == null) smokeColor = new Color[]{Pal.sap.cpy().a(0.6f), Pal.sap.cpy().a(0.35f)};
        }
        if(lightColor == null) lightColor = sparkColor;

        if(waveRadInterp == null) waveRadInterp = Interp.linear;
        if(waveStrokeInterp == null) waveStrokeInterp = Interp.linear;
        if(sparkRadInterp == null) sparkRadInterp = Interp.pow3Out;
        if(sparkSizeInterp == null) sparkSizeInterp = Interp.linear;
        if(!sap){
            if(smokeRadInterp == null) smokeRadInterp = Interp.pow3Out;
            if(smokeSizeInterp == null) smokeSizeInterp = Interp.linear;
        } else{
            if(smokeRadInterp == null) smokeRadInterp = Interp.pow5Out;
            if(smokeSizeInterp == null) smokeSizeInterp = Interp.pow3In;
        }

        if(drawSmokeLight < 0 && sap) drawSmokeLight = 1;

        if(power < 0) power *= -1; if(rad < 0) rad *= -1;
//UnitTypes.zenith.weapons.get(0).bullet.despawnHit = false; UnitTypes.zenith.weapons.get(1).bullet.despawnHit = false;        

        if(rad > 0){
            float r = rad > 0 ? rad : -rad;
            float m = 0f;

            if(power > 0 && sap){
                if(sparkLen == 0) sparkLen = 1f + (power / 7f);
                if(sparkStroke == 0) sparkStroke = 0.5f + (power / 75f);

                if(smokeLife == 0) smokeLife = (r * 1.4f) + (power / 5f);
                if(smokeRad == 0) smokeRad = Math.max(r - (-4f + r / 4f), 3f);
                if(smokeSize == 0) smokeSize = Math.max(Mathf.pow(power, 0.5f) - 1.5f, 5f);
            }

            m = flak || plast || sap ? 5f : surge ? 4f : 3f;
            if(waveLife == 0) waveLife = r / ((r <= 20f ? 3f : m) * (1 + r / 120f));
            m = flak || plast ? 0.9f : pyra ? 1.2f : 1f;
            if(smokeLife == 0) smokeLife = (30f + (power > 0 ? ((r + power) / 30f) : (r / 20f))) * m;
            lifetime = maxx(lifetime, smokeLife, waveLife);
            if(sparkLife == 0) sparkLife = (lifetime - (lifetime / 3f + (r / 40f))) * (sap ? 0.5f : 1f);

            m = flak ? 1f : plast ? 2f : 0f;
            if(waveRad == 0) waveRad = r + 2 + m;
            m = blast || pyra ? 1f : sap ? -1f : 0f;
            if(waveStroke == 0) waveStroke = 2 + (power > 0 ? (power / 35f) : (r / 20f)) + m;

            m = flak ? 0.85f : plast ? 2f / 3f : surge ? 1.5f : 1f;
            if(sparks < 0) sparks = round((5f + (power > 0 ? power / 20f + r / 60f : r / 20f)) * m);
            m = flak || blast ? 1f : pyra ? 1.1f : 1.25f;
            if(sparkRad == 0) sparkRad = (r + (4f + r / 20f)) * m;
            m = flak || blast ? 1f : pyra ? 0.8f : 2f;
            if(surge) if(sparkLen == 0) sparkLen = (1f + (power > 0 ? (r / 40f) + (power / 15f) : (r / 12.5f))) * m;
                else if(sparkLen == 0) sparkLen = (1f + (power > 0 ? (r / 15f) + (power / 50f) : (r / 12.5f))) * m;
            m = flak ? 1 : blast ? 1.3f : pyra ? 2.2f : 0.75f;
            if(sparkStroke == 0) sparkStroke = (1f + (power > 0 ? (r / 100f) + (power / 45f) : (r / 40f))) * m;

            m = blast ? 1.1f : pyra || plast || sap ? 1.25f : 1f;
            if(smokes < 0) smokes = round((4f + (power > 0 ? power / 40f : r / 15f)) * m);
            if(smokeRad == 0) smokeRad = r >= 15f ? r - 5f : r >= 10f ? r - 3f : Math.max(r - 1f, 2f);
            m = blast ? 1.25f : pyra ? 1.5f : 1f;
            if(smokeSize == 0) smokeSize = ((power > 0 ? (r / 18f) + (power / 7f) : r / 10f)) * m;
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
            e.scaled(waveLife, i -> {
                color(lerpWithA(waveColor, i.fin(interpColor ? waveRadInterp : Interp.linear)));
                stroke(waveStroke * (waveStroke > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(waveStrokeInterp)));
                Lines.circle(e.x, e.y, waveRad * (waveRad > 0 ? i.fin(waveRadInterp) : 1f - i.fin(smokeSizeInterp)));
            });
        }
        if(sparks > 0){
            e.scaled(sparkLife, i -> {
                color(lerpWithA(sparkColor, i.fin(interpColor ? sparkRadInterp : Interp.linear)));
                stroke(sparkStroke * (sparkStroke > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(sparkSizeInterp)));
                randLenVectors(e.id, sparks, sparkRad * (sparkRad > 0 ? i.fin(sparkRadInterp) : 1f - i.fin(smokeSizeInterp)), (x, y) -> {
                    lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), sparkLen * (sparkLen > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(sparkSizeInterp)));
                });
            });
        }
        if(smokes > 0 && sap){
            e.scaled(smokeLife * 0.8f, i -> {
                color(lerpWithA(smokeColor, i.fin(interpColor ? smokeRadInterp : Interp.linear)));
                randLenVectors(e.id + 2, smokes, smokeRad * (smokeRad > 0 ? i.fin(smokeRadInterp) : 1f - i.fin(smokeSizeInterp)), (x, y) -> {
                    float r = ((smokeSize * 2f) / (2f / 3f)) * (smokeSize > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(smokeSizeInterp));
                    //Fill.circle(e.x + x, e.y + y, r);
                    Draw.rect(Core.atlas.find(smokeTex), e.x + x, e.y + y, r, r, smokeBaseRot + (e.time * smokeRot));
                    if(drawSmokeLight > 0) Drawf.light(e.x + x, e.y + y, r * smokeLightScl, lerpWithA(smokeColor, e.fin()), smokeLightOpacity * Draw.getColor().a);
                });
            });
        }
        if(smokes > 0){
            e.scaled(smokeLife, i -> {
                color(lerpWithA(smokeColor, i.fin(interpColor ? smokeRadInterp : Interp.linear)));
                randLenVectors(e.id + 1, smokes, smokeRad * (smokeRad > 0 ? i.fin(smokeRadInterp) : 1f - i.fin(smokeSizeInterp)), (x, y) -> {
                    float r = (smokeSize * 2f) * (smokeSize > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(smokeSizeInterp));
                    //Fill.circle(e.x + x, e.y + y, r);
                    Draw.rect(Core.atlas.find(smokeTex), e.x + x, e.y + y, r, r, smokeBaseRot + (e.time * smokeRot));
                    if(drawSmokeLight > 0) Drawf.light(e.x + x, e.y + y, r * smokeLightScl, lerpWithA(smokeColor, e.fin()), smokeLightOpacity * Draw.getColor().a);
                });
            });
        }
        float lightRad = 2f * (drawWave ? (waveRad > 0 ? waveRad : -waveRad) : sparks > 0 ? (sparkRad > 0 ? sparkRad : -sparkRad) : 0f);
        Drawf.light(e.x, e.y, lightRad, lerpWithA(lightColor, e.fin()), 0.8f * e.fout());
    }

    public Color lerpWithA(Color[] colors, float s){
        int l = colors.length;
        Color a = colors[Mathf.clamp((int)(s * (l - 1)), 0, colors.length - 1)];
        Color b = colors[Mathf.clamp((int)(s * (l - 1) + 1), 0, l - 1)];

        float n = s * (l - 1) - (int)(s * (l - 1));
        float i = 1f - n;
        return new Color(a.r * i + b.r * n, a.g * i + b.g * n, a.b * i + b.b * n, a.a * i + b.a * n);
    }
}
