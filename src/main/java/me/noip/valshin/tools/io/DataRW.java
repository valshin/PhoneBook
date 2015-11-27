package me.noip.valshin.tools.io;

import java.io.IOException;

import me.noip.valshin.db.entities.RamStorage;

public interface DataRW {
	public RamStorage readData() throws IOException;
	public void writeData(RamStorage storage) throws IOException;
}

