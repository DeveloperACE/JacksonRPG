package com.jacksonrpg.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jacksonrpg.JacksonRPG;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "JacksonRPG";



		//configuration.addIcon("icon32.png", Files.FileType.Internal);
		//configuration.addIcon("icon16.png", Files.FileType.Internal);
		//configuration.preferencesDirectory = ".gdxtexturepackergui";

		//game launcher window size
		config.width = 400;
		config.height = 400;
		config.resizable = false;
        config.addIcon("icon_128.png", FileType.Internal);
        config.addIcon("icon_32.png", FileType.Internal);
        config.addIcon("icon_16.png", FileType.Internal);

		new LwjglApplication(new JacksonRPG(), config);
	}
}
