package com.dabai.domain;

import com.dabai.domain.interfaces.Blockable;
import com.dabai.domain.interfaces.Moveable;
import com.dabai.game.Config;
import com.dabai.utils.CollsionUtils;
import com.dabai.utils.Direction;

public class EnemyTank extends Element implements Moveable{

	private int blood;
	private int speed = 16;
	private int power;
	private Direction direction = Direction.DOWN;

	private int bullettime = 400;
	
	private long lastFire = 0l;
	private long lastMove = 0l;
	
	public EnemyTank(String imgPath, int x, int y) {
		super(imgPath, x, y);

		power = Config.POWER;
		
	}
	

	public void move() {
		long nowTime = System.currentTimeMillis();
		if (nowTime - lastMove < 10) {
			return;
		}
		lastMove = nowTime;
		//如果当前移动方向是不可移动方向
		if (direction.equals(unmoveDirection)) {
			direction = this.getRandomDirection();//无法移动时获取随机方向
			return;
		}
		
		switch (direction) {
		case UP:
			y -= speed;
			break;

		case DOWN:
			y += speed;
			break;

		case LEFT:
			x -= speed;
			break;

		case RIGHT:
			x += speed;
			break;

		case RESET:
			//复位
			x = Config.WIDTH / 2 - Config.PX / 2;
			y = Config.HEIGHT - Config.PX;

			break;
		default:
			break;
		}

		
		/**
		 * 越界检测
		 */
		if (x < 0) {
			direction = this.getRandomDirection();
			x = 0;
		} else if (x > Config.WIDTH - this.width) {
			direction = this.getRandomDirection();
			x = Config.WIDTH - this.width;
		}

		if (y < 0) {
			direction = this.getRandomDirection();
			y = 0;
		} else if (y > Config.HEIGHT - this.height) {
			direction = this.getRandomDirection();
			y = Config.HEIGHT - this.height;
			
		}
	}

	/**
	 * 重写父类draw 2019-10-17 下午2:48:31
	 */

	
	String tank_u = "res\\img\\enemy_1_u.gif";
	String tank_d = "res\\img\\enemy_1_d.gif";
	String tank_l = "res\\img\\enemy_1_l.gif";
	String tank_r = "res\\img\\enemy_1_r.gif";
	
	@Override
	public void draw() {
		switch (direction) {
		case UP:
			this.imgPath = tank_u;
			break;
		case DOWN:
			this.imgPath = tank_d;
			break;
		case LEFT:
			this.imgPath = tank_l;
			break;
		case RIGHT:
			this.imgPath = tank_r;
			break;
		default:
			break;
		}
		super.draw();
	}
	
	
	/**
	 * 设置坦克皮肤
	 */
	String img_root = "res\\img\\";
	public void skin(int i) {
		
		switch (i) {
		case 1:
			tank_u = img_root + "tank2_u.gif";
			tank_d = img_root + "tank2_d.gif";
			tank_l = img_root + "tank2_l.gif";
			tank_r = img_root + "tank2_r.gif";
			break;
		case 2:
			
			break;
		default:
			break;
		}
	}
	
	public Bullet shot() {
		
		long nowTime = System.currentTimeMillis();//获得当前时间戳
		
		if ((nowTime - lastFire) < bullettime) {
			return null;
		}
		lastFire = nowTime;
		return null;
	}

	
	private Direction unmoveDirection;//不可移动的方向
	
	//实现接口中的方法  
	public boolean checkCollsion(Blockable blockable) {
		
		Element element = (Element)blockable;
		
		int x1 = element.x;
		int y1 = element.y;
		int w1 = element.width;
		int h1 = element.height;
		
		int x2 = this.x;
		int y2 = this.y;
		
		switch (direction) {
		case UP:
			y2 -= this.speed;
			break;
		case DOWN:
			y2 += this.speed;
			break;
		case LEFT:
			x2 -= this.speed;
			break;
		case RIGHT: 
			x2 += this.speed;
			break;

		default:
			break;
		}
		
		boolean bool = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, width, height);
		if (bool) {
			this.unmoveDirection = direction;
		}else {
			this.unmoveDirection = null;
		}
		return bool;
	}


	public int getPower() {
		return power;
	}


	public Direction getDirection() {
		return direction;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setBullettime(int bullettime) {
		this.bullettime = bullettime;
	}
	
	private Direction getRandomDirection() {
		int random = (int)(Math.random() * 4);
		
		switch (random) {
		case 0:
			return Direction.UP;
		case 1:
			return Direction.DOWN;
		case 2:
			return Direction.LEFT;
		case 3:
			return Direction.RIGHT;
		default:
			return Direction.UP;
		}
	}
	
	

}
