package me.noip.valshin.tools.generators;

import me.noip.valshin.db.entities.Note;

public class NoteGenerator {
	public static Note getRandomNote(boolean isFullyFilled){
		Note note = new Note();
		note.setName(RandomValuesGenerator.getRandomString(4, 10));
		note.setSecondName(RandomValuesGenerator.getRandomString(4, 10));
		note.setLastName(RandomValuesGenerator.getRandomString(4, 10));
		note.setPhone(RandomValuesGenerator.getRandomPhone());
		if (isFullyFilled || RandomValuesGenerator.getRandomBoolean()){
			note.setHomePhone(RandomValuesGenerator.getRandomPhone());
		}
		if (isFullyFilled || RandomValuesGenerator.getRandomBoolean()){
			note.setAddress(RandomValuesGenerator.getRandomString(0, 20));
		}
		if (isFullyFilled || RandomValuesGenerator.getRandomBoolean()){
			note.setEmail(RandomValuesGenerator.getRandomEmail());
		}
		return note;
	}
}
