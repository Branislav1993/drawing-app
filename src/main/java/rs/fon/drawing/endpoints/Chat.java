package rs.fon.drawing.endpoints;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat")
public class Chat {

	private static final Object CHAT_PRESENT = new Object();

	private static final ConcurrentMap<Session, Object> chatPeers = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session peer) {
		chatPeers.put(peer, CHAT_PRESENT);
	}

	@OnClose
	public void onClose(Session peer) {
		chatPeers.remove(peer);
	}

	@OnMessage
	public void onMessage(String s, Session session) throws IOException {
		System.out.println(s);
		for (Session peer : session.getOpenSessions()) {
			peer.getBasicRemote().sendText(s);
		}
	}
}
