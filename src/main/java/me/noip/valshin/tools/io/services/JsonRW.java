package me.noip.valshin.tools.io.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import me.noip.valshin.db.entities.RamStorage;
import me.noip.valshin.tools.io.DataRW;

@Service
public class JsonRW implements DataRW {
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${File_DB_Path}")
	private String path;

	@Override
	public RamStorage readData() {
		RamStorage data = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
	        try {
	        	data = mapper.readValue(fis, RamStorage.class);
	        } finally {
	        	fis.close();
	        }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		return data;
	}

	@Override
	public int writeData(RamStorage storage) {
		FileOutputStream fos = null;
		try {
			System.out.println(path);
			fos = new FileOutputStream(path);
	        try {
	        	mapper.writeValue(new FileOutputStream(path), storage);
	        } finally {
	        	fos.close();
	        }
	        return 0;
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		return 1;
	}
}

