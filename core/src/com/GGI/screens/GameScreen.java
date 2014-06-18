/**
 * 
 */
package com.GGI.screens;

import java.util.ArrayList;

import com.GGI.grid.GridBlock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	private Texture open;
	private Texture blocked;
	private Texture selected;
	private Texture start;
	private Texture end;
	
	private SpriteBatch pix;
	
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

		

		grid[(int) (Math.random()*grid.length)][0].setState(3);
		grid[(int) (Math.random()*grid.length)][grid[0].length-1].setState(4);

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
		
		pix.begin();
		
		for (GridBlock b : blocks) {
			
			
			Texture temp = null;
			
			Rectangle rect = b.getBounds();
			float x1 = (b.getPosition().x + rect.x);
			float y1 = (b.getPosition().y + rect.y);
			
			switch(b.getState()){
			case 0: temp = blocked;
			break;
			case 1: temp = open;
			break;
			case 2: temp = selected;
			break;
			case 3: temp = start;
			break;
			case 4: temp = end;
			break;
			}
			pix.draw(temp,(x1*difficulty)+(sideBuffer/2), y1*difficulty, rect.width*difficulty, rect.height*difficulty);
		}
		
		pix.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		xU = Gdx.graphics.getWidth()/difficulty;
		yU = Gdx.graphics.getHeight()/difficulty;
		
		pix = new SpriteBatch();
		
		sideBuffer = Gdx.graphics.getWidth()%difficulty;

		//this.cam = new OrthographicCamera(xU,yU);
		blocks = new ArrayList<GridBlock>();
		setImages();
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

	private void setImages() {
		open = new Texture(Gdx.files.internal("open.png"));
		blocked = new Texture(Gdx.files.internal("blocked.png"));
		selected = new Texture(Gdx.files.internal("selected.png"));
		start = new Texture(Gdx.files.internal("start.png"));
		end = new Texture(Gdx.files.internal("end.png"));
	}
	
}
