package VO;

import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.Sounds;
import mindustry.graphics.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.DirectionalUnloader;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;

import VO.custom.*;

public class VOBlockChanges {

    /*private static void addReq(Block target, ItemStack... items){
		ItemStack[] newReq = new ItemStack[items.length + target.requirements.length];
		
		System.arraycopy(target.requirements, 0, newReq, 0, target.requirements.length);
		System.arraycopy(items, 0, newReq, target.requirements.length, items.length);
		
		target.requirements = newReq;
		Arrays.sort(target.requirements, Structs.comparingInt((j) -> j.item.id));
	}
	
	private static void removeReq(Block target, Item... items){
		Seq<ItemStack> req = new Seq<>(ItemStack.class);
		req.addAll(target.requirements);
		
		for(Item item : items)req.each(itemReq -> itemReq.item == item, req::remove);
		
		target.requirements = req.shrink();
	}*/

    private static float wstroke(float val){
        return 2 + (val / 50);
    }
    
    public static void load(){

        ductUnloader: {if(!(Blocks.ductUnloader instanceof DirectionalUnloader))break ductUnloader; DirectionalUnloader block = (DirectionalUnloader)Blocks.ductUnloader; block.speed = 60 / 16;}

        duo: {
			if(!(Blocks.duo instanceof ItemTurret))break duo;
			ItemTurret block = (ItemTurret)Blocks.duo;
			
			block.ammoTypes.putAll(Items.copper, new BasicBulletType(2.5f, 9){{
                lifetime = 60;
                ammoMultiplier = 2;
                width = 7;
                height = 9;
                hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
            }},
            Items.graphite, new BasicBulletType(3.5f, 18){{
                lifetime = 60;
                ammoMultiplier = 4;
                width = 9;
                height = 12;
                reloadMultiplier = 0.6f;
                hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
            }},
            Items.silicon, new BasicBulletType(3, 12){{
                lifetime = 60;
                ammoMultiplier = 5;
                width = 7;
                height = 9;
                homingPower = 0.1f;
                reloadMultiplier = 1.5f;
                hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
            }});
            block.limitRange();
		}

        scatter: {
			if(!(Blocks.scatter instanceof ItemTurret))break scatter;
			ItemTurret block = (ItemTurret)Blocks.scatter;
			
			block.ammoTypes.putAll(Items.scrap, new FlakBulletType(4f, 3){{
                lifetime = 60f;
                width = 6f;
                height = 8f;
                ammoMultiplier = 5f;
                reloadMultiplier = 0.5f;
                splashDamage = 33f;
                splashDamageRadius = 24f;
                shrinkY = -0.4f;
                shootEffect = Fx.shootSmall;
                hitEffect = new Effect(30, e -> {
                    color(Pal.bulletYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(33)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 24f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + 18f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.lighterOrange);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + 28f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3.5f);
                        });
                    });
                    Drawf.light(e.x, e.y, 48f, Pal.lighterOrange, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.lead, new FlakBulletType(4.2f, 3){{
                lifetime = 60f;
                width = 6f;
                height = 8f;
                ammoMultiplier = 4f;
                splashDamage = 40.5f;
                splashDamageRadius = 15f;
                shrinkY = -0.4f;
                shootEffect = Fx.shootSmall;
                hitEffect = new Effect(30, e -> {
                    color(Pal.bulletYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(40.5f)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 15f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + 12f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 3f + 0.5f);
                    });
                    color(Pal.lighterOrange);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + 19f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, 30f, Pal.lighterOrange, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.metaglass, new FlakBulletType(4f, 3){{
                lifetime = 60f;
                width = 6f;
                height = 8f;
                ammoMultiplier = 5f;
                reloadMultiplier = 0.8f;
                splashDamage = 45f;
                splashDamageRadius = 20f;
                shrinkY = -0.4f;
                shootEffect = Fx.shootSmall;
                hitEffect = new Effect(30, e -> {
                    color(Pal.bulletYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(45)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 20f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + 14f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 3.5f + 0.5f);
                    });
                    color(Pal.lighterOrange);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + 24f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, 40f, Pal.lighterOrange, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;

                fragBullets = 6;
                fragBullet = new BasicBulletType(3f, 5){{
                    lifetime = 20f;
                    width = 5f;
                    height = 12f;
                    collidesGround = false;
                    shrinkY = 1f;
                    frontColor = Color.white;
                    backColor = Pal.gray;
                    hitEffect = despawnEffect = Fx.none;
                }};
            }});
            block.limitRange(2f);
		}

        scorch: {
			if(!(Blocks.scorch instanceof ItemTurret))break scorch;
			ItemTurret block = (ItemTurret)Blocks.scorch;
			
			block.ammoTypes.putAll(Items.coal, new VOFlameBulletType(3.35f, 17f){{
                lifetime = 18f;
                hitSize = 7f;
                ammoMultiplier = 3f;
                pierce = true;
                status = StatusEffects.burning;
                statusDuration = 60f * 4;
                particleAmount = 20;
                particleSizeScl = 1.6f;
                particleSpread = 10f;
                smokeColors = new Color[]{Pal.darkFlame, Color.darkGray, Color.gray};
                colors = new Color[]{Color.white, Color.valueOf("fff4ac"), Pal.lightFlame, Pal.darkFlame, Color.gray};
            }},
            Items.pyratite, new VOFlameBulletType(4f, 60f){{
                lifetime = 18f;
                hitSize = 7f;
                ammoMultiplier = 6f;
                rangeChange = 8;
                pierce = true;
                status = StatusEffects.burning;
                statusDuration = 60f * 10;
                particleAmount = 20;
                particleSizeScl = 1.6f;
                particleSpread = 10f;
                smokeColors = new Color[]{Pal.darkPyraFlame, Color.darkGray, Color.gray};
                colors = new Color[]{Color.white, Color.valueOf("fff4ac"), Pal.lightPyraFlame, Pal.darkPyraFlame, Color.gray};
            }});
		}

        hail: {
			if(!(Blocks.hail instanceof ItemTurret))break hail;
			ItemTurret block = (ItemTurret)Blocks.hail;
            float splash = 25f * 0.75f;
			
			block.ammoTypes.putAll(Items.graphite, new ArtilleryBulletType(3f, 20){{
                lifetime = 80f;
                width = height = 11f;
                collidesTiles = false;
                splashDamage = 33f;
                splashDamageRadius = splash;
                knockback = 0.8f;
                hitEffect = new Effect(30, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(33)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * splash);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + (splash - 5f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 3.5f + 0.5f);
                    });
                    color(Pal.missileYellowBack);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + (splash + 4f) * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash * 2f, Pal.missileYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.silicon, new ArtilleryBulletType(3f, 20){{
                lifetime = 80f;
                width = height = 11f;
                ammoMultiplier = 3f;
                reloadMultiplier = 1.2f;
                collidesTiles = false;
                homingPower = 0.08f;
                homingRange = 50f;
                splashDamage = 33f;
                splashDamageRadius = splash;
                knockback = 0.8f;
                hitEffect = new Effect(30, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(33)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * splash);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + (splash - 5f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 3.5f + 0.5f);
                    });
                    color(Pal.missileYellowBack);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + (splash + 4f) * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash * 2f, Pal.missileYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.pyratite, new ArtilleryBulletType(3f, 25){{
                lifetime = 80f;
                width = height = 13f;
                ammoMultiplier = 4f;
                collidesTiles = false;
                splashDamage = 45f;
                splashDamageRadius = splash;
                status = StatusEffects.burning;
                statusDuration = 60f * 12f;
                makeFire = true;
                knockback = 0.8f;
                frontColor = Pal.lightishOrange;
                backColor = Pal.lightOrange;
                trailEffect = Fx.incendTrail;
                hitEffect = new Effect(35, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke(wstroke(45) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * splash);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 6, 2f + (splash - 5f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4.5f + 0.5f);
                    });
                    color(Pal.lightishOrange);
                    e.scaled(22, i -> {stroke(i.fout() * 2.5f);
                        randLenVectors(e.id + 1, 8, 2f + (splash + 4f) * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash * 3f, Pal.lightishOrange, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }});
            block.limitRange(0f);
		}

        swarmer: {
			if(!(Blocks.swarmer instanceof ItemTurret))break swarmer;
			ItemTurret block = (ItemTurret)Blocks.swarmer;
			
			block.ammoTypes.putAll(Items.blastCompound, new MissileBulletType(3.7f, 10){{
                lifetime = 60;
                width = 8;
                height = 8;
                ammoMultiplier = 5;
                splashDamageRadius = 30;
                splashDamage = 45;
                status = StatusEffects.blasted;
                statusDuration = 60;
                homingDelay = 5;
                shrinkY = 0;
                hitEffect = new Effect(30, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(45)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 30f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + 25f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.missileYellowBack);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + 34f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, 60f, Pal.missileYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
                trailWidth = 2.4f;
                trailLength = 3;
            }},
            Items.pyratite, new MissileBulletType(3.7f, 12){{
                lifetime = 60;
                width = 7;
                height = 8;
                ammoMultiplier = 5;
                splashDamageRadius = 20;
                splashDamage = 45;
                status = StatusEffects.burning;
                makeFire = true;
                homingPower = 0.08f;
                homingDelay = 5;
                shrinkY = 0;
                hitEffect = new Effect(35, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke(wstroke(45) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 20f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 6, 2f + 15f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4.5f + 0.5f);
                    });
                    color(Pal.lightishOrange);
                    e.scaled(22, i -> {stroke(i.fout() * 2.5f);
                        randLenVectors(e.id + 1, 8, 2f + 24f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, 60f, Pal.lightishOrange, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
                frontColor = Pal.lightishOrange;
                backColor = Pal.lightOrange;
                trailWidth = 2.1f;
                trailLength = 3;
                trailColor = Pal.lightOrange;
            }},
            Items.surgeAlloy, new MissileBulletType(3.7f, 18){{
                lifetime = 60;
                width = 8;
                height = 8;
                ammoMultiplier = 4;
                splashDamageRadius = 25;
                splashDamage = 35;
                homingDelay = 5;
                shrinkY = 0;
                hitEffect = new Effect(30, e -> {
                    color(Pal.bulletYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(35)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 25f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + 20f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.bulletYellowBack);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 8, 2f + 31f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1.5f + i.fout() * 5f);
                        });
                    });
                    Drawf.light(e.x, e.y, 75f, Pal.bulletYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
                frontColor = Pal.bulletYellow;
                backColor = trailColor = Pal.bulletYellowBack;
                lightning = 2;
                lightningDamage = 10;
                lightningLength = 10;
                trailWidth = 2.4f;
                trailLength = 3;
            }});
            block.limitRange(5);
		}

        salvo: {
			if(!(Blocks.salvo instanceof ItemTurret))break salvo;
			ItemTurret block = (ItemTurret)Blocks.salvo;
			
			block.ammoTypes.putAll(Items.copper, new BasicBulletType(2.5f, 11){{
                lifetime = 60;
                ammoMultiplier = 2;
                width = 7;
                height = 9;
                hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
            }},
            Items.graphite, new BasicBulletType(3.5f, 20){{
                lifetime = 60;
                ammoMultiplier = 4;
                width = 9;
                height = 12;
                reloadMultiplier = 0.6f;
                hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
            }},
            Items.pyratite, new BasicBulletType(3.2f, 18){{
                lifetime = 60;
                ammoMultiplier = 5;
                width = 10;
                height = 12;
                splashDamage = 12;
                splashDamageRadius = 22;
                status = StatusEffects.burning;
                makeFire = true;
                frontColor = Pal.lightishOrange;
                backColor = trailColor = Pal.lightOrange;
                hitEffect = new MultiEffect(new Effect(18, e -> {
                    color(Pal.lightishOrange); stroke(e.fout() * 2.5f);
                    randLenVectors(e.id + 1, 5, 2f + 22f * e.finpow(), (x, y) -> {
                        lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f);
                        Drawf.light(e.x + x, e.y + y, e.fout() * 3f * 4f, Pal.lightishOrange, 0.7f);
                    });
                }), Fx.fireHit);
                despawnEffect = Fx.none;
                trailWidth = 2.6f;
                trailLength = 3;
            }},
            Items.silicon, new BasicBulletType(3, 15){{
                lifetime = 60;
                ammoMultiplier = 5;
                width = 7;
                height = 9;
                homingPower = 0.1f;
                reloadMultiplier = 1.5f;
                hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
            }},
            Items.thorium, new BasicBulletType(4, 29){{
                lifetime = 60;
                ammoMultiplier = 4;
                width = 10;
                height = 13;
                shootEffect = Fx.shootBig;
                smokeEffect = Fx.shootBigSmoke;
                hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
            }});
            block.limitRange();
		}

        ripple: {
			if(!(Blocks.ripple instanceof ItemTurret))break ripple;
			ItemTurret block = (ItemTurret)Blocks.ripple;
            float splash1 = 25f * 0.75f, splash2 = 35f * 0.75f, splash3 = 45f * 0.75f;
			
			block.ammoTypes.putAll(Items.graphite, new ArtilleryBulletType(3f, 20){{
                lifetime = 80f;
                width = height = 11f;
                collidesTiles = false;
                splashDamage = 33f;
                splashDamageRadius = splash1;
                knockback = 0.8f;
                hitEffect = new Effect(30, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(33)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * splash1);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + (splash1 - 5f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.missileYellowBack);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + (splash1 + 4f) * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash1 * 2f, Pal.missileYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.silicon, new ArtilleryBulletType(3f, 20){{
                lifetime = 80f;
                width = height = 11f;
                ammoMultiplier = 3f;
                reloadMultiplier = 1.2f;
                collidesTiles = false;
                splashDamage = 33f;
                splashDamageRadius = splash1;
                homingPower = 0.08f;
                homingRange = 50f;
                knockback = 0.8f;
                hitEffect = new Effect(30, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(33)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * splash1);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + (splash1 - 5f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.missileYellowBack);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + (splash1 + 4f) * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash1 * 2f, Pal.missileYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.pyratite, new ArtilleryBulletType(3f, 24){{
                lifetime = 80f;
                width = height = 13f;
                ammoMultiplier = 4f;
                collidesTiles = false;
                splashDamage = 45f;
                splashDamageRadius = splash1;
                status = StatusEffects.burning;
                statusDuration = 60f * 12f;
                makeFire = true;
                knockback = 0.8f;
                frontColor = Pal.lightishOrange;
                backColor = Pal.lightOrange;
                trailEffect = Fx.incendTrail;
                hitEffect = new Effect(35, e -> {
                    color(Pal.missileYellow);
                    e.scaled(6, i -> {
                        stroke(wstroke(45) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * splash1);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 6, 2f + (splash1 - 5f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4.5f + 0.5f);
                    });
                    color(Pal.lightishOrange);
                    e.scaled(22, i -> {stroke(i.fout() * 2.5f);
                        randLenVectors(e.id + 1, 8, 2f + (splash1 + 4f) * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash1 * 3, Pal.lightishOrange, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.blastCompound, new ArtilleryBulletType(2f, 20, "shell"){{
                lifetime = 80f;
                width = height = 14f;
                ammoMultiplier = 4f;
                collidesTiles = false;
                splashDamage = 55f;
                splashDamageRadius = splash3;
                status = StatusEffects.blasted;
                knockback = 0.8f;
                frontColor = Pal.missileYellow;
                backColor = Pal.missileYellowBack;
                hitEffect = new Effect(30, e -> {
                    color(Pal.missileYellow);
                    e.scaled(7, i -> {
                        stroke((wstroke(55)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * splash3);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 6, 2f + (splash3 - 5f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 5f + 0.5f);
                    });
                    color(Pal.missileYellowBack);
                    e.scaled(20, i -> {stroke(i.fout() * 1.75f);
                        randLenVectors(e.id + 1, 7, 2f + (splash3 + 4f) * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3.5f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash3 * 2f, Pal.missileYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.plastanium, new ArtilleryBulletType(3.4f, 20, "shell"){{
                lifetime = 80f;
                width = height = 13f;
                collidesTiles = false;
                splashDamage = 45f;
                splashDamageRadius = splash2;
                knockback = 1f;
                frontColor = Pal.plastaniumFront;
                backColor = Pal.plastaniumBack;
                hitEffect = new Effect(28, e -> {
                    color(Pal.plastaniumFront);
                    e.scaled(7, i -> {
                        stroke(wstroke(45) * i.fout());
                        Lines.circle(e.x, e.y, 3f + i.fin() * splash2);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 7, 2f + (splash2 - 4f) * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.plastaniumBack);
                    e.scaled(24, i -> {stroke(e.fout());
                        randLenVectors(e.id + 1, 4, 1f + (splash2 + 2f) * e.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, splash2 * 2f, Pal.plastaniumBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;

                fragBullets = 10;
                fragBullet = new BasicBulletType(2.5f, 10, "bullet"){{
                    lifetime = 15f;
                    width = 10f;
                    height = 12f;
                    collidesAir = false;
                    shrinkY = 1f;
                    frontColor = Pal.plastaniumFront;
                    backColor = Pal.plastaniumBack;
                    hitEffect = despawnEffect = Fx.none;
                }};
            }});
		}

        cyclone: {
			if(!(Blocks.cyclone instanceof ItemTurret))break cyclone;
			ItemTurret block = (ItemTurret)Blocks.cyclone;
			
			block.ammoTypes.putAll(Items.metaglass, new FlakBulletType(4f, 6){{
                width = 6f;
                height = 8f;
                ammoMultiplier = 2f;
                reloadMultiplier = 0.8f;
                collidesGround = true;
                splashDamage = 45f;
                splashDamageRadius = 25f;
                explodeRange = 20f;
                shootEffect = Fx.shootSmall;
                hitEffect = new Effect(30, e -> {
                    color(Pal.bulletYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(45)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 25f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + 20f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 3.5f + 0.5f);
                    });
                    color(Pal.lighterOrange);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 6, 2f + 29f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, 50f, Pal.lighterOrange, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;

                fragBullets = 4;
                fragBullet = new BasicBulletType(3f, 12, "bullet"){{
                    lifetime = 20f;
                    width = 5f;
                    height = 12f;
                    shrinkY = 1f;
                    frontColor = Color.white;
                    backColor = Pal.gray;
                    hitEffect = despawnEffect = Fx.none;
                }};
            }},
            Items.plastanium, new FlakBulletType(4f, 8){{
                ammoMultiplier = 4f;
                collidesGround = true;
                splashDamage = 37.5f;
                splashDamageRadius = 40f;
                explodeRange = 20f;
                frontColor = Pal.plastaniumFront;
                backColor = Pal.plastaniumBack;
                shootEffect = Fx.shootBig;
                hitEffect = new Effect(28, e -> {
                    color(Pal.plastaniumFront);
                    e.scaled(7, i -> {
                        stroke(wstroke(37.5f) * i.fout());
                        Lines.circle(e.x, e.y, 3f + i.fin() * 40f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 7, 2f + 35f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.plastaniumBack);
                    e.scaled(24, i -> {stroke(e.fout());
                        randLenVectors(e.id + 1, 4, 1f + 42f * e.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f);
                        });
                    });
                    Drawf.light(e.x, e.y, 80f, Pal.plastaniumBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;

                fragBullets = 6;
                fragBullet = new BasicBulletType(2.5f, 12, "bullet"){{
                    width = 10f;
                    height = 12f;
                    shrinkY = 1f;
                    lifetime = 15f;
                    backColor = Pal.plastaniumBack;
                    frontColor = Pal.plastaniumFront;
                    hitEffect = despawnEffect = Fx.none;
                }};
            }},
            Items.surgeAlloy, new FlakBulletType(4.5f, 13){{
                ammoMultiplier = 5f;
                collidesGround = true;
                splashDamage = 75;
                splashDamageRadius = 38f;
                lightning = 2;
                lightningLength = 7;
                explodeRange = 20f;
                shootEffect = Fx.shootBig;
                hitEffect = new Effect(30, e -> {
                    color(Pal.bulletYellow);
                    e.scaled(6, i -> {
                        stroke((wstroke(75)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 38f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 5, 2f + 33f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                    });
                    color(Pal.bulletYellowBack);
                    e.scaled(20, i -> {stroke(i.fout());
                        randLenVectors(e.id + 1, 8, 2f + 44f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1.5f + i.fout() * 5f);
                        });
                    });
                    Drawf.light(e.x, e.y, 114f, Pal.bulletYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }},
            Items.blastCompound, new FlakBulletType(4f, 8){{
                ammoMultiplier = 5f;
                collidesGround = true;
                splashDamage = 45f;
                splashDamageRadius = 60f;
                status = StatusEffects.blasted;
                statusDuration = 60f;
                shootEffect = Fx.shootBig;
                hitEffect = new Effect(30, e -> {
                    color(Pal.missileYellow);
                    e.scaled(8, i -> {
                        stroke((wstroke(45)) * i.fout());
                        Lines.circle(e.x, e.y, 2f + i.fin() * 60f);
                    });
                    color(Color.gray);
                    randLenVectors(e.id, 6, 2f + 48f * e.finpow(), (x, y) -> {
                        Fill.circle(e.x + x, e.y + y, e.fout() * 5.5f + 0.5f);
                    });
                    color(Pal.missileYellowBack);
                    e.scaled(20, i -> {stroke(i.fout() * 2f);
                        randLenVectors(e.id + 1, 7, 2f + 64f * i.finpow(), (x, y) -> {
                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + i.fout() * 4f);
                        });
                    });
                    Drawf.light(e.x, e.y, 120f, Pal.missileYellowBack, 0.8f * e.fout());
                });
                despawnEffect = Fx.none;
            }});
            block.limitRange();
		}

        spectre: {
            if(!(Blocks.spectre instanceof ItemTurret))break spectre;
			ItemTurret block = (ItemTurret)Blocks.spectre;
			
			block.ammoTypes.putAll(Items.graphite, new BasicBulletType(7.5f, 50){{
                lifetime = 60;
                ammoMultiplier = 4;
                width = 15;
                height = 21;
                hitSize = 4.8f;
                reloadMultiplier = 1.7f;
                knockback = 0.7f;
                shootEffect = Fx.shootBig;
                smokeEffect = Fx.shootBigSmoke;
                hitEffect = new MultiEffect(Fx.shootBig, Fx.hitBulletBig);
            }},
            Items.thorium, new BasicBulletType(8, 80){{
                lifetime = 60;
                ammoMultiplier = 5;
                width = 16;
                height = 23;
                hitSize = 5;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 2;
                knockback = 0.7f;
                shootEffect = Fx.shootBig;
                smokeEffect = Fx.shootBigSmoke;
                hitEffect = new MultiEffect(Fx.shootBig, Fx.hitBulletBig);
            }},
            Items.pyratite, new BasicBulletType(7, 70){{
                lifetime = 60;
                ammoMultiplier = 5;
                width = 16;
                height = 21;
                hitSize = 5;
                splashDamage = 20;
                splashDamageRadius = 25;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 2;
                status = StatusEffects.burning;
                makeFire = true;
                knockback = 0.6f;
                frontColor = Pal.lightishOrange;
                backColor = trailColor = Pal.lightOrange;
                shootEffect = Fx.shootBig;
                smokeEffect = Fx.shootBigSmoke;
                hitEffect = new MultiEffect(new Effect(18, e -> {
                    color(Pal.lightishOrange); stroke(e.fout() * 3.8f);
                    randLenVectors(e.id + 1, 7, 2f + 25f * e.finpow(), (x, y) -> {
                        lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 4f);
                        Drawf.light(e.x + x, e.y + y, e.fout() * 4f * 4f, Pal.lightishOrange, 0.7f);
                    });
                }), Fx.fireHit);
                despawnEffect = Fx.none;
                trailWidth = 4f;
                trailLength = 3;
            }});
            block.limitRange();
        }



        breach: {
            if(!(Blocks.breach instanceof ItemTurret))break breach;
			ItemTurret block = (ItemTurret)Blocks.breach;

            block.ammoTypes.putAll(Items.beryllium, new BasicBulletType(7.5f, 85){{
                width = 12;
                height = 20;
                hitSize = 7;
                ammoMultiplier = 1;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 2;
                buildingDamageMultiplier = 0.3f;
                frontColor = Color.white;
                backColor = Pal.berylShot;
                trailColor = Pal.berylShot;
                hitColor = Pal.berylShot;
                shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                smokeEffect = Fx.shootBigSmoke;
                hitEffect = new MultiEffect(VOFx.shootMediumColor, Fx.hitBulletColor);
                despawnEffect = Fx.hitBulletColor;
                trailWidth = 2.1f;
                trailLength = 10;
            }}, 
            Items.tungsten, new BasicBulletType(8, 95){{
                width = 13;
                height = 19;
                hitSize = 7;
                ammoMultiplier = 1;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 3;
                buildingDamageMultiplier = 0.3f;
                frontColor = Color.white;
                backColor = Pal.tungstenShot;
                trailColor = Pal.tungstenShot;
                hitColor = Pal.tungstenShot;
                shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                smokeEffect = Fx.shootBigSmoke;
                hitEffect = new MultiEffect(VOFx.shootMediumColor, Fx.hitBulletColor);
                despawnEffect = Fx.hitBulletColor;
                trailWidth = 2.2f;
                trailLength = 11;
                rangeChange = 40;
            }});
            block.limitRange(12f);
        }

        diffuse: {
            if(!(Blocks.diffuse instanceof ItemTurret))break diffuse;
			ItemTurret block = (ItemTurret)Blocks.diffuse;

            block.ammoTypes.put(Items.graphite, new BasicBulletType(8f, 41){{
                width = 25f;
                height = 20f;
                hitSize = 7f;
                ammoMultiplier = 1;
                buildingDamageMultiplier = 0.2f;
                knockback = 4f;
                trailWidth = 6f;
                trailLength = 3;
                frontColor = Pal.redLight;
                hitColor = backColor = trailColor = Color.valueOf("ea8878");
                shootEffect = Fx.shootBigColor;
                smokeEffect = Fx.shootSmokeSquareSparse;
                hitEffect = new MultiEffect(VOFx.shootMediumColor, Fx.hitSquaresColor);
                despawnEffect = Fx.hitSquaresColor;
            }});
            block.limitRange(25f);
        }

        disperse: {
            if(!(Blocks.disperse instanceof ItemTurret))break disperse;
			ItemTurret block = (ItemTurret)Blocks.disperse;

            block.targetInterval = 0;
            block.ammoTypes.put(Items.tungsten, new BasicBulletType(8.5f, 65){{
                block.targetGround = false;
                block.targetAir = true;
                block.inaccuracy = 8;
                block.velocityRnd = 0.11f;
                block.shootCone = 30;

                lifetime = 34;
                width = 16;
                height = 16;
                shrinkY = 0.3f;
                ammoMultiplier = 3;
                collidesGround = collidesTiles = false;
                sprite = "mine-bullet";
                backSprite = "large-bomb-back";
                frontColor = Color.white;
                hitColor = backColor = trailColor = Color.sky;
                shootEffect = Fx.shootBig2;
                smokeEffect = Fx.shootSmokeDisperse;
                hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
                despawnEffect = Fx.hitBulletColor;
                trailEffect = Fx.disperseTrail;
                trailChance = 0.44f;
                trailRotation = true;
                rotationOffset = 90;
            }});
            block.limitRange(-5f);
        }

        afflict: {
            if(!(Blocks.afflict instanceof PowerTurret))break afflict;
			PowerTurret block = (PowerTurret)Blocks.afflict;

            block.shootType = new BasicBulletType(5, 180, "large-orb"){{
                lifetime = 80f;
                width = height = 16f;

                pierceCap = 2;

                shrinkX = shrinkY = 0f;
                frontColor = Color.white;
                hitColor = backColor = trailColor = Pal.surge;
                trailLength = 18;
                trailWidth = 2.5f;
                trailInterval = 3f;
                trailParam = 4f;
                shootEffect = new MultiEffect(Fx.shootTitan, new WaveEffect(){{
                    lifetime = 14f;
                    sizeTo = 26f;
                    strokeFrom = 4f;
                    colorTo = Pal.surge;
                }});
                smokeEffect = Fx.shootSmokeTitan;
                trailEffect = Fx.missileTrail;
                hitEffect = new MultiEffect(new ExplosionEffect(){{
                    waveRad = 40f;
                    waveStroke = 4f;
                    waveColor = Pal.surge;
                    smokeColor = Color.gray;
                    sparkColor = Pal.sap;
                }}, Fx.shootBigColor);
                despawnEffect = new ExplosionEffect(){{
                    waveRad = 40f;
                    waveStroke = 4f;
                    waveColor = Pal.surge;
                    smokeColor = Color.gray;
                    sparkColor = Pal.sap;
                }};
                despawnSound = Sounds.dullExplosion;

                fragOnHit = false;
                fragVelocityMin = 0.5f;
                fragVelocityMax = 1.5f;
                fragLifeMin = 0.5f;
                fragBullets = 20;

                bulletInterval = 3f;
                intervalAngle = 180f;
                intervalSpread = 300f;
                intervalRandomSpread = 20f;
                intervalBullets = 2;

                fragBullet = intervalBullet = new BasicBulletType(3f, 35){{
                    lifetime = 35f;
                    width = 9f;
                    height = 15f;
                    hitSize = 5f;
                    pierce = true;
                    pierceBuilding = true;
                    homingPower = 0.2f;
                    buildingDamageMultiplier = 0.3f;
                    trailWidth = 2.1f;
                    trailLength = 8;
                    frontColor = Color.white;
                    hitColor = backColor = trailColor = Pal.surge;
                    hitEffect = new MultiEffect(new WaveEffect(){{
                        lifetime = 10f;
                        sizeTo = 4f;
                        strokeFrom = 4f;
                        colorFrom = colorTo = Pal.surge;
                    }}, Fx.shootSmallColor);
                    despawnEffect = new WaveEffect(){{
                        lifetime = 10f;
                        sizeTo = 4f;
                        strokeFrom = 4f;
                        colorFrom = colorTo = Pal.surge;
                    }};
                }};
            }};
            block.shootSound = Sounds.cannon;
            block.limitRange(9f);
        }
    }

    public static void loadNew(){
        disperse: {
            if(!(Blocks.disperse instanceof ItemTurret))break disperse;
			ItemTurret block = (ItemTurret)Blocks.disperse;

            block.ammoTypes.putAll(Items.carbide, new BasicBulletType(8.5f, 92){{
                block.targetGround = false;
                block.targetAir = true;
                block.inaccuracy = 8;
                block.velocityRnd = 0.11f;
                block.shootCone = 30;

                lifetime = 34;
                width = 16;
                height = 16;
                shrinkY = 0.3f;
                ammoMultiplier = 6;
                reloadMultiplier = 0.8f;
                rangeChange = 44;
                collidesGround = false;
                collidesTiles = false;
                pierce = true;
                pierceBuilding = false;
                pierceCap = 2;
                sprite = "mine-bullet";
                backSprite = "large-bomb-back";
                frontColor = Color.valueOf("c9a5c8");
                hitColor = backColor = trailColor = Color.valueOf("89769a");
                shootEffect = Fx.shootBig2;
                smokeEffect = Fx.shootSmokeDisperse;
                hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
                despawnEffect = Fx.hitBulletColor;
                trailEffect = Fx.disperseTrail;
                trailChance = 0.44f;
                trailRotation = true;
                rotationOffset = 90;
            }},
            Items.thorium, new ArtilleryBulletType(5, 15){{
                block.targetGround = true;
                block.targetAir = false;
                block.inaccuracy = 12;
                block.velocityRnd = 0.2f;
                block.shootCone = 5;

                lifetime = 34;
                width = 14;
                height = 20;
                ammoMultiplier = 3;
                reloadMultiplier = 0.5f;
                rangeChange = -10;
                collidesAir = collidesGround = false;
                collidesTiles = true;
                splashDamage = 110;
                splashDamageRadius = 24;
                scaledSplashDamage = true;
                frontColor = Color.valueOf("f9a3c7");
                hitColor = backColor = trailColor = Color.valueOf("cb8ebf");
                shootEffect = Fx.shootBig2;
                smokeEffect = Fx.shootSmokeDisperse;
                hitEffect = VOFx.disperseExplosion;
                despawnEffect = Fx.none;
            }});
            block.limitRange(-5f);
        }
    }
}
