package me.noip.valshin.tools.generators;

import me.noip.valshin.db.entities.Note;

public class NoteArrayGenerator {
	public static Note[] getRandomNotes(int min, int max){
		return getRandomNotes(RandomValuesGenerator.getRandomInt(min, max));
	}
	
	public static Note[] getRandomNotes(int count){
		Note[] notes = new Note[count];
		for (int i = 0; i < count; i++){
			notes[i] = NoteGenerator.getRandomNote(false);
		}
		return notes;
	}
}
