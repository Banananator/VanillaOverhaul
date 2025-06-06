package VO;

import arc.graphics.Color;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.*;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.weapons.RepairBeamWeapon;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;

import VO.custom.*;

public class VOUnitChanges {

    public static void loadDefault(){
        Effect hitLaserBoltGreen = new MultiEffect(
            new WrapEffect(Fx.shootSmallColor, Pal.heal),
            new WrapEffect(Fx.shootSmallColor, Pal.heal, 90),
            new WrapEffect(Fx.shootSmallColor, Pal.heal, 180),
            new WrapEffect(Fx.shootSmallColor, Pal.heal, 270),
            Fx.hitLaser
        );

        UnitTypes.alpha.trailLength = 40;
        UnitTypes.beta.trailLength = 50;
        UnitTypes.gamma.trailLength = 60;
        UnitTypes.incite.trailLength = 30;
        UnitTypes.emanate.trailLength = 35;
        UnitTypes.flare.trailLength = 9;
        UnitTypes.horizon.trailLength = 12;
        UnitTypes.zenith.engineSize = 4.5f;
        UnitTypes.zenith.trailLength = 15;
        UnitTypes.antumbra.engineSize = 6.5f;
        UnitTypes.antumbra.trailLength = 35;
        UnitTypes.eclipse.engineSize = 8;
        UnitTypes.eclipse.trailLength = 65;
        UnitTypes.mono.trailLength = 10;
        UnitTypes.poly.trailLength = 10;
        UnitTypes.mega.trailLength = 10;
        UnitTypes.quad.trailLength = 30;
        UnitTypes.elude.trailLength = 18;

        UnitTypes.alpha.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        UnitTypes.beta.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        UnitTypes.gamma.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);

