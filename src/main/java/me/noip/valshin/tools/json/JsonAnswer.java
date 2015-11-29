package me.noip.valshin.tools.json;

public class JsonAnswer {
	private final Object data;
	private final String error;
	
	public JsonAnswer(Object data, String error) {
		super();
		this.data = data;
		this.error = error;
	}
	public Object getData() {
		return data;
	}
	public String getError() {
		return error;
	}
	
}
