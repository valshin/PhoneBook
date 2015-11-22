package me.noip.valshin.db.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;

public class FileDB extends RamDB{
	@Value("${File_DB_Path}")
	private String path;
	private File file;
	
	public FileDB() {
		file = new File(path);
		ObjectMapper mapper = new ObjectMapper();
        try {
			List data = mapper.readValue(file, new TypeReference<List>(){});
			this.notes = (Map<String, Note>) data.get(0);
			this.users = (Map<String, User>) data.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
