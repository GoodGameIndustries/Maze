/**
 * 
 */
package com.GGI.screens;

import java.util.ArrayList;

import com.GGI.grid.GridBlock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
public class GameScreen implements Screen,InputProcessor{
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

		int startRow=(int) (Math.random()*grid.length);
		grid[startRow][0].setState(3);
		genPath(startRow,0);
		

	}
	
	public void genPath(int row, int column){
		int select=(int) (Math.random()*4);
		if(select==0){
			if(row-1 <0){genPath(row,column);}
			else{
				if(grid[row-1][column].getState()!=3){
					grid[row-1][column].setState(1);genPath(row-1,column);
				}
				else{genPath(row,column);}
			}
		}
		else if(select==1){
			if(row+1>grid.length-1 ){genPath(row,column);}
			else{
				if(grid[row+1][column].getState()!=3){
					grid[row+1][column].setState(1);genPath(row+1,column);
				}
				else{genPath(row,column);}
			}
		}
		else if(select==2){
			if(column-1<0 ){genPath(row,column);}
			else{
				if(grid[row][column-1].getState()!=3){
					grid[row][column-1].setState(1);genPath(row,column-1);
				}
				else{genPath(row,column);}
			}
		}
		else if(select==3){
			if(column+1==grid[0].length-1){grid[row][column+1].setState(4);return;}
			if(column+1>grid[0].length-1){genPath(row,column);}
			else{
				if(grid[row][column+1].getState()!=3){
					grid[row][column+1].setState(1);genPath(row,column+1);
				}
				else{genPath(row,column);}
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
		Gdx.input.setInputProcessor(this);
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(getGridTouch(screenX,screenY)!=null){
			getGridTouch(screenX,screenY).setState(2);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(getGridTouch(screenX,screenY)!=null){
			getGridTouch(screenX,screenY).setState(2);
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private GridBlock getGridTouch(int x,int y){
		GridBlock result = null;
		for(GridBlock b: blocks){
			//x=Gdx.graphics.getWidth()-x;
			//y=Gdx.graphics.getHeight()-y;
			Rectangle rect = b.getBounds();
			float x1 = (b.getPosition().x + rect.x)*difficulty;
			float y1 = (b.getPosition().y + rect.y)*difficulty;
			if((x>x1 && x<x1+(rect.width*difficulty)) && (y>y1 && y<y1+(rect.height*difficulty))){
				
				
				result = b;
				break;
			}
		}
		
		return result;
		
	}
}