        UnitTypes.dagger.weapons.get(0).bullet.shootEffect = new VOShootEffect();
        UnitTypes.dagger.weapons.get(0).bullet.smokeEffect = Fx.none;
        UnitTypes.dagger.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        UnitTypes.mace.weapons.clear();
        UnitTypes.mace.weapons.add(new Weapon("flamethrower"){{
            top = false;
            reload = 5;
            shootY = 2;
            shootY = 5.9f;
            shootX = 0.1f;
            recoil = 0;
            ejectEffect = Fx.none;
            shootSound = Sounds.flame;
            bullet = new VOFlameBulletType(4.2f, 17){{
                lifetime = 13;
                hitSize = 7;
                ammoMultiplier = 3;
                keepVelocity = false;
                hittable = false;
                collidesAir = true;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 2;
                status = StatusEffects.burning;
                statusDuration = 60 * 4;
                particleAmount = 20;
                particleSizeScl = 1.6f;
                particleSpread = 10f;
                smokeColors = new Color[]{Pal.darkFlame, Color.darkGray, Color.gray};
                colors = new Color[]{Color.white, Color.valueOf("fff4ac"), Pal.lightFlame, Pal.darkFlame, Color.gray};
            }};
        }});
        UnitTypes.fortress.weapons.get(0).bullet.shootEffect = new VOShootEffect(32, 9);
        UnitTypes.fortress.weapons.get(0).bullet.smokeEffect = Fx.none;
        UnitTypes.fortress.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(35, 80, "blast");
        UnitTypes.fortress.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.scepter.weapons.get(0).bullet.shootEffect = new VOShootEffect(38, 11, "heavy");
        UnitTypes.scepter.weapons.get(0).bullet.smokeEffect = Fx.none;
        UnitTypes.scepter.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootBig, Fx.hitBulletSmall);
        UnitTypes.scepter.weapons.get(1).bullet.shootEffect = new VOShootEffect();
        UnitTypes.scepter.weapons.get(1).bullet.smokeEffect = Fx.none;
        UnitTypes.scepter.weapons.get(1).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        UnitTypes.scepter.weapons.get(2).bullet.shootEffect = new VOShootEffect();
        UnitTypes.scepter.weapons.get(2).bullet.smokeEffect = Fx.none;
        UnitTypes.scepter.weapons.get(2).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        UnitTypes.reign.weapons.get(0).shootY = 15;
        UnitTypes.reign.weapons.get(0).bullet.shootEffect = new VOShootEffect(44, 13, "heavy");
        UnitTypes.reign.weapons.get(0).bullet.smokeEffect = Fx.none;
        UnitTypes.reign.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(13, 18, "blast");
        UnitTypes.reign.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.reign.weapons.get(0).bullet.fragBullet.hitEffect = new VOExplosionEffect(10, 15, "flak");
        UnitTypes.reign.weapons.get(0).bullet.fragBullet.despawnEffect = Fx.none;

        UnitTypes.nova.weapons.get(0).bullet.despawnHit = true;
        UnitTypes.nova.weapons.get(0).bullet.shootEffect = Fx.shootHeal;
        UnitTypes.nova.weapons.get(0).bullet.hitEffect = hitLaserBoltGreen;
        UnitTypes.nova.weapons.get(0).bullet.despawnEffect = Fx.none;

        UnitTypes.crawler.deathExplosionEffect = new VOExplosionEffect(55, 90, "flak");
        UnitTypes.atrax.weapons.get(0).shootY = 5.5f;
        UnitTypes.atrax.weapons.get(0).bullet.shootEffect = new MultiEffect(Fx.shootSmall, new ParticleEffect(){{
            lifetime = 25;
            particles = 4;
            cone = 25;
            length = 20;
            sizeFrom = 1;
            sizeTo = 0;
            colorFrom = Pal.slagOrange;
            colorTo = Pal.slagOrange;
            interp = Interp.pow3Out;
            sizeInterp = Interp.pow3In;
        }});
        UnitTypes.atrax.weapons.get(0).bullet.smokeEffect = Fx.none;
        UnitTypes.atrax.weapons.get(0).bullet.trailWidth = 3.1f;
        UnitTypes.atrax.weapons.get(0).bullet.trailLength = 4;
        UnitTypes.arkyid.weapons.get(3).bullet.shootEffect = new VOShootEffect(38, 11, "heavy");
        UnitTypes.arkyid.weapons.get(3).bullet.smokeEffect = Fx.none;
        UnitTypes.arkyid.weapons.get(3).bullet.hitEffect = new VOExplosionEffect(70, 65, "sap");
        UnitTypes.arkyid.weapons.get(3).bullet.despawnEffect = Fx.none;
        UnitTypes.toxopid.weapons.get(1).bullet.shootEffect = new VOShootEffect(50, 16, "heavy");
        UnitTypes.toxopid.weapons.get(1).bullet.smokeEffect = Fx.none;
        UnitTypes.toxopid.weapons.get(1).bullet.hitEffect = new VOExplosionEffect(80, 75, "sap");
        UnitTypes.toxopid.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.toxopid.weapons.get(1).bullet.fragBullet.hitEffect = new VOExplosionEffect(70, 40, "sap");
        UnitTypes.toxopid.weapons.get(1).bullet.fragBullet.despawnEffect = Fx.none;

        UnitTypes.flare.weapons.get(0).bullet.shootEffect = new VOShootEffect();
        UnitTypes.flare.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.flare.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        UnitTypes.horizon.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(25, 27, "flak");
        UnitTypes.horizon.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.zenith.weapons.get(0).shoot = new ShootAlternate(2){{shots = 2;}};
        UnitTypes.zenith.weapons.get(0).bullet.trailWidth = 2.4f;
        UnitTypes.zenith.weapons.get(0).bullet.trailLength = 3;
        UnitTypes.zenith.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(25, 15, "blast");
        UnitTypes.zenith.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.antumbra.weapons.get(0).bullet.trailWidth = 2.4f;
        UnitTypes.antumbra.weapons.get(1).bullet.trailWidth = 2.4f;
        UnitTypes.antumbra.weapons.get(0).bullet.trailLength = 3;
        UnitTypes.antumbra.weapons.get(1).bullet.trailLength = 3;
        UnitTypes.antumbra.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(20, 37, "blast");
        UnitTypes.antumbra.weapons.get(1).bullet.hitEffect = new VOExplosionEffect(20, 37, "blast");
        UnitTypes.antumbra.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.antumbra.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.antumbra.weapons.get(2).bullet.shootEffect = new VOShootEffect(35, 10);
        UnitTypes.antumbra.weapons.get(2).bullet.smokeEffect = Fx.none;
        UnitTypes.antumbra.weapons.get(2).bullet.hitEffect = new MultiEffect(Fx.shootBig, Fx.hitBulletSmall);
        UnitTypes.eclipse.weapons.get(1).bullet.shootEffect = new VOShootEffect(27, 8);
        UnitTypes.eclipse.weapons.get(2).bullet.shootEffect = new VOShootEffect(27, 8);
        UnitTypes.eclipse.weapons.get(1).bullet.smokeEffect = Fx.none;
        UnitTypes.eclipse.weapons.get(2).bullet.smokeEffect = Fx.none;
        UnitTypes.eclipse.weapons.get(1).bullet.hitEffect = new VOExplosionEffect(25, 65, "flak");
        UnitTypes.eclipse.weapons.get(2).bullet.hitEffect = new VOExplosionEffect(25, 65, "flak");
        UnitTypes.eclipse.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.eclipse.weapons.get(2).bullet.despawnEffect = Fx.none;

        UnitTypes.poly.weapons.get(0).bullet.despawnHit = true;
        UnitTypes.poly.weapons.get(0).bullet.hitEffect = hitLaserBoltGreen;
        UnitTypes.poly.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.poly.weapons.get(0).bullet.trailWidth = 2;
        UnitTypes.poly.weapons.get(0).bullet.trailLength = 4;
        UnitTypes.mega.weapons.get(0).bullet.despawnHit = true;
        UnitTypes.mega.weapons.get(0).bullet.shootEffect = Fx.shootHeal;
        UnitTypes.mega.weapons.get(0).bullet.hitEffect = hitLaserBoltGreen;
        UnitTypes.mega.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.mega.weapons.get(1).bullet.despawnHit = true;
        UnitTypes.mega.weapons.get(1).bullet.shootEffect = Fx.shootHeal;
        UnitTypes.mega.weapons.get(1).bullet.hitEffect = hitLaserBoltGreen;
        UnitTypes.mega.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.quad.weapons.get(0).bullet.hitEffect = VOFx.greenBombPlus;
        UnitTypes.quad.weapons.get(0).bullet.despawnEffect = Fx.none;

        UnitTypes.risso.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        UnitTypes.risso.weapons.get(1).bullet.trailWidth = 2.4f;
        UnitTypes.risso.weapons.get(1).bullet.trailLength = 4;
        UnitTypes.risso.weapons.get(1).bullet.trailColor = Pal.bulletYellowBack;
        UnitTypes.risso.weapons.get(1).bullet.hitEffect = new VOExplosionEffect(25, 10, "blast");
        UnitTypes.risso.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.minke.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(15, 27 * 1.5f, "flak");
        UnitTypes.minke.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.minke.weapons.get(1).bullet.hitEffect = new VOExplosionEffect(30 * 0.75f, 40, "blast");
        UnitTypes.minke.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.bryde.weapons.get(1).bullet.trailWidth = 2.4f;
        UnitTypes.bryde.weapons.get(1).bullet.trailLength =  4;
        UnitTypes.bryde.weapons.get(1).bullet.trailColor = Pal.bulletYellowBack;
        UnitTypes.bryde.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(40, 70, "blast");
        UnitTypes.bryde.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.bryde.weapons.get(1).bullet.hitEffect = new VOExplosionEffect(25, 10, "blast");
        UnitTypes.bryde.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.sei.weapons.get(1).bullet.hitEffect = new MultiEffect(Fx.shootBig, Fx.hitBulletSmall);
        UnitTypes.sei.weapons.get(0).bullet.trailWidth = 2.4f;
        UnitTypes.sei.weapons.get(0).bullet.trailLength = 3;
        UnitTypes.sei.weapons.get(0).bullet.trailColor = Pal.bulletYellowBack;
        UnitTypes.sei.weapons.get(0).bullet.hitEffect = new VOExplosionEffect(35, 45, "blast");
        UnitTypes.sei.weapons.get(0).bullet.despawnEffect = Fx.none;
        UnitTypes.omura.weapons.get(0).bullet = new RailBulletType(){{
            length = 500;
            damage = 1250;
            pierceDamageFactor = 0.5f;
            shootEffect = Fx.railShoot;
            smokeEffect = Fx.shootBig2;
            pointEffect = new MultiEffect(Fx.railTrail, new ParticleEffect(){{
                lifetime = 20;
                particles = 16;
                length = 50;
                cone = 10;
                colorFrom = Color.valueOf("ffffffa15");
                colorTo = Color.valueOf("ffffff15");
                interp = Interp.pow3Out;
                sizeInterp = Interp.linear;
            }});
            pointEffectSpace = 40f;
            hitEffect = Fx.massiveExplosion;
            pierceEffect = Fx.railHit;
        }};

        UnitTypes.retusa.weapons.get(1).bullet.hitEffect = new MultiEffect(new WrapEffect(Fx.shootBigColor, Pal.heal), new WrapEffect(Fx.shootBigColor, Pal.heal, 90), new WrapEffect(Fx.shootBigColor, Pal.heal, 180), new WrapEffect(Fx.shootBigColor, Pal.heal, 270),
            new WaveEffect(){{
                lifetime = 10;
                sizeFrom =  1;
                sizeTo =  32;
                strokeFrom =  2.5f;
                strokeTo =  0;
                colorFrom = Pal.heal;
                colorTo = Pal.heal;
            }}
        );UnitTypes.retusa.weapons.get(1).bullet.despawnEffect = Fx.none;
        UnitTypes.navanax.weapons.get(4).bullet.shootEffect = new MultiEffect(Fx.shootBigColor, Fx.hitEmpSpark);
        UnitTypes.navanax.weapons.get(4).bullet.smokeEffect = new MultiEffect(Fx.shootBigSmoke2, Fx.hitLaser);
        UnitTypes.navanax.weapons.get(4).bullet.lightning = 9;
        UnitTypes.navanax.weapons.get(4).bullet.lightningDamage = 0;
        UnitTypes.navanax.weapons.get(4).bullet.lightningLength = 16;
        UnitTypes.navanax.weapons.get(4).bullet.lightningLengthRand = 0;
        UnitTypes.navanax.weapons.get(4).bullet.lightningColor = Pal.heal;
        UnitTypes.navanax.weapons.get(4).bullet.hitEffect = new MultiEffect(VOFx.navanaxHit, 
            new ParticleEffect(){{
                lifetime = 120;
                particles = 1;
                length = 0;
                sizeFrom = 100;
                sizeTo = 100;
                colorFrom = Color.valueOf("98ffa920");
                colorTo = Color.valueOf("98ffa905");
            }}
        );


        
        UnitTypes.stell.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
        UnitTypes.precept.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootBigColor, Fx.blastExplosion);
        UnitTypes.precept.weapons.get(0).bullet.fragBullet.hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
        UnitTypes.vanquish.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootBigColor, Fx.hitBulletColor);
        UnitTypes.vanquish.weapons.get(1).bullet.hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
        UnitTypes.vanquish.weapons.get(2).bullet.hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);

        UnitTypes.merui.weapons.get(0).bullet.recoil = 1;
        UnitType anthicus = UnitTypes.anthicus.weapons.get(0).bullet.spawnUnit;
        anthicus.deathExplosionEffect = anthicus.weapons.get(0).bullet.hitEffect = anthicus.weapons.get(0).bullet.despawnEffect = Fx.none;
        anthicus.weapons.get(0).bullet.shootEffect = new MultiEffect(new Effect(30, e -> {
            color(Pal.techBlue); stroke(e.fout() * 2.25f);
            randLenVectors(e.id + 1, 9, 3f, 30f * e.finpow(), (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 5f);
            });
        }), new WrapEffect(Fx.dynamicSpikes, Pal.techBlue, 24f));
        BulletType tecta = UnitTypes.tecta.weapons.get(0).bullet;
        tecta.homingPower = 0.08f;
        tecta.homingDelay = 10;
        tecta.hitEffect = new MultiEffect(new Effect(30, e -> {
            color(Pal.techBlue); stroke(e.fout() * 2.25f);
            randLenVectors(e.id + 1, 9, 3f, 35f * e.finpow(), (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 5f);
            });
        }), new WrapEffect(Fx.dynamicSpikes, Pal.techBlue, 29f));

        UnitTypes.collaris.weapons.get(0).bullet.fragBullet.hitEffect = UnitTypes.collaris.weapons.get(0).bullet.fragBullet.despawnEffect = new MultiEffect(new ExplosionEffect(){{
            lifetime = 30f;
            waveStroke = 2f;
            waveColor = sparkColor = Pal.techBlue;
            waveRad = 5f;
            smokeSize = 0f;
            smokeSizeBase = 0f;
            sparks = 5;
            sparkRad = 20f;
            sparkLen = 6f;
            sparkStroke = 2f;
        }}, Fx.blastExplosion, Fx.shootBigColor);

        UnitTypes.elude.weapons.get(0).bullet.hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
        Weapon avert = UnitTypes.avert.weapons.get(0);
        avert.bullet.lifetime = 18;
        avert.shoot = new ShootHelix(){{mag = 1; scl = 4.3f;}};
        avert.bullet.homingPower = 0.08f; avert.bullet.homingRange = 1;
        avert.bullet.hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
        avert.bullet.trailLength = 5;
    }

    public static void loadOverrides(){
        UnitTypes.oct.weapons.add(new Weapon(){{
            controllable = aiControllable = mirror = useAttackRange = false;
            reload = 1;
            shootCone = 360;
            shootOnDeath = true;
            bullet = new ExplosionBulletType(1350, 160){{
                shootEffect = VOFx.octDeathEffect;
                smokeEffect = hitEffect = despawnEffect = Fx.none;
                healPercent = 100;
            }};
        }});
        UnitTypes.oct.deathExplosionEffect = Fx.none;



        UnitTypes.evoke.weapons.add(new RepairBeamWeapon(){{
            targetBuildings = targetUnits = true;
            targetSwitchInterval = 0;
            beamWidth = 0.5f;
            x = y = 0;
            shootY = 0f;
            reload = 30f;
            rotate = true;
            rotateSpeed = 360;
            mirror = false;
            repairSpeed = 0.5f;
            laserColor = healColor = Pal.accent;
            bullet = new BulletType(){{
                maxRange = 100f;
            }};
        }});
        UnitTypes.incite.weapons.add(new RepairBeamWeapon(){{
            targetBuildings = targetUnits = true;
            targetSwitchInterval = 0;
            beamWidth = 0.5f;
            x = 0;
            y = -0.75f;
            shootY = 0f;
            reload = 30f;
            rotate = true;
            rotateSpeed = 100;
            mirror = false;
            repairSpeed = 0.75f;
            laserColor = healColor = Pal.accent;
            bullet = new BulletType(){{
                maxRange = 115f;
            }};
        }});
        UnitTypes.emanate.weapons.add(new RepairBeamWeapon(){{
            targetBuildings = targetUnits = true;
            targetSwitchInterval = 0;
            beamWidth = 0.5f;
            x = 0;
            y = -2;
            shootY = 0f;
            reload = 30f;
            rotate = true;
            rotateSpeed = 100;
            mirror = false;
            repairSpeed = 1f;
            laserColor = healColor = Pal.accent;
            bullet = new BulletType(){{
                maxRange = 130f;
            }};
        }});
        
        BulletType cleroi = UnitTypes.cleroi.weapons.get(0).bullet;
        cleroi.splashDamage = 18f; cleroi.splashDamageRadius = 16f;
        cleroi.hitEffect = cleroi.despawnEffect = new MultiEffect(Fx.hitBulletColor, new WaveEffect(){{
            lifetime = 9f;
            sizeTo = 19f;
            strokeFrom = 3f;
            colorFrom = colorTo = Pal.techBlue;
        }});
        cleroi.fragAngle = cleroi.fragSpread = cleroi.fragRandomSpread = 0;
        cleroi.fragOnAbsorb = false;
        cleroi.fragBullets = 1; cleroi.fragBullet = new LaserBulletType(20){{
            lifetime = 20;
            length = 18;
            width = 12;
            pierceArmor = true;
            hitEffect = Fx.hitBulletColor;
            hitColor = Pal.techBlue;
            colors = new Color[]{Pal.techBlue.cpy().a(0.4f), Pal.techBlue, Color.white};
        }};

        Weapon avert = UnitTypes.avert.weapons.get(0);
        avert.shoot = new ShootBarrel(){{
            shots = 2;
            barrels =  new float[]{
                0, 0, 15,
                0, 0, -15,
            };
        }};
        avert.bullet.lifetime = 19.5f;
        avert.bullet.homingPower = 1; avert.bullet.homingRange = 32; avert.bullet.homingDelay = 5;
        avert.bullet.trailLength = 40;
    }

    public static void loadNewEngines(){
        UnitTypes.avert.engines.clear();
        UnitTypes.avert.abilities.addAll(
            new VOFlameEngineAbility(7.75f, -8.75f, 1.75f, 7f, 225f, 4f, 1, 18f),
            new VOFlameEngineAbility(-7.75f, -8.75f, 1.75f, 7f, 135f, 4f, 1, 18f),
            new VOFlameEngineAbility(8.75f, -3f, 1.4f, 6f, 225f, 4f, 1, 15f),
            new VOFlameEngineAbility(-8.75f, -3f, 1.4f, 6f, 135f, 4f, 1, 15f)
        );
        UnitTypes.quell.engines.clear();
        UnitTypes.quell.engineSize = 0;
        UnitTypes.quell.abilities.addAll(
            new VOFlameEngineAbility(0f, -13f, 4f, 13.5f, 180f, 2f, 1, 24f),
            new VOFlameEngineAbility(14f, -13.5f, 2.25f, 8.5f, 225f, 4f, 1, 20f),
            new VOFlameEngineAbility(-14f, -13.5f, 2.25f, 8.5f, 135f, 4f, 1, 20f),
            new VOFlameEngineAbility(17f, -6.5f, 1.75f, 6.5f, 225f, 4f, 1, 18f),
            new VOFlameEngineAbility(-17f, -6.5f, 1.75f, 6.5f, 135f, 4f, 1, 18f)
        );
        UnitTypes.disrupt.engines.clear();
        UnitTypes.disrupt.engineSize = 0;
        UnitTypes.disrupt.abilities.addAll(
            new VOFlameEngineAbility(0f, -22.5f, 5.5f, 17.5f, 180f, 2f, 1, 28f),
            new VOFlameEngineAbility(21.75f, -13.25f, 3f, 12f, 255f, 3f, 1, 24f),
            new VOFlameEngineAbility(-21.75f, -13.25f, 3f, 12f, 105f, 3f, 1, 24f),
            new VOFlameEngineAbility(21f, -21.75f, 2.75f, 9f, 210f, 3f, 1, 20f),
            new VOFlameEngineAbility(-21f, -22.75f, 2.75f, 9f, 150f, 3f, 1, 20f)
        );
    }
}