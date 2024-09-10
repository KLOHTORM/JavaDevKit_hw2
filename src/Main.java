import client.ClientController;
import client.ClientGUI;
import server.ServerController;
import server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        ServerController serverController = new ServerController();
        serverController.setRepository(serverGUI);
        serverGUI.setServerController(serverController);

        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView((clientGUI1));
        clientGUI1.setClient(clientController1);
        clientController1.setServer(serverController);

        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView((clientGUI2));
        clientGUI2.setClient(clientController2);
        clientController2.setServer(serverController);
    }
}