package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;


import design.CustomColor;


public class ClientRMIGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel textPanel, inputPanel;
    private JTextField textField;
    private String name, message;
    private Font helveticaNeue = new Font("Helvetica Neue", Font.PLAIN, 14);
    private Border blankBorder = BorderFactory.createEmptyBorder(10, 10, 20, 10); 
    private ChatClient3 chatClient;
    private JList<String> list;
    private DefaultListModel<String> listModel;


    Color lightBlue = CustomColor.createColor("E1", "F1", "FF");
    Color skyBlue = CustomColor.createColor("37", "98", "FF");
    Color deepBlue = CustomColor.createColor("46", "64", "94");

   
    protected JTextArea textArea, userArea;
    protected JFrame frame;
    protected JButton privateMsgButton, startButton, sendButton;
    protected JPanel clientPanel, userPanel;

    public static void main(String args[]) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
        new ClientRMIGUI();
    }

    /**
     * Konstruktor GUI
     */
    public ClientRMIGUI() {

        frame = new JFrame("Chat");

        ImageIcon chatIcon = new ImageIcon("Java-RMI-Chat\\client\\chatIcon.png");
        frame.setIconImage(chatIcon.getImage());

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                if (chatClient != null) {
                    try {
                        sendMessage("Użytkownik " + (name) +" opuścił chat");
                        chatClient.serverIF.leaveChat(name);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });

        Container c = getContentPane();
        JPanel outerPanel = new JPanel(new BorderLayout());

        outerPanel.add(getInputPanel(), BorderLayout.CENTER);
        outerPanel.add(getTextPanel(), BorderLayout.NORTH);
        
        c.setLayout(new BorderLayout());
        c.add(outerPanel, BorderLayout.CENTER);
        c.add(getUsersPanel(), BorderLayout.EAST);

        frame.add(c);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setLocation(150, 150);
        textField.requestFocus();
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(true);
        
        textPanel.setBackground(skyBlue);
        inputPanel.setBackground(skyBlue);
    }

    /**
     * Metoda do ustawienia JPanelu do wyświetlania tekstu czatu
     *
     * @return
     */
    public JPanel getTextPanel() {
        String welcome = "Witaj! Podaj swoj nick i naciśnij rozpocznij\n";
        textArea = new JTextArea(welcome, 24, 24);
        //textArea.setPreferredSize(new Dimension(350, 400)); // Dostosuj szerokość i wysokość

        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(helveticaNeue);
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textPanel = new JPanel();
        textPanel.add(scrollPane);


        textPanel.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        return textPanel;
    }

    /**
     * Metoda do zbudowania panelu z polem wejściowym
     *
     * @return inputPanel
     */
    public JPanel getInputPanel() {
        inputPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        inputPanel.setBorder(blankBorder);

        textField = new JTextField();
        textField.setFont(helveticaNeue);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);

        inputPanel.add(textField);
        
        return inputPanel;        
    
    }

    /**
     * Metoda do zbudowania panelu wyświetlającego obecnie podłączonych
     * użytkowników oraz wywołanie metody budowania panelu przycisków
     *
     * @return
     */
    public JPanel getUsersPanel() {

        userPanel = new JPanel(new BorderLayout());
        userPanel.setPreferredSize(new Dimension(320, 400)); // Ustaw odpowiednią szerokość i wysokość

        
        userPanel.setBackground(skyBlue); 
    
        String userStr = "Aktywni użytkownicy";
    
        JLabel userLabel = new JLabel(userStr, JLabel.CENTER);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
        String[] noClientsYet = { "Brak " };
        setClientPanel(noClientsYet);
    
            
        clientPanel.setFont(helveticaNeue);
        userPanel.add(userLabel, BorderLayout.NORTH);
        userPanel.add(makeButtonPanel(), BorderLayout.SOUTH);
        userPanel.setBorder(blankBorder);

    
        return userPanel;
    }

    /**
     * Metoda do uzupełnienia aktualnego panelu użytkownika z listą aktualnie
     * podłączonych użytkowników
     *
     * @param currClients
     */
    public void setClientPanel(String[] currClients) {
        clientPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<String>();


        for (String s : currClients) {
            listModel.addElement(s);
        }
        if (currClients.length > 1) {
            privateMsgButton.setEnabled(true);
        }

        // Utwórz listę i umieść ją w panelu przewijania.
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(8);
        list.setFont(helveticaNeue);
        JScrollPane listScrollPane = new JScrollPane(list);

        clientPanel.add(listScrollPane, BorderLayout.CENTER);
        userPanel.add(clientPanel, BorderLayout.CENTER);
   
    }

    /**
     * Utwórz przyciski i dodaj nasłuchiwacz
     *
     * @return
     */
    public JPanel makeButtonPanel() {

        
        startButton = new JButton("Rozpocznij ");
        startButton.setBackground(skyBlue);
        startButton.addActionListener(this);
        startButton.setForeground(Color.WHITE);  // Ustawienie koloru tekstu na biały
        startButton.setFont(new Font("Arial", Font.BOLD, 12));  // Ustawienie tekstu jako pogrubionego
             
        privateMsgButton = new JButton("Wiadomość prywatna");
        privateMsgButton.setBackground(skyBlue);
        privateMsgButton.addActionListener(this);
        privateMsgButton.setEnabled(false);
        privateMsgButton.setForeground(Color.WHITE);  // Ustawienie koloru tekstu na biały
        privateMsgButton.setFont(new Font("Arial", Font.BOLD, 12));  // Ustawienie tekstu jako pogrubionego
        

        sendButton = new JButton("Wyślij ");
        sendButton.setBackground(skyBlue);
        sendButton.addActionListener(this);
        sendButton.setEnabled(false);
        sendButton.setForeground(Color.WHITE);  // Ustawienie koloru tekstu na biały
        sendButton.setFont(new Font("Arial", Font.BOLD, 12));  // Ustawienie tekstu jako pogrubionego
   
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
  
        buttonPanel.setBackground(lightBlue);
        buttonPanel.add(privateMsgButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(sendButton);
        buttonPanel.add(startButton);

      

        return buttonPanel;
    }

    /**
     * Obsługa zdarzeń na przyciskach
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            // Połącz się z usługą czatu
            if (e.getSource() == startButton) {
                name = textField.getText();
                if (name.length() != 0) {
                    frame.setTitle(name + "'s chat ");
                    textField.setText("");
                    textArea.append("Nick : " + name + " dołączył do chatu...\n");
                    getConnected(name);
                    if (!chatClient.connectionProblem) {
                        startButton.setEnabled(false);
                        sendButton.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Podaj swoj nick");
                }
            }

            // Pobierz tekst i wyczyść pole textField
            if (e.getSource() == sendButton) {
                message = textField.getText();
                textField.setText("");
                sendMessage(message);
                System.out.println("Wysyłanie wiadomości : " + message);
            }

            // Wyślij prywatną wiadomość do wybranych użytkowników
            if (e.getSource() == privateMsgButton) {
                int[] privateList = list.getSelectedIndices();

                for (int i = 0; i < privateList.length; i++) {
                    System.out.println("wybrany index :" + privateList[i]);
                }
                message = textField.getText();
                textField.setText("");
                sendPrivate(privateList);
            }

        } catch (RemoteException remoteExc) {
            remoteExc.printStackTrace();
        }

    }


    /**
     * Wyślij wiadomość, aby została przekazana do wszystkich rozmówców
     *
     * @param chatMessage
     * @throws RemoteException
     */
    private void sendMessage(String chatMessage) throws RemoteException {
        chatClient.serverIF.updateChat(name, chatMessage);
    }

    /**
     * Wyślij wiadomość, aby została przekazana tylko do wybranych rozmówców
     *
     * @param privateList
     * @throws RemoteException
     */
    private void sendPrivate(int[] privateList) throws RemoteException {
        String privateMessage = "[PW od " + name + "] :" + message + "\n";
        chatClient.serverIF.sendPM(privateList, privateMessage);
    }

    /**
     * Nawiąż połączenie z serwerem czatu
     *
     * @param userName
     * @throws RemoteException
     */
    private void getConnected(String userName) throws RemoteException {
        // Usuń białe znaki i niealfanumeryczne, aby uniknąć nieprawidłowego URL
        String cleanedUserName = userName.replaceAll("\\s+", "_");
        cleanedUserName = userName.replaceAll("\\W+", "_");
        try {
            chatClient = new ChatClient3(this, cleanedUserName);
            chatClient.startClient();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
