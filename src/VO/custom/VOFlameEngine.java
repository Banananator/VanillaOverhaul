package VO.custom;

import arc.graphics.Color;
import mindustry.entities.bullet.ContinuousFlameBulletType;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Sounds;
import mindustry.gen.Unit;
import mindustry.type.Weapon;

public class VOFlameEngine extends Weapon{
    public Color teamCol;

    @Override
    public void draw(Unit unit, WeaponMount mount){
        super.draw(unit, mount);
        teamCol = unit.team.color;
    }
    
    public VOFlameEngine(float x, float y, float width, float length, float rotation){
        this.x = x; this.y = y;
        mirror = top = alwaysShooting = continuous = alwaysContinuous = true;
        alternate = display = rotate = false;
        baseRotation = rotation;
        shootY = 0;
        shootSound = Sounds.none;
        bullet = new ContinuousFlameBulletType(0f){{
            lifetime = 0;
            layer = 110;
            colors = new Color[]{teamCol.cpy().a(0.3f), teamCol, Color.white};
            drawFlare = false;
        }};
    }
}
