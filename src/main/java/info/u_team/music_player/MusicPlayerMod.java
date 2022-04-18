package info.u_team.music_player;

import info.u_team.music_player.init.MusicPlayerClientConstruct;
import net.fabricmc.api.ClientModInitializer;

public class MusicPlayerMod implements ClientModInitializer {
	
	public static final String MODID = "musicplayer";
	
	@Override
	public void onInitializeClient() {
		MusicPlayerClientConstruct.construct();
	}
}
