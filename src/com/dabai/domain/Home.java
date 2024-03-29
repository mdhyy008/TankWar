package com.dabai.domain;

import com.dabai.domain.interfaces.Attackable;
import com.dabai.domain.interfaces.Blockable;
import com.dabai.domain.interfaces.Destroyable;
import com.dabai.domain.interfaces.Hitable;

public class Home extends Element implements Blockable, Hitable, Destroyable {

	private int blood = 10;

	public Home(String imgpath, int x, int y) {
		super(imgpath, x, y);

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

	@Override
	public Blast showExplosive(Attackable attackable) {
		this.blood -= attackable.getPower();
		return new Blast(this);
	}

}
