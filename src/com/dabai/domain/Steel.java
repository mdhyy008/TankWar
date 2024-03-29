package com.dabai.domain;

import com.dabai.domain.interfaces.Attackable;
import com.dabai.domain.interfaces.Blockable;
import com.dabai.domain.interfaces.Destroyable;
import com.dabai.domain.interfaces.Hitable;
import com.dabai.game.Config;

/**
 * 铁墙对象
 * 
 * @author 故事与猫 19-9-19
 */

public class Steel extends Element implements Blockable, Hitable, Destroyable {

	private int blood;// 血量

	// 构造方法：无参,有参
	public Steel(String imgPath, int x, int y) {
		super(imgPath, x, y);
		this.blood = Config.S_BLOOD;
	}

	// 公有的普通方法

	public Blast showExplosive() {
		return new Blast(this);
	}

	@Override
	public Blast showExplosive(Attackable attackable) {
		this.blood -= attackable.getPower();
		return new Blast(this);
	}

	@Override
	public Blast showDestroy() {
		// TODO Auto-generated method stub
		return new Blast(this, true);
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return this.blood <= 0 ? true : false;
	}
}
