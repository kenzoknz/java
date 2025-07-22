package BaiThucHanhSo2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai7 extends Frame implements ItemListener {
    Panel panel = new Panel(new FlowLayout());
    Checkbox male = new Checkbox("Male"), female = new Checkbox("Female");
    Choice choice = new Choice();
    List list = new List();
    Label label = new Label("The event is displayed here");

    public void start() {
        choice.add("MS-DOS");
        choice.add("Windows");
        choice.add("macOS");
        choice.add("Ubuntu");
        choice.add("Nothing");
        
        list.add("Tiger");
        list.add("Lion");
        list.add("Elephant");
        list.add("Cat");
        list.add("Dog");
        list.add("Giraffe");
        list.add("Monkey");
        
        // Đăng ký ItemListener cho các component
        male.addItemListener(this);
        female.addItemListener(this);
        choice.addItemListener(this);
        list.addItemListener(this);
        
        panel.add(male);  
        panel.add(female);  
        panel.add(choice);  
        panel.add(list); 
        panel.add(label);
        add(panel);
    }

    public Bai7() {
        super("ItemEventTest");
        setBounds(100, 100, 400, 300);
        start();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Bai7();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        String message = "";
        
        if (source == male || source == female) {
           
            if (e.getStateChange() == ItemEvent.SELECTED) {
                message =  " checkbox: SELECTED";
            } else {
                message =  " checkbox: DESELECTED";
            }
        } 
        else if (source == choice) {
       
            String selected = choice.getSelectedItem();
            message = "Selected OS: " + selected;
        }
        else if (source == list) {
       
            String selected = list.getSelectedItem();
            message = "Selected animal: " + selected;
        }
        
        label.setText(message);
    }
}