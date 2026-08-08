package http;

import java.util.Optional;

public class HttpServerConfig {
	private int port;
	private Optional<String> directory;
	public HttpServerConfig(String[] cmdArgs, int port) {
		this.port=port;
		directory=extractDirectoryfromCmdArgs(cmdArgs);
	}
	
	private Optional<String> extractDirectoryfromCmdArgs(String[] cmdArgs) {
		if(cmdArgs.length==2 && cmdArgs[0].contentEquals("--directory") ) {
			return Optional.of(cmdArgs[1]);
		}
		
		return Optional.empty();
		
	}

	public int getPort() {
		return port;
	}

	public Optional<String> getDirectory() {
		return directory;
	}
	
	
	
	
}
