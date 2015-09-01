package rs.fon.drawing.endpoints;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket")
public class Whiteboard {

	private static final Object PRESENT = new Object();

	private static final ConcurrentMap<Session, Object> peers = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session peer) {
		peers.put(peer, PRESENT);
	}

	@OnClose
	public void onClose(Session peer) {
		peers.remove(peer);
	}

	@OnMessage
	public void onMessage(String s, Session session) throws IOException {
		System.out.println(s);
		for (Session peer : session.getOpenSessions()) {
			if (!peer.equals(session)) {
				peer.getBasicRemote().sendText(s);
			}
		}
	}

	@OnMessage
	public void onMessageByte(ByteBuffer byteBuffer, Session session) throws IOException {
		for (Session peer : session.getOpenSessions()) {
			if (!peer.equals(session)) {
				peer.getBasicRemote().sendBinary(byteBuffer);
			}
		}
	}
}
