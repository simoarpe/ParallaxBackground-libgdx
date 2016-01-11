package com.rahul.libgdx.parallax.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.rahul.libgdx.parallax.test.GameListener;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		Settings settings = new Settings();
        settings.maxWidth = 1024*8;
        settings.maxHeight = 1024*8;
        settings.filterMin=TextureFilter.Nearest;
        settings.filterMag=TextureFilter.Nearest;

//		TexturePacker.process(settings, "../../desktop/assets-raw", "../../core/assets/data", "main_atlas");
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 450;
		config.vSyncEnabled=true;
		new LwjglApplication(new GameListener(), config);
	}
}
