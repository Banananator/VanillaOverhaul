package VO.custom;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.Vec2;
import mindustry.entities.Effect;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;

import arc.Core;
import arc.func.Floatc2;

public class VOShootEffect extends Effect{
    /** Explosion mode. Automaticly sets up colors and affects values of auto-setup. */
    public boolean basic = true, heavy = false, artillery = false, missile = false;
    /** Overrides auto-setup colors if not null. */
    public Color[] flashColor = null, smokeColor = null;
    /** Easy way to set the explosion type. Write {@code basic}, {@code heavy},  {@code artillery} or {@code missile} here. */
    public String type = "";
    /** 
     * Values of explosion auto-setup. If {@code rad} is not 0, automaticly sets up all the values depending 
     * on radius ({@code rad}) and damage of the explosion ({@code power}, if set). Can't be negative. Set
     * {@code rad} to 0 to disable auto-setup.
    */
    public float len = 20, width = 5;
    /** Explosion value. If not 0, overrides itself in auto-setup. If negative, effect will be inverted. */
    public float flashLife = 0f;
    /** Explosion value. If not 0, overrides itself in auto-setup. If negative, effect will be inverted. */
    public float smokeLife = 0f, smokeSize = 0f, smokeLen = 0f, smokeCone = 25f;
    /** Whether to draw light on smoke particles. */
    public boolean drawSmokeLight = false;
    /** Values of smoke's light, if drawn. */
    public float smokeLightScl = 2f, smokeLightOpacity = 0.6f;
    /** Explosion value interpolation. If not {@code null}, overrides itself in auto-setup. */
    public Interp flashInterp = null, smokeLenInterp = null, smokeSizeInterp = null, smokeColorInterp = null;
    /** Amount of particles. Set to 0 to disable particle. */
    public int smokes = -1;
    /** Texture of the smokes. */
    public String smokeRegion = "circle";
    /** Rotation of the smoke, if has a non-circle region. */
    public float smokeRot = 0f, smokeBaseRot = 0f;

    private static final Rand rand = new Rand();
    private static final Vec2 rv = new Vec2();

    /** Creates an advanced explosion effect.
     * @param rad is used for basic auto-setup.
     * @param power is added to {@code rad} for advanced auto-setup. Is usually the explosion's damage.     
     * @param type sets the explosion type. */
    public VOShootEffect(float len, float width, String type){
        this.len = len;
        this.width = width;
        this.type = type;
        clip = 0;
        lifetime = 0;
        followParent = true;
        rotWithParent = false;
    }
    
    /** Creates an advanced explosion effect.
     * @param rad is used for basic auto-setup.
     * @param power is added to {@code rad} for advanced auto-setup. Is usually the explosion's damage.  */
    public VOShootEffect(float len, float width){
        this(len, width, "basic");
    }

    /** Creates an advanced explosion effect. */
    public VOShootEffect(){
        this(15f, 6f, "basic");
    }

