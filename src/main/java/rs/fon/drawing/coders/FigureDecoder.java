package rs.fon.drawing.coders;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import rs.fon.drawing.domain.Figure;

public class FigureDecoder implements Decoder.Text<Figure>{
	
	 private static final Logger LOGGER = Logger.getLogger(FigureDecoder.class.getName());

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(EndpointConfig ec) {
		// TODO Auto-generated method stub
		
	}

	public Figure decode(String string) throws DecodeException {
		LOGGER.log(Level.INFO, "decoding: {0}", string);
		JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();
		
		return new Figure(jsonObject);
	}

	public boolean willDecode(String string) {
		Json.createReader(new StringReader(string)).readObject();
		return true;
		
	}

}
