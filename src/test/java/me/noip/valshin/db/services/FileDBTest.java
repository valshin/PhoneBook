package me.noip.valshin.db.services;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.noip.valshin.MvcConfigTest;
import me.noip.valshin.db.entities.RamStorage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MvcConfigTest.class)
public class FileDBTest {
	@InjectMocks
    private String path = "src/test/resources/testFileDB.db";
	@InjectMocks
    private ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
	
	@Test
	public void testFilePath(){
		File file = new File(path);
		assertTrue(file.exists());
	}
	
	
	
	@Test
	public void testRW(){
		RamStorage rs = Mockito.mock(RamStorage.class);
		try {
			Mockito.when(this.mapper.readValue(path, RamStorage.class)).thenReturn(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileDB db = new FileDB();
		
	}
	
}
