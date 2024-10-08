package VO;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.graphics.Pal;
import mindustry.world.blocks.defense.turrets.ItemTurret;

import VO.entities.*;
import arc.graphics.Color;

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
    
    public static void load(){
        scorch: {
			if(!(Blocks.scorch instanceof ItemTurret))break scorch;
			ItemTurret block = (ItemTurret)Blocks.scorch;
			
			block.ammoTypes.put(Items.coal, new VOFlameBulletType(3.35f, 17f){{
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
            }});
            block.ammoTypes.put(Items.pyratite, new VOFlameBulletType(4f, 60f){{
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

        /*Blocks.duo.ammoTypes.put(Items.copper, new BasicBulletType(2.5f, 9){{
            lifetime = 60;
            ammoMultiplier = 2;
            width = 7;
            height = 9;
            hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        }});
        Blocks.duo.ammoTypes.put(Items.graphite, new BasicBulletType(3.5f, 18){{
            lifetime = 60;
            ammoMultiplier = 4;
            width = 9;
            height = 12;
            reloadMultiplier = 0.6f;
            hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        }});
        Blocks.duo.ammoTypes.put(Items.silicon, new BasicBulletType(3, 12){{
            lifetime = 60;
            ammoMultiplier = 5;
            width = 7;
            height = 9;
            homingPower = 0.1f;
            reloadMultiplier = 1.5f;
            hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        }});
        Blocks.duo.limitRange();

        Blocks.swarmer.ammoTypes.put(Items.blastCompound, new MissileBulletType(3.7f, 10){{
            lifetime = 60;
            width = 8;
            height = 8;
            shrinkY = 0;
            splashDamageRadius = 30;
            splashDamage = 45;
            ammoMultiplier = 5;
            hitEffect = Fx.blastExplosion;
            despawnEffect = Fx.blastExplosion;
            status = StatusEffects.blasted;
            statusDuration = 60;
            trailWidth = 2.4f;
            trailLength = 3;
        }});
        Blocks.swarmer.ammoTypes.put(Items.pyratite, new MissileBulletType(3.7f, 12){{
            lifetime = 60;
            width = 7;
            height = 8;
            shrinkY = 0;
            splashDamageRadius = 20;
            splashDamage = 45;
            ammoMultiplier = 5;
            homingPower = 0.08f;
            hitEffect = Fx.blastExplosion;
            despawnEffect = Fx.blastExplosion;
            frontColor = Pal.lightishOrange;
            backColor = Pal.lightOrange;
            status = StatusEffects.burning;
            makeFire = true;
            trailWidth = 2.1f;
            trailLength = 3;
            trailColor = Pal.lightOrange;
        }});
        Blocks.swarmer.ammoTypes.put(Items.surgeAlloy, new MissileBulletType(3.7f, 18){{
            lifetime = 60;
            width = 8;
            height = 8;
            shrinkY = 0;
            splashDamageRadius = 25;
            splashDamage = 35;
            ammoMultiplier = 4;
            hitEffect = Fx.blastExplosion;
            despawnEffect = Fx.blastExplosion;
            despawnHit = true;
            lightning = 2;
            lightningDamage = 10;
            lightningLength = 10;
            trailWidth = 2.4f;
            trailLength = 3;
        }});
        Blocks.swarmer.limitRange(5);

        Blocks.salvo.ammoTypes.put(Items.copper, new BasicBulletType(2.5f, 11){{
            lifetime = 60;
            ammoMultiplier = 2;
            width = 7;
            height = 9;
            hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        }});
        Blocks.salvo.ammoTypes.put(Items.graphite, new BasicBulletType(3.5f, 20){{
            lifetime = 60;
            ammoMultiplier = 4;
            width = 9;
            height = 12;
            reloadMultiplier = 0.6f;
            hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        }});
        Blocks.salvo.ammoTypes.put(Items.pyratite, new BasicBulletType(3.2f, 18){{
            lifetime = 60;
            ammoMultiplier = 5;
            width = 10;
            height = 12;
            splashDamage = 12;
            splashDamageRadius = 22;
            status = StatusEffects.burning;
            makeFire = true;
            frontColor = Pal.lightishOrange;
            backColor = Pal.lightOrange;
            hitEffect = new MultiEffect(new ExplosionEffect(){{lifetime = 18; waveRad = 0; waveColor = Pal.lightishOrange; smokes = 0; sparks = 5; sparkRad = 24; sparkStroke = 2.5f;}}, Fx.fireHit);
        }});
        Blocks.salvo.ammoTypes.put(Items.silicon, new BasicBulletType(3, 15){{
            lifetime = 60;
            ammoMultiplier = 5;
            width = 7;
            height = 9;
            homingPower = 0.1f;
            reloadMultiplier = 1.5f;
            hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        }});
        Blocks.salvo.ammoTypes.put(Items.thorium, new BasicBulletType(4, 29){{
            lifetime = 60;
            ammoMultiplier = 4;
            width = 10;
            height = 13;
            shootEffect = Fx.shootBig;
            smokeEffect = Fx.shootBigSmoke;
            hitEffect = new MultiEffect(Fx.shootSmall, Fx.hitBulletSmall);
        }});
        Blocks.salvo.limitRange();

        Blocks.spectre.ammoTypes.put(Items.graphite, new BasicBulletType(7.5f, 50){{
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
        }});
        Blocks.spectre.ammoTypes.put(Items.thorium, new BasicBulletType(8, 80){{
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
        }});
        Blocks.spectre.ammoTypes.put(Items.pyratite, new BasicBulletType(7, 70){{
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
            backColor = Pal.lightOrange;
            shootEffect = Fx.shootBig;
            smokeEffect = Fx.shootBigSmoke;
            hitEffect = new MultiEffect(new ExplosionEffect(){{lifetime = 18; waveRad = 0; waveColor = Pal.lightishOrange; smokes = 0; sparks = 7; sparkRad = 27; sparkStroke = 2.6f;}}, Fx.fireHit);
        }});
        Blocks.spectre.limitRange();


        Blocks.breach.ammoTypes.put(Items.beryllium, new BasicBulletType(7.5f, 85){{
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
            hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
            despawnEffect = Fx.hitBulletColor;
            trailWidth = 2.1f;
            trailLength = 10;
        }});
        Blocks.breach.ammoTypes.put(Items.tungsten, new BasicBulletType(8, 95){{
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
            hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
            despawnEffect = Fx.hitBulletColor;
            trailWidth = 2.2f;
            trailLength = 11;
            rangeChange = 40;
        }});
        Blocks.breach.limitRange(12);

        Blocks.disperse.ammoTypes.put(Items.tungsten, new BasicBulletType(8.5f, 65){{
            lifetime = 34;
            width = 16;
            height = 16;
            shrinkY = 0.3f;
            ammoMultiplier = 3;
            collidesGround = false;
            collidesTiles = false;
            frontColor = Color.white;
            backColor = Color.sky;
            trailColor = Color.sky;
            hitColor = Color.sky;
            shootEffect = Fx.shootBig2;
            smokeEffect = Fx.shootSmokeDisperse;
            hitEffect = new MultiEffect(Fx.shootSmallColor, Fx.hitBulletColor);
            despawnEffect = Fx.hitBulletColor;
            trailEffect = Fx.disperseTrail;
            trailChance = 0.44f;
            trailRotation = true;
            rotationOffset = 90;
        }});
        Blocks.disperse.limitRange(5);*/
    }
}
