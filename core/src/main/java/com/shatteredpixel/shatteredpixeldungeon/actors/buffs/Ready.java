package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class Ready extends Buff{
    @Override
    public int icon() {
        return BuffIndicator.TIME;
    }
    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(0.1f, 0.6f, 0.8f);
    }

}
