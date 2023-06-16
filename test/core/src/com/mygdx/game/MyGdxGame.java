package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Character character;
	Obstacles obstacles;
	boolean gameOver;
	Texture restartTexture;



	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		character = new Character();
		obstacles = new Obstacles();
		gameOver = false;
		restartTexture = new Texture("RestartBtn.png");

	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.render(batch);
		obstacles.render(batch);
		if(!gameOver) {
			character.render(batch);
		}else{
			batch.draw(restartTexture, 150, 150);
		}
		batch.end();
	}

	public void update(){
		bg.update();
		character.update();
		obstacles.update();
		for (int i = 0; i < Obstacles.obs.length; i++) {
			if(character.position.x > Obstacles.obs[i].position.x && character.position.x < Obstacles.obs[i].position.x+50){
				if(!Obstacles.obs[i].emptySpace.contains(character.position)){
					gameOver = true;
				}
			}
		}
		if(character.position.y <0 || character.position.y > 600){
			gameOver = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
			recreate();
		}


	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void recreate(){
		character.recreate();
		obstacles.recreate();
		gameOver = false;
	}
}