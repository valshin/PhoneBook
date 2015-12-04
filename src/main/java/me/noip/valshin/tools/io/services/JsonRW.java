package me.noip.valshin.tools.io.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.noip.valshin.config.Config;
import me.noip.valshin.db.entities.RamStorage;
import me.noip.valshin.tools.FileTools;
import me.noip.valshin.tools.io.DataRW;

@Service
public class JsonRW implements DataRW {
	protected Logger logger = Logger.getLogger(JsonRW.class.getName());
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired 
	Config config; 
	
	@Override
	public RamStorage readData() throws IOException {
		RamStorage data = null;
		FileInputStream fis = null;
		if (!FileTools.exists(config.getFileDbPath())){
			RamStorage storage = new RamStorage();
			storage.init();
			return storage;
		} else {
			try {
				fis = new FileInputStream(config.getFileDbPath());
		        try {
		        	data = mapper.readValue(fis, RamStorage.class);
		        } finally {
		        	fis.close();
		        }
		    } catch (IOException e) {
		    	throw e;
		    }
			return data;	
		}
		
	}

	@Override
	public void writeData(RamStorage storage) throws IOException {
		FileOutputStream fos = null;
		try {
			System.out.println(config.getFileDbPath());
			fos = new FileOutputStream(config.getFileDbPath());
	        try {
	        	mapper.writeValue(new FileOutputStream(config.getFileDbPath()), storage);
	        } finally {
	        	fos.close();
	        }
	    } catch (IOException e) {
	    	throw e;
	    }
	}
}

