package rs.fon.drawing.coders;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import rs.fon.drawing.domain.Figure;

public class FigureEncoder implements Encoder.Text<Figure>{
	
	private static final Logger LOGGER = Logger.getLogger(FigureDecoder.class.getName());

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(EndpointConfig ec) {
		// TODO Auto-generated method stub
		
	}

	public String encode(Figure figure) throws EncodeException {
		LOGGER.log(Level.INFO, "encoding: {0}", figure);
		return figure.getJson().toString();
	}

}
