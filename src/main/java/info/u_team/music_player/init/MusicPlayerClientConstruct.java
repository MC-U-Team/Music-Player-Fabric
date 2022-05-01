package info.u_team.music_player.init;

import info.u_team.music_player.config.ClientConfig;
import info.u_team.music_player.dependency.DependencyManager;
import info.u_team.music_player.musicplayer.MusicPlayerManager;

public class MusicPlayerClientConstruct {
	
	public static void construct() {
		System.setProperty("http.agent", "Chrome");
		
		ClientConfig.load();
		
		DependencyManager.load();
		
		MusicPlayerFiles.load();
		
		MusicPlayerManager.setup();
		MusicPlayerKeys.register();
		
		MusicPlayerEventHandler.register();
	}
	
}
