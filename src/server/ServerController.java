package server;

import client.ClientController;
import client.ClientGUI;
import client.ClientView;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    public static final String LOG_PATH = "src/log.txt";

    boolean work;
    Repository repository;
    List<ClientView> listOfClients = new ArrayList<>();

    ClientController clientController = new ClientController();

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public boolean connectUser(ClientGUI clientGUI) {
        if (work) {
            listOfClients.add(clientGUI);
            return true;
        } else {
            return false;
        }
    }

    public String getHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void message(String text) {
        if (!work) {
            return;
        }
        showOnWindow(text);
        answerAll(text);
        saveInLog(text);

    }

    private void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void answerAll(String text) {
        for (ClientView client : listOfClients) {
            client.showMessage(text);
        }
    }


    public void start() {
        if (work) {
            showOnWindow("Сервер доступен");
        } else {
            work = true;
            showOnWindow("Сервер запущен");
        }
    }

    private void showOnWindow(String text) {
        repository.showMessage(text);
    }

    public void stop() {
        if (!work) {
            showOnWindow("Cервер недоступен");
        } else {
            work = false;
            showOnWindow("Cервер остановлен");
            //disconnect user
            while (!listOfClients.isEmpty()) {
                disconnectUser(listOfClients.getLast());
            }
        }
    }

    public void disconnectUser(ClientView clientView) {
        if (clientView != null) {
            clientController.disconnectedFromServer(clientView);
        }
        listOfClients.remove(clientView);
    }
}
