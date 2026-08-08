package handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesHandler extends AbstractHandler {

	private String directory;
	public FilesHandler( String request,String directory) {
		super("/files/", request);
		this.directory=directory;
	}

	@Override
	public boolean areConditionsMatched() {
		return super.areConditionsMatched()&& new File(getFilePath()).exists();
	}
	@Override
	public String getResponse() {
		return getResponseStatusLine()+getResponseHeaders()+getFileContent();
	}
	
	private String getResponseHeaders() {
		return  "\r\nContent-Type: application/octet-stream\r\nContent-Length: "+getFileContent().length()+"\r\n\r\n";
	}
	
	private String getFileContent() {
		File file = new File(getFilePath());
		if(file.exists()) {
			return getFileContent(getFilePath());
		}
		return directory;
	}

	private String getFilePath() {
		return directory+"/"+getFileName();
	}
	
	private String getFileContent(String path) {
		String fileContent="";
		try  {
			fileContent=Files.readString(Path.of(path));
		} catch (IOException e) {
			e.getMessage();
		}
		return fileContent;
	}
	private String getFileName() {
		 String [] urlParts=getUrlParts();
		 return urlParts[urlParts.length-1];
	 }
	
	 private String[] getUrlParts() {
		 String urlFromRequest=getUrlFromRequestString();
		 return urlFromRequest.split("/");
	 }
}
