package gui;

import java.util.ArrayList;
import java.util.Collections;
import controller.PhonebookController;
import controller.Sort;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CallLog;
import model.Person;

	public class ApplicationWindow extends Application {

		private TextField firstNameField;
		private TextField lastNameField;
		private TextField numberField;
		private TextField emailField;
		private ListView<Person> lview;
		private ListView<CallLog> cView;
		private ObservableList<Person> observableView;
		private ObservableList<CallLog> logObsView;
		private Button closeButton;
		private Button addButton;
		private Button callLogButton;
		private Button contactsButton;
		private Button callButton;
		private ArrayList<Person> allContacts;
	    private ArrayList<CallLog> callHistory;
	    private VBox lvb;
	    private ArrayList<Person> temp;
	    private ObservableList<Person> searchedList;
        
	    public void start(Stage stage) throws Exception 
		{
			lvb = new VBox();
        	this.lview = new ListView<Person>();
			this.cView = new ListView<CallLog>();
		    PhonebookController.getInstance().loadContacts();
		    PhonebookController.getInstance().loadHistory();
		    
		    allContacts = PhonebookController.getInstance().getAllContacts();
			observableView = FXCollections.observableList(allContacts);
			callHistory = PhonebookController.getInstance().getAllCallLogs();
			logObsView = FXCollections.observableList(callHistory);
			
			lview.setItems(observableView);
			lview.setPrefSize(260, 377);
			String style = "-fx-border-color : black;"
					+ "-fx-border-width : 2;"
					+ "-fx-border-style : solid;"
					+ "-fx-background-color : #d6dbdf";
			lview.setStyle(style);
			cView.setItems(logObsView);
			cView.setPrefSize(260, 377);
			cView.setStyle(style);
        	
        	stage.setTitle("Phonebook Application");
			this.closeButton = new Button("Close");
			closeButton.setPrefSize(120, 25);
			BorderPane bp = new BorderPane();
			bp.setTop(topPane());
			bp.setCenter(centerPane());
		    bp.setRight(contactsFuntionsPane());
			
			Scene firstScene = new Scene(bp, 440 , 310);
			stage.setScene(firstScene);
			stage.show();
	        
			callLogButton.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e)
			    {
					logObsView =  FXCollections.observableList(callHistory);
					lvb.getChildren().remove(0);
	        		lvb.getChildren().add(cView);
	        		cView.refresh();
					bp.setRight(callHistoryFunctions());
				}
			});
			
			contactsButton.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e)
				{
	        		observableView = FXCollections.observableList(allContacts);
	        		lview.setItems(observableView);
					lvb.getChildren().remove(0);
	        		lvb.getChildren().add(lview);
	        		bp.setRight(contactsFuntionsPane());
	        		lview.refresh();
			    }		
			});
			
			
			closeButton.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e)
			    {
					PhonebookController.getInstance().saveContacts();
					PhonebookController.getInstance().saveCallHistory();
					stage.close(); 	
				}
			});
	    }
	    
		private Node topPane()
		{
			HBox viewBox = new HBox();
		    Text title = new Text("Phone Book");
			title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
			viewBox.setStyle("-fx-background-color : #d6dbdf");
			viewBox.getChildren().add(title);
			viewBox.setAlignment(Pos.CENTER);
			
			return viewBox;
		}
	    
		private Node centerPane() throws ClassNotFoundException
		{
			this.contactsButton = new Button("Contacts");
			contactsButton.setPrefSize(125, 25);
			this.callLogButton = new Button("Call History");
			callLogButton.setPrefSize(125, 25);
            HBox hb = new HBox(5);
            hb.getChildren().add(contactsButton);
            hb.getChildren().add(callLogButton);
            hb.setAlignment(Pos.CENTER);
            
			GridPane pane = new GridPane();
			pane.setAlignment(Pos.CENTER);
			pane.setPadding(new Insets(20,20,20,20));
			pane.setStyle("-fx-background-color :  #d6dbdf ");
			pane.add(hb, 0,0);
			
			lvb.getChildren().add(lview);
			pane.add(lvb,0,1);
			
			lview.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e)
				{
					Person p1 = lview.getSelectionModel().getSelectedItem();
				    leftPane(p1);   
				}
			});
			
			cView.setOnMouseClicked(new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e)
				{
					CallLog c1 = cView.getSelectionModel().getSelectedItem();
				
					VBox vb = new VBox(5);
					HBox hb = new HBox();
					HBox hb2 = new HBox();
					vb.setPadding(new Insets(10,10,10,10));
					Scene showEntry = new Scene(vb, 250,200);
					Stage callEntry = new Stage();
					callEntry.setScene(showEntry);
					
					Button deleteButton = new Button("Delete Entry");
					Label title = new Label("Call Details");
					hb.setAlignment(Pos.CENTER);
					hb2.setAlignment(Pos.CENTER);
					hb2.setPadding(new Insets(10,10,10,10));
					hb.getChildren().add(title);
					Label name = new Label("Name : " + c1.getPerson().getFirstName() + " " + c1.getPerson().getLastName());
					Label number = new Label("Number : 0" + c1.getPerson().getPhoneNumber());
					Label date = new Label("Called at : " + c1.getDate());
					Label callDuration = new Label(c1.getCallDuration());
					hb2.getChildren().add(deleteButton);
					
					vb.getChildren().add(hb);
					vb.getChildren().add(name);
					vb.getChildren().add(number);
					vb.getChildren().add(date);
					vb.getChildren().add(callDuration);
					vb.getChildren().add(hb2);
					
					callEntry.show();
					
					deleteButton.setOnAction(new EventHandler<ActionEvent>(){
						public void handle(ActionEvent e)
						{
							PhonebookController.getInstance().removeCallLogEntry(c1);
							PhonebookController.getInstance().saveCallHistory();
							callEntry.close();
							cView.refresh();
						}
					});
				}
			});
			
			return pane;
		}
		
		public Node callHistoryFunctions()
		{
			VBox vb = new VBox(10);
			vb.setStyle("-fx-background-color :  #d6dbdf");
			vb.setPadding(new Insets(10,20,20,10));
			vb.setAlignment(Pos.BOTTOM_CENTER);		
			
			ComboBox<String> sortBy = new ComboBox<String>();
			sortBy.setPrefSize(120,25);
			sortBy.setPromptText("Sort By");
			sortBy.getItems().addAll("Newest", "Oldest");
			vb.getChildren().add(sortBy);
			vb.getChildren().add(closeButton);
			
			sortBy.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e)
				{
					String temp = sortBy.getValue();
					if(temp.equals("Newest"))
					{
					    Collections.sort(callHistory,Sort.compareByNewest);
					}
					else if(temp.equals("Oldest"))
					{
						Collections.sort(callHistory,Sort.compareByOldest);
					}
					cView.refresh();
					logObsView = FXCollections.observableList(callHistory);
				}
			});
			return vb;
		}
		
		public Node contactsFuntionsPane()
		{
			VBox hb = new VBox(10);
			hb.setStyle("-fx-background-color :  #d6dbdf");
			hb.setPadding(new Insets(10,20,20,10));
			hb.setAlignment(Pos.BOTTOM_CENTER);
			this.addButton = new Button("Add");
			addButton.setPrefSize(120, 25);
			
			ComboBox<String> sortBy = new ComboBox<String>();
			sortBy.setPrefSize(120,25);
			sortBy.setPromptText("Sort By");
			sortBy.getItems().addAll("First name", "Last name");
			
			Label searchLabel = new Label("Search");
			TextField searchField = new TextField();
			searchField.setPrefSize(75, 25);
			
			hb.getChildren().add(searchLabel);
			hb.getChildren().add(searchField);
		    hb.getChildren().add(sortBy);
		    hb.getChildren().add(addButton);			
		    hb.getChildren().add(closeButton);
		    
		    searchField.setOnKeyPressed(new EventHandler<KeyEvent>(){
				public void handle(KeyEvent e)
		    	{
		    		temp = (ArrayList<Person>)allContacts.clone();
		    		String term = searchField.getText();
		    	    for(Person p : new ArrayList<Person>(temp))
		    	    {
		    	    	if(!p.getFullName().toLowerCase().startsWith(term.toLowerCase()))
		    	    	{
		    	    		temp.remove(p);
		    	    	}
		    	    }
		    	    searchedList = FXCollections.observableList(temp);
		    	    lview.setItems(searchedList);
		    	    lview.refresh();
		    	}
		    });
		    
			addButton.setOnAction(new EventHandler<ActionEvent>(){
		        public void handle(ActionEvent e) 
		        {
			        leftPane(null);
		        }
			});
			
			sortBy.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e)
				{
					String temp = sortBy.getValue();
					if(temp.equals("First name"))
					{
					    Collections.sort(allContacts,Sort.compareFirstName);
					}
					else if(temp.equals("Last name"))
					{
						Collections.sort(allContacts,Sort.compareLastName);
					}
					lview.refresh();
					observableView = FXCollections.observableList(allContacts);
				}
			});
			
			
			return hb;
		}
		
		public Node leftPane(Person p)
		{
		    Label fNameLabel = new Label("First Name : ");
		    Label lNameLabel = new Label("Last Name : ");
		    Label numberLabel = new Label("Phone Number : ");
		    Label emailLabel = new Label("Email address (optional) : ");			
		    firstNameField = new TextField();
		    lastNameField = new TextField();
		    numberField = new TextField();
		    emailField = new TextField();
		    
		    VBox hbox = new VBox(5);
			Stage stage2 = new Stage();
			Scene addScene = new Scene(hbox, 220, 350);
			stage2.setScene(addScene);
			stage2.show();
						
			hbox.setPadding(new Insets(20,20,20,20));
			hbox.getChildren().add(fNameLabel);
			hbox.getChildren().add(firstNameField);
		    hbox.getChildren().add(lNameLabel);
			hbox.getChildren().add(lastNameField);
			hbox.getChildren().add(numberLabel);
			hbox.getChildren().add(numberField);
			hbox.getChildren().add(emailLabel);
			hbox.getChildren().add(emailField);
						
			HBox hb = new HBox(5);
						
		    Button saveButton = new Button("Save");
		    Button cancelButton = new Button("Cancel");
			Button deleteButton = new Button("Delete");
			this.callButton = new Button("Call");
			callButton.setPrefSize(200, 25);
		
			saveButton.setPrefSize(100, 25);
			cancelButton.setPrefSize(100, 25);
			deleteButton.setPrefSize(100, 25);
			hb.getChildren().add(saveButton);
			hbox.getChildren().add(hb);
						
			if(p != null)
			{
		        firstNameField.setText(p.getFirstName());
				lastNameField.setText(p.getLastName());
				numberField.setText("0"+p.getPhoneNumber());
				if(p.getEmailAddress() != null)
				{
					emailField.setText(p.getEmailAddress());
				}
				hb.getChildren().add(deleteButton);
				hbox.getChildren().add(callButton);
		    }
			else{
		        hb.getChildren().add(cancelButton);
			}
		    
			callButton.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e)
				{
					CallLog entry = new CallLog(p);
					PhonebookController.getInstance().addCallLog(entry);
					
					VBox vb = new VBox(5);
					Button endCall = new Button("End Call");
					endCall.setPrefSize(100, 25);
					Label dialog = new Label("Calling ....");
					Label contactName = new Label(p.getFirstName() + " " + p.getLastName());
					contactName.setAlignment(Pos.CENTER);
					dialog.setAlignment(Pos.CENTER);
					vb.setAlignment(Pos.CENTER);
					
					dialog.setPrefSize(200, 25);
					vb.getChildren().add(dialog);
					vb.getChildren().add(contactName);
					vb.getChildren().add(endCall);
					
					Stage calling = new Stage();
					Scene callScene = new Scene(vb, 200, 120);
					calling.setScene(callScene);
					calling.show();
					stage2.close();
					
					endCall.setOnAction(new EventHandler<ActionEvent>(){
						public void handle(ActionEvent e)
						{
							calling.close();
							PhonebookController.getInstance().saveCallHistory();
							entry.endCallTimer();
							callHistory = PhonebookController.getInstance().getAllCallLogs();
							logObsView = FXCollections.observableList(callHistory);
							cView.setItems(logObsView);
							cView.refresh();
						}
					});
				}
			});
			
			saveButton.setOnAction(new EventHandler<ActionEvent>(){
			    public void handle(ActionEvent e)
				{
			        String firstNameEntered = null;
					String lastNameEntered = null;
					String numberEntered = null;
					Person p1 = null;
								
				    if(!firstNameField.getText().trim().isEmpty() && !lastNameField.getText().trim().isEmpty()
				    		&&!numberField.getText().trim().isEmpty() && numberField.getText().matches("[0-9]+")
						    && numberField.getText().length() <= 10)
					{
					    firstNameEntered = firstNameField.getText();
						lastNameEntered = lastNameField.getText();
					    numberEntered = numberField.getText();
									
						p1 = new Person(firstNameEntered, lastNameEntered, Integer.parseInt(numberEntered));
					    PhonebookController.getInstance().removeContact(p);
                        if(p !=  null)
                        {
                        	 PhonebookController.getInstance().updateEntry(p, p1);
                        	 observableView.remove(p);
                        }
                        
                        observableView.remove(p);
						observableView.add(p1);									
			            PhonebookController.getInstance().saveContacts();
						allContacts = PhonebookController.getInstance().getAllContacts();
						callHistory = PhonebookController.getInstance().getAllCallLogs();
						PhonebookController.getInstance().saveCallHistory();
			            stage2.close();
						lview.refresh();
				    }
				    else if(firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty()){
					    Alert alert = new Alert(AlertType.WARNING);
					    alert.setContentText("Must enter full name");
						alert.showAndWait();
								    
					}
				    
				    else if(numberField.getText().trim().isEmpty() || !numberField.getText().matches("[0-9]+")
						    || numberField.getText().length() > 10)
			        {
				    	Alert alert = new Alert(AlertType.WARNING);
					    alert.setContentText("Invalid mobile number");
						alert.showAndWait();
			        }
				
				    //checks that field is not empty
				    if(!emailField.getText().trim().isEmpty() && p1 != null)
				    {
				    	p1.setEmailAddress(emailField.getText());
				    }
			        PhonebookController.getInstance().saveContacts();
			        PhonebookController.getInstance().saveCallHistory();
				} 
            });
			cancelButton.setOnAction(new EventHandler<ActionEvent>(){
			    public void handle(ActionEvent e)
			    {
			        stage2.close();
			    }
			});
		    deleteButton.setOnAction(new EventHandler<ActionEvent>(){
		        public void handle(ActionEvent e)
			    {
					PhonebookController.getInstance().removeContact(p);
					PhonebookController.getInstance().saveContacts();
					observableView.remove(p);
					stage2.close();
					lview.refresh();
			    }
		    });
		    return hbox;    
		}
		
		
		public static void main(String[] args)
		{
			ApplicationWindow appWindow = new ApplicationWindow();
			Application.launch(args);
		}
   }