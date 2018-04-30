/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachat;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    
    private ArrayList<Message> messages = new ArrayList();
    private final MessageLoader loader = new MessageLoader(); // It is the MessageLoader class
    private final ObservableList<Message> olistMessages = FXCollections.observableArrayList();
    private Contact selectedContact;
    @FXML
    private ListView<Contact> LVIEWCONTACTS;
    @FXML
    private Label LABELNAME;
    @FXML
    private ListView<Message> LVIEWCHAT;
    @FXML
    private TextField tfieldMessage;
    @FXML
    private Label label;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<Contact> list = new ArrayList();
        list.add(new Contact("José","5555"));
        list.add(new Contact("Anastasiia","1119"));
        list.add(new Contact("Maliha","2129"));
        list.add(new Contact("Roman","1234"));
        ObservableList<Contact> olist = FXCollections.observableArrayList(list); // you create an observable list, and then you set this list to the FXdata member
        
              
        
        //list.add(new Contact("TEST1","4444"));
        
       
        LVIEWCONTACTS.setItems(olist);
        
        
        
        //list.add(new Contact("TEST2","4444"));
        olist.setAll(list); // You will update the list with this, for whenever you add new contacts. 
        
        LABELNAME.setText("Test");
    }    

    @FXML
    private void onContactClicked(MouseEvent event) throws ParseException {
        // add here an if statement when we click on the same user.
        if(LVIEWCONTACTS.getSelectionModel().getSelectedItem() == null || selectedContact == LVIEWCONTACTS.getSelectionModel().getSelectedItem()) // When user clicks on empty space and clicks on the user again
            return;
       selectedContact = LVIEWCONTACTS.getSelectionModel().getSelectedItem();
       LABELNAME.setText(selectedContact.name);
       updateMessages(true);
       /*
       // get messages
       messages = loader.loadMessages("You","5555",selectedContact.number); // load messages sent by me
       
       // this add any type of data
       messages.addAll(loader.loadMessages(selectedContact.name,selectedContact.number,"5555")); // load messages sent to me. 
       
       
       
       messages.sort((Message o1, Message o2) -> o1.timestamp.after(o2.timestamp) ? 1 :-1); // lambda expression
       
       
       olistMessages.setAll(messages);
       LVIEWCHAT.setItems(olistMessages);
       
       // Get messages    
       */
    }
    
    private void sortArray(){
        //messages.sort();
    }

    @FXML
    private void onSendMessageClicked(ActionEvent event) {
        String message = tfieldMessage.getText();
        if(message == null || "".equals(message)) // trick when message might be null, so it doesn't fuck up
            return;
        boolean Updater = loader.sendMessage("5555", selectedContact.number, message);
        
        if(Updater == true){
            updateMessages(Updater);    
            tfieldMessage.setText("");  
        }
        
        
        /*
        messages = loader.loadMessages("You","5555",selectedContact.number);
        messages.addAll(loader.loadMessages(selectedContact.name,selectedContact.number,"5555"));
        messages.sort((Message o1, Message o2) -> o1.timestamp.after(o2.timestamp) ? 1 :-1);
        olistMessages.setAll(messages);
        LVIEWCHAT.setItems(olistMessages);
        */
    }
    
    public void updateMessages(boolean g){
            messages = loader.loadMessages("You","5555",selectedContact.number);
            messages.addAll(loader.loadMessages(selectedContact.name,selectedContact.number,"5555"));
            messages.sort((Message o1, Message o2) -> o1.timestamp.after(o2.timestamp) ? 1 :-1);
            olistMessages.setAll(messages);
            LVIEWCHAT.setItems(olistMessages);
        return;
    }
    
}
