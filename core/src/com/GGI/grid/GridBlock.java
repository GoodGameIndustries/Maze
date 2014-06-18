/**
 * 
 */
package com.GGI.grid;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class GridBlock {

	private int state = 0; //0=blocked 1=open 2=selected 3=start 4=finish
	private float size = 1f;
	private Vector2 position;
	private Rectangle bounds;
	
	public GridBlock(Vector2 position){
		this.position = position;
		
		bounds = new Rectangle();
		this.bounds.height = size;
		this.bounds.width = size;
	}

	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return bounds;
	}

	public int getState() {
		// TODO Auto-generated method stub
		return state;
	}

	
	
	public void setState(int i){
		state=i;
	}
	
}
