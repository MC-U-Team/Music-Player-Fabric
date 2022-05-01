package info.u_team.music_player.dependency.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.spi.URLStreamHandlerProvider;
import java.nio.file.Files;
import java.nio.file.Path;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class JarLookupURLStreamHandlerProvider extends URLStreamHandlerProvider {
	
	public static final String PROTOCOL = "jarlookup";
	
	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		if (PROTOCOL.equals(protocol)) {
			return new URLStreamHandler() {
				
				@Override
				protected URLConnection openConnection(URL url) throws IOException {
					return new URLConnection(url) {
						
						@Override
						public void connect() throws IOException {
						}
						
						@Override
						public InputStream getInputStream() throws IOException {
							final ModContainer container = FabricLoader.getInstance().getModContainer(url.getHost()).orElseThrow(() -> {
								return new IOException("Modid " + url.getHost() + " does not exists");
							});
							final Path path = container.findPath(url.getPath().substring(1)).orElseThrow(() -> {
								return new IOException("Path " + url.getPath().substring(1) + " in mod " + url.getHost() + " does not exists");
							});
							return Files.newInputStream(path);
						}
					};
				}
			};
		}
		return null;
	}
}
