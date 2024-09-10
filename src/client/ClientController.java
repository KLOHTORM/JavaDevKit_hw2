package client;

import server.ServerController;
public class ClientController {
    private boolean connected;
    private String name;
    private ClientView clientView;
    private ServerController server;

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }
    public void setServer(ServerController server) {
        this.server = server;
    }
    public boolean isConnected() {
        return connected;
    }

    public boolean connectToServer(ClientGUI clientGUI) {
        if (server.connectUser(clientGUI)) {
            showOnWindow("Вы были подключены!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                server.message(text);
            }
        } else {
            showOnWindow("Подключение не доступно");
        }
    }

    public void disconnectedFromServer(ClientView clientView) {
        connected = false;
        clientView.disconnectedFromServer();
        clientView.showMessage("Вы были отключены!");
    }

    private void showOnWindow(String text) {
        clientView.showMessage(text);
    }

    public void disconnectUserFromServer(ClientView clientView) {
        server.disconnectUser(clientView);
    }
}
