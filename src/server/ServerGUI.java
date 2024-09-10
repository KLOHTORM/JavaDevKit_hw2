package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements Repository {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    JButton btnStart, btnStop;
    JTextArea log;

    ServerController serverController;

    public ServerGUI() {
        setting();
        createPanel();
        setVisible(true);
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stop();
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    private void setting() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Server client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void showMessage(String message) {
        log.append((message + '\n'));

    }
}
