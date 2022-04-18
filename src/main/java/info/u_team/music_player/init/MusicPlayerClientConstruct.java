package info.u_team.music_player.init;

import info.u_team.music_player.dependency.DependencyManager;

public class MusicPlayerClientConstruct {
	
	public static void construct() {
		System.setProperty("http.agent", "Chrome");
		
		// ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.CONFIG);
		
		DependencyManager.load();
		
		MusicPlayerFiles.load();
		
		// BusRegister.registerMod(MusicPlayerManager::registerMod);
		// BusRegister.registerMod(MusicPlayerKeys::registerMod);
		//
		// BusRegister.registerForge(MusicPlayerEventHandler::registerForge);
	}
	
}