    public float maxx(float a, float b, float c, float d){
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    @Override
    public void init(){
        if(!basic && !heavy && !artillery && !missile) basic = true;

        if(type == "basic"){
            basic = true;
            heavy = artillery = missile = false;
        } /*else if(type == "blast"){
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
        }*/

        if(flashColor == null) flashColor = new Color[]{Pal.lighterOrange, Pal.lightOrange};
        if(smokeColor == null) smokeColor = new Color[]{Pal.lighterOrange, Pal.lightOrange, Color.gray};

        if(flashInterp == null) flashInterp = Interp.linear;
        if(smokeLenInterp == null) smokeLenInterp = Interp.pow5Out;
        if(smokeSizeInterp == null) smokeSizeInterp = Interp.linear;
        if(smokeColorInterp == null) smokeColorInterp = Interp.linear;

        if(len < 0) len *= -1; if(width < 0) width *= -1;
//UnitTypes.zenith.weapons.get(0).bullet.despawnHit = false; UnitTypes.zenith.weapons.get(1).bullet.despawnHit = false;        

        if(len > 0 && width > 0){
            float l = len > 0 ? len : -len;
            float w = width > 0 ? width : -width;
            float m = 0f;

            m = basic ? 8f : 0f;
            if(flashLife == 0) flashLife = m + (l / 50f) + (w / 50f);
            m = basic ? 2f : 1f;
            if(smokeLife == 0) smokeLife = ((l + w) / (l / 25f + w / 25f)) * m;
            lifetime = Math.max(lifetime, smokeLife * 1.25f);

            m = basic ? 1f : 1f;
            if(smokes < 0) smokes = round((w / l * w * 1.5f) * m);
            if(smokeLen == 0) smokeLen = l * 0.65f;
            m = basic ? 1f : 1f;
            if(smokeSize == 0) smokeSize = Mathf.pow((l / 20f) + (w / 10f), 0.65f) * m;
        }

        if(lifetime == 0) lifetime = 20f;
        if(flashLife == 0) flashLife = 8f;
        if(smokeLife == 0) smokeLife = 20f;

        if(smokes < 0) smokes = 5;
        if(smokeLen == 0) smokeLen = 24f;
        if(smokeSize == 0) smokeSize = 2f;

        clip = maxx(clip, len, width, smokeLen + smokeSize);
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
        if(smokes > 0){
            e.scaled(smokeLife * 0.8f, i -> {
                color(lerpp(smokeColor, i.fin(smokeColorInterp)));
                customRandLenVectors(e.id, smokes * 2, smokeLen * 1.25f * i.fin(smokeLenInterp), 0, e.rotation, smokeCone * 0.5f * i.finpow(), (x, y) -> {
                    float r = (smokeSize * 2f) * (smokeSize > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(smokeSizeInterp));
                    Draw.rect(Core.atlas.find(smokeRegion), e.x + x, e.y + y, r, r, smokeBaseRot + (e.time * smokeRot));
                    if(drawSmokeLight) Drawf.light(e.x + x, e.y + y, r * smokeLightScl, lerpp(smokeColor, i.fin()), smokeLightOpacity * Draw.getColor().a);
                });
            });
            e.scaled(smokeLife, i -> {
                color(lerpp(smokeColor, i.fin(smokeColorInterp)));
                customRandLenVectors(e.id + 1, smokes, smokeLen * i.fin(smokeLenInterp), 0.5f, e.rotation, smokeCone * 0.8f * i.finpow(), (x, y) -> {
                    float r = (smokeSize * 2f) * (smokeSize > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(smokeSizeInterp));
                    Draw.rect(Core.atlas.find(smokeRegion), e.x + x, e.y + y, r, r, smokeBaseRot + (e.time * smokeRot));
                    if(drawSmokeLight) Drawf.light(e.x + x, e.y + y, r * smokeLightScl, lerpp(smokeColor, i.fin()), smokeLightOpacity * Draw.getColor().a);
                });
            });
            e.scaled(smokeLife * 1.2f, i -> {
                color(lerpp(smokeColor, i.fin(smokeColorInterp)));
                customRandLenVectors(e.id + 2, smokes, smokeLen * 0.75f * i.fin(smokeLenInterp), 0.75f, e.rotation, smokeCone * i.finpow(), (x, y) -> {
                    float r = (smokeSize * 2f) * (smokeSize > 0 ? 1f - i.fin(smokeSizeInterp) : i.fin(smokeSizeInterp));
                    Draw.rect(Core.atlas.find(smokeRegion), e.x + x, e.y + y, r, r, smokeBaseRot + (e.time * smokeRot));
                    if(drawSmokeLight) Drawf.light(e.x + x, e.y + y, r * smokeLightScl, lerpp(smokeColor, i.fin()), smokeLightOpacity * Draw.getColor().a);
                });
            });
        }
        e.scaled(flashLife, i -> {
            color(lerpp(flashColor, i.fin()));
            Drawf.tri(e.x, e.y, width * i.fout(flashInterp), len * i.fout(flashInterp), e.rotation);
            Drawf.tri(e.x, e.y, width * i.fout(flashInterp), 2f + ((len - 5f) / 10f) * i.fout(flashInterp), e.rotation + 180f);
        });
    }

    public Color lerpWithA(Color[] colors, float s){
        int l = colors.length;
        Color a = colors[Mathf.clamp((int)(s * (l - 1)), 0, colors.length - 1)];
        Color b = colors[Mathf.clamp((int)(s * (l - 1) + 1), 0, l - 1)];

        float n = s * (l - 1) - (int)(s * (l - 1));
        float i = 1f - n;
        if(a != null && b != null){
            return new Color(a.r * i + b.r * n, a.g * i + b.g * n, a.b * i + b.b * n, a.a * i + b.a * n);
        } else return Color.white;
    }

    public Color lerpp(Color[] colors, float interp){
        int ll = colors.length;
        float l = (ll - 1) * interp;
        if(ll <= 1) return colors[0];
        Color c = null;
        Color cc = null;
        float interp2 = 0;
        while(interp < 1){
            int i = 1;
            while(i < l) i += 1;
            c = colors[i - 1];
            cc = colors[i];
            interp2 = 1 - (i - l);
            return lerpWithA(new Color[]{c, cc}, interp2);
        }
        return Color.white;
    }

    public static void customRandLenVectors(long seed, int amount, float length, float minLenMult, float angle, float range, Floatc2 cons){
        rand.setSeed(seed);
        for(int i = 0; i < amount; i++){
            rv.trns(angle + rand.range(range), rand.random(length * minLenMult, length));
            cons.get(rv.x, rv.y);
        }
    }
}
