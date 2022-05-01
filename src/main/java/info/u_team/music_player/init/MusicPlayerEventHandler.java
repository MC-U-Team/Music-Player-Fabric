package info.u_team.music_player.init;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;

import info.u_team.music_player.gui.GuiMusicPlayer;
import info.u_team.music_player.lavaplayer.api.queue.ITrackManager;
import info.u_team.music_player.musicplayer.MusicPlayerManager;
import info.u_team.music_player.musicplayer.MusicPlayerUtils;
import info.u_team.music_player.musicplayer.SettingsManager;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class MusicPlayerEventHandler {
	
	private static final SettingsManager SETTINGS_MANAGER = MusicPlayerManager.getSettingsManager();
	
	// Used to listen to keyboard events
	
	public static void onKeyInput() {
		handleKeyboard(false, -1, -1);
	}
	
	public static boolean onKeyboardPressed(int keyCode, int scanCode) {
		if (SETTINGS_MANAGER.getSettings().isKeyWorkInGui()) {
			return handleKeyboard(true, keyCode, scanCode);
		}
		return false;
	}
	
	private static boolean handleKeyboard(boolean gui, int keyCode, int scanCode) {
		final boolean handled;
		final ITrackManager manager = MusicPlayerManager.getPlayer().getTrackManager();
		if (isKeyDown(MusicPlayerKeys.OPEN, gui, keyCode, scanCode)) {
			final Minecraft mc = Minecraft.getInstance();
			if (!(mc.screen instanceof GuiMusicPlayer)) {
				mc.setScreen(new GuiMusicPlayer());
			}
			handled = true;
		} else if (isKeyDown(MusicPlayerKeys.PAUSE, gui, keyCode, scanCode)) {
			if (manager.getCurrentTrack() != null) {
				manager.setPaused(!manager.isPaused());
			}
			handled = true;
		} else if (isKeyDown(MusicPlayerKeys.SKIP_FORWARD, gui, keyCode, scanCode)) {
			if (manager.getCurrentTrack() != null) {
				MusicPlayerUtils.skipForward();
			}
			handled = true;
		} else if (isKeyDown(MusicPlayerKeys.SKIP_BACK, gui, keyCode, scanCode)) {
			MusicPlayerUtils.skipBack();
			handled = true;
		} else {
			handled = false;
		}
		return handled;
	}
	
	private static boolean isKeyDown(KeyMapping binding, boolean gui, int keyCode, int scanCode) {
		if (gui) {
			final Key key = InputConstants.getKey(keyCode, scanCode);
			return key != InputConstants.UNKNOWN && key.equals(KeyBindingHelper.getBoundKeyOf(binding));
		} else {
			return binding.consumeClick();
		}
	}
	
	public static void register() {
		// ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
		// System.out.println("XOXOX");
		// if (SETTINGS_MANAGER.getSettings().isKeyWorkInGui()) {
		// ScreenKeyboardEvents.afterKeyPress(screen).register((unused, key, scancode, modifiers) -> {
		// System.out.println("HELLO ROFL");
		// handleKeyboard(true, key, scancode);
		// });
		// }
		// });
	}
	
}
