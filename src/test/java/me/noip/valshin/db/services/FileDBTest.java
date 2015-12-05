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
import me.noip.valshin.db.entities.MapStorage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MvcConfigTest.class)
public class FileDBTest {
	
	@Test
	public void testMock(){
		assertTrue(true);
//		RamStorage rs = Mockito.mock(RamStorage.class);
//		try {
//			Mockito.when(this.mapper.readValue(path, RamStorage.class)).thenReturn(rs);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		FileDB db = new FileDB();
		
	}
	
}
