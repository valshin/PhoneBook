package me.noip.valshin.db.services;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.noip.valshin.MvcConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MvcConfigTest.class)
public class FileDBTest {
	
	@InjectMocks
    private String path = "/testFileDB.db";
	@InjectMocks
    private ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
	
	
}
