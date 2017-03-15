package com.jacksonrpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jacksonrpg.JacksonRPG;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "JacksonRPG";

		//game launcher window size
		config.width = 400;
		config.height = 400;
		config.resizable = false;

		new LwjglApplication(new JacksonRPG(), config);
	}
}
