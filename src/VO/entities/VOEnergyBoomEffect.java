package VO.entities;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;

public class VOEnergyBoomEffect extends Effect{
    public Color color = Color.lightGray, midColor = Color.white, waveColorFrom = Color.white, waveColorTo = Color.white;
    public float width = 5f, length = 15f, midWidth = 2.5f, midLength = 5f;
    public float waveSize = 12f, waveStroke = 3f;
    public float baseRotation = 0f, polygonRotation;
    public int sides = -1;
    public Interp interp = Interp.linear, waveInterp = Interp.pow3Out;
    public boolean interpStroke = true;
    
    public VOEnergyBoomEffect(){
        clip = 100;
        lifetime = 30;
    }

    public void render(EffectContainer e){
        if(waveSize > 0 && waveStroke > 0){
            /*color(waveColorFrom, waveColorTo, e.fin(waveInterp));
            if(interpStroke = true){
                stroke(waveInterp.apply(waveStroke, 0, e.fin()));
            } else stroke(Interp.linear.apply(waveStroke, 0, e.fin()));
            float circleRad = waveInterp.apply(0, waveSize, e.fin());
            Lines.circle(e.x, e.y, circleRad);*/

            float fin = e.fin();
            float ifin = e.fin(interp);
            float ox = e.x + Angles.trnsx(e.rotation, 0, 0), oy = e.y + Angles.trnsy(e.rotation, 0, 0);

            Draw.color(waveColorFrom, waveColorTo, ifin);
            Lines.stroke(interp.apply(waveStroke, 0, fin));

            float rad = interp.apply(0, waveSize, fin);
            Lines.poly(ox, oy, sides <= 0 ? Lines.circleVertices(rad) : sides, rad, polygonRotation + e.rotation);

            Drawf.light(ox, oy, rad * 3, Draw.getColor(), 0.8f * e.fin(waveInterp));
        }

        if(width > 0 && length > 0){
            color(color);
            for(int i = 0; i < 4; i++){
                Drawf.tri(e.x, e.y, width, length * e.fout(interp), (i * 90) + baseRotation);
            }
        }
    
        if(midWidth > 0 && midLength > 0){
            color(midColor);
            for(int i = 0; i < 4; i++){
                Drawf.tri(e.x, e.y, midWidth, midLength * e.fout(interp), (i * 90) + baseRotation);
            }
        }
    }
}
