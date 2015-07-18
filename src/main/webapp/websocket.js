var wsUri = "ws://" + document.location.hostname + ":" + document.location.port
		+ document.location.pathname + "websocket";
var websocket = new WebSocket(wsUri);
var output = document.getElementById("output");
websocket.onmessage = function(evt) {
	onMessage(evt)
};
websocket.onerror = function(evt) {
	onError(evt)
};

function sendText(json) {
	console.log("sending text: " + json);
	websocket.send(json);
}


function onMessage(evt) {
	console.log("received: " + evt.data);
	if (typeof evt.data == "string") {
		drawImageText(evt.data);
	}
}

function onError(evt) {
	writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {
	var pre = document.createElement("p");
	pre.style.wordWrap = "break-word";
	pre.innerHTML = message;
	output.appendChild(pre);
}