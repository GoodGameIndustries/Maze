/**
 * 
 */
package com.GGI.screens;

import java.util.ArrayList;

import com.GGI.grid.GridBlock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Emmett Deen
 *
 */
public class GameScreen implements Screen{
	private float xU;
	private float yU;
	private int difficulty;
	//private OrthographicCamera cam;
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	private ArrayList<GridBlock> blocks;
	private GridBlock[][] grid;
	private int sideBuffer;
	
	public GameScreen(int difficulty){
		this.difficulty = difficulty;
		
	}
	
	private void genGrid() {
		grid = new GridBlock[(int) xU][(int) yU];
		for(int i = 0; i < xU; i++){
			for(int j = 0; j < yU; j++){
				blocks.add(new GridBlock(new Vector2(i,j)));
				grid[i][j] = blocks.get(blocks.size()-1);
			}
		}
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f,1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		//debugRenderer.setProjectionMatrix(cam.view);
		debugRenderer.begin(ShapeType.Line);
		for (GridBlock b : blocks) {
			Rectangle rect = b.getBounds();
			float x1 = (b.getPosition().x + rect.x);
			float y1 = (b.getPosition().y + rect.y);
			
			switch(b.getState()){
			case 0: debugRenderer.setColor(new Color(0,1,0,1));
			break;
			case 1: debugRenderer.setColor(new Color(0,0,0,1));
			break;
			case 2: debugRenderer.setColor(new Color(0,0,1,1));
			break;
			case 3: debugRenderer.setColor(new Color(1,1,0,1));
			break;
			case 4: debugRenderer.setColor(new Color(1,0,0,1));
			break;
			}
			
			//debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect((x1*difficulty)+(sideBuffer/2), y1*difficulty, rect.width*difficulty, rect.height*difficulty);
		}
		debugRenderer.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		xU = Gdx.graphics.getWidth()/difficulty;
		yU = Gdx.graphics.getHeight()/difficulty;

		sideBuffer = Gdx.graphics.getWidth()%difficulty;

		//this.cam = new OrthographicCamera(xU,yU);
		blocks = new ArrayList<GridBlock>();
		genGrid();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
