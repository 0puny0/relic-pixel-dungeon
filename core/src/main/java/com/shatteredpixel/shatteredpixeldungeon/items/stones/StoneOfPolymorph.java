package com.shatteredpixel.shatteredpixeldungeon.items.stones;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Sheep;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.TargetHealthIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class StoneOfPolymorph extends  Runestone {
    {
        image = ItemSpriteSheet.STONE_AUGMENTATION;
    }
    @Override
    protected void activate(int cell) {
        Char ch = Actor.findChar( cell);
        if (ch != null && !(ch instanceof Hero)
                && !ch.properties().contains(Char.Property.BOSS)
                && !ch.properties().contains(Char.Property.MINIBOSS)){
            Sheep sheep = new Sheep();
            sheep.lifespan = 10;
            sheep.pos = ch.pos;
            ch.destroy();
            ch.sprite.killAndErase();
            Dungeon.level.mobs.remove(ch);
            TargetHealthIndicator.instance.target(null);
            GameScene.add(sheep);
            CellEmitter.get(sheep.pos).burst(Speck.factory(Speck.WOOL), 4);
            Sample.INSTANCE.play(Assets.Sounds.PUFF);
            Sample.INSTANCE.play(Assets.Sounds.SHEEP);
            Dungeon.level.occupyCell(sheep);
        } else {
            CellEmitter.get(cell).burst(Speck.factory(Speck.WOOL), 4);
            Sample.INSTANCE.play(Assets.Sounds.PUFF);
            GLog.w(Messages.get(this, "nothing"));
        }
    }

    @Override
    public int value() {
        return super.value()*2;
    }
}
