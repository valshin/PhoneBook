package me.noip.valshin.tools.io;

import java.io.IOException;

import me.noip.valshin.db.entities.MapStorage;

public interface DataRW {
	public MapStorage readData() throws IOException;
	public void writeData(MapStorage storage) throws IOException;
}

