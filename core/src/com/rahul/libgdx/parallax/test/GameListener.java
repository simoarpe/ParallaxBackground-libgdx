package com.rahul.libgdx.parallax.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rahul.libgdx.parallax.ParallaxBackground;
import com.rahul.libgdx.parallax.TextureRegionParallaxLayer;
import com.rahul.libgdx.parallax.Utils;
import com.rahul.libgdx.parallax.Utils.WH;
/**
 * Usage example of {@link ParallaxBackground}
 * @author Rahul Verma
 *
 */
public class GameListener extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private OrthographicCamera worldCamera;
	
	private TextureAtlas atlas;
	
	private ParallaxBackground parallaxBackground;
	
	private final float worldWidth = 40;
	private float worldHeight;
	
	private Color clearColor = new Color(0.25f,0.25f,0.25f, 1);
	
	
	@Override
	public void create () {
		
		//worldHeight = Utils.calculateOtherDimension(WH.width, worldWidth, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldHeight= 17;
		batch = new SpriteBatch();
	    worldCamera = new OrthographicCamera();
	    worldCamera.setToOrtho(false,worldWidth,worldHeight);
	    worldCamera.update();
	    
	    createLayers();  
	    
	    Gdx.input.setInputProcessor(new MyInputProcessor());
	}
	 

	private void createLayers() {
		atlas = new TextureAtlas("data/main_atlas.atlas");

		
		TextureRegion mountainsRegionA = atlas.findRegion("Layer0");
		TextureRegionParallaxLayer mountainsLayerA = new TextureRegionParallaxLayer(mountainsRegionA, worldWidth*2, new Vector2(.3f,.6f), WH.width);
	
		TextureRegion mountainsRegionB = atlas.findRegion("Layer1");
		TextureRegionParallaxLayer mountainsLayerB = new TextureRegionParallaxLayer(mountainsRegionB, worldWidth*2/**.7275f*/, new Vector2(.6f,.6f), WH.width);
        //mountainsLayerB.setPadLeft(.2725f*worldWidth);
		
		TextureRegion cloudsRegion = atlas.findRegion("Layer2");
		TextureRegionParallaxLayer cloudsLayer = new TextureRegionParallaxLayer(cloudsRegion, worldWidth*2, new Vector2(.6f,.6f), WH.width);
		//cloudsLayer.setPadBottom(worldHeight*.467f);
		
		TextureRegion buildingsRegionA = atlas.findRegion("Layer3");
		TextureRegionParallaxLayer buildingsLayerA = new TextureRegionParallaxLayer(buildingsRegionA, worldWidth*2, new Vector2(.75f,.6f), WH.width);
       	
		TextureRegion buildingsRegionB = atlas.findRegion("Layer4");
		TextureRegionParallaxLayer buildingsLayerB = new TextureRegionParallaxLayer(buildingsRegionB, worldWidth*2/**.8575f*/, new Vector2(1,.6f), WH.width);
       	//buildingsLayerB.setPadLeft(.07125f*worldWidth);
		//buildingsLayerB.setPadRight(buildingsLayerB.getPadLeft());
       	
		TextureRegion buildingsRegionC = atlas.findRegion("Layer5");
		TextureRegionParallaxLayer buildingsLayerC = new TextureRegionParallaxLayer(buildingsRegionC, worldWidth*2, new Vector2(1.3f,1.3f), WH.width);

		parallaxBackground = new ParallaxBackground();
    	parallaxBackground.addLayers(mountainsLayerA,mountainsLayerB,cloudsLayer,buildingsLayerA,buildingsLayerB,buildingsLayerC);
		//parallaxBackground.addLayers(mountainsLayerA);
		
		
	}


	@Override
	public void render () {
		if(inputOn)
			processInput();
		Gdx.gl.glClearColor(clearColor.r,clearColor.g,clearColor.b, clearColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		parallaxBackground.draw(worldCamera, batch);
		batch.end();
	}
	
	private final float deltaDimen = .2f;
	private void processInput() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)&&(worldCamera.position.x-worldCamera.viewportWidth*.5f>0))
			worldCamera.position.sub(deltaDimen, 0, 0);
		else if(Gdx.input.isKeyPressed(Keys.RIGHT))
			worldCamera.position.add(deltaDimen, 0, 0);
		else if(Gdx.input.isKeyPressed(Keys.DOWN))
			worldCamera.position.sub(0, deltaDimen, 0);
		else if(Gdx.input.isKeyPressed(Keys.UP))
			worldCamera.position.add(0, deltaDimen, 0);
		else if(Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))
			worldCamera.zoom*=1.1f;
		else if(Gdx.input.isKeyPressed(Keys.ALT_RIGHT))
			worldCamera.zoom/=1.1f;
		
		worldCamera.update();
		batch.setProjectionMatrix(worldCamera.combined);
		
	}


	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		atlas.dispose();
	}
	
	
	boolean inputOn = false;
	private class MyInputProcessor extends InputAdapter{
		@Override
		public boolean keyDown(int keycode) {
			if(keycode==Keys.ESCAPE){
				Gdx.app.exit();
				return true;
			}
			if(keycode==Keys.LEFT||keycode==Keys.RIGHT||keycode==Keys.UP||keycode==Keys.DOWN||keycode==Keys.CONTROL_RIGHT||keycode==Keys.ALT_RIGHT)
			{
				inputOn = true;
			}
			return false;
		}
		@Override
		public boolean keyUp(int keycode) {
		    inputOn = false;
		    return false;
		}
		
		
	}
	
	

	
}
