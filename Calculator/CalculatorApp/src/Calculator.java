import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import JDBCConnection.databaseConnector;
import java.util.List;

public class Calculator implements ActionListener{
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[12]; 
    JButton addButton,subButton,mulButton,divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JButton sqrtButton, squareButton, historyButton;
    JPanel panel;
    
    Font myFont = new Font("Arial",Font.BOLD,30);
    double num1=0,num2=0,result=0;
    char operator;
    databaseConnector connector = new databaseConnector();
    
    Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 600);
        frame.setLayout(null);
        
        textfield = new JTextField();
        textfield.setBounds(50, 25, 400, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        
        // Initialize buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");
        sqrtButton = new JButton("√");
        squareButton = new JButton("x²");
        historyButton = new JButton("History");
        
        // Add buttons to array
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = sqrtButton;
        functionButtons[10] = squareButton;
        functionButtons[11] = historyButton;
        
        // Loop through functionButtons array to set properties
        for(int i =0;i<functionButtons.length;i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }
        
        // Loop through numberButtons array to set properties
        for(int i =0;i<10;i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }
        
        sqrtButton.setBounds(50, 430, 100, 50);
        squareButton.setBounds(150, 430, 100, 50);
        historyButton.setBounds(250, 430, 200, 50);
        clrButton.setBounds(50, 350, 150, 50);
        delButton.setBounds(300, 350, 150, 50);
        
        panel = new JPanel();
        panel.setBounds(50, 100, 400, 250); // Adjusted panel size
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // Adjusted grid layout
        
        // Add buttons to the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(sqrtButton);
        frame.add(squareButton);
        frame.add(historyButton);
        frame.add(negButton);
        frame.add(textfield);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<10;i++) {
            if(e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource()==decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if(e.getSource()==addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='+';
            textfield.setText("");
        }
        if(e.getSource()==subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='-';
            textfield.setText("");
        }
        if(e.getSource()==mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='*';
            textfield.setText("");
        }
        if(e.getSource()==divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='/';
            textfield.setText("");
        }
        if(e.getSource()==equButton) {
            num2=Double.parseDouble(textfield.getText());
            switch(operator) {
                case'+':
                    result=num1+num2;
                    connector.saveAdditionResult(num1, num2, result);
                    break;
                case'-':
                    result=num1-num2;
                    connector.saveSubtractionResult(num1, num2, result);
                    break;
                case'*':
                    result=num1*num2;
                    connector.saveMultiplicationResult(num1, num2, result);
                    break;
                case'/':
                    result=num1/num2;
                    connector.saveDivisionResult(num1, num2, result);
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1=result;
        }
        if(e.getSource()==clrButton) {
            textfield.setText("");
        }
        if(e.getSource()==delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for(int i=0;i<string.length()-1;i++) {
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }
        if(e.getSource()==negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp*=-1;
            textfield.setText(String.valueOf(temp));
        }
        if(e.getSource() == sqrtButton) {
            double num1 = Double.parseDouble(textfield.getText());
            double result = Math.sqrt(num1);
            connector.saveSquareRootResult(num1, result);
            textfield.setText(String.valueOf(result));
        } else if(e.getSource() == squareButton) {
            double num1 = Double.parseDouble(textfield.getText());
            double result = Math.pow(num1, 2);
            connector.saveSquareResult(num1, result);
            textfield.setText(String.valueOf(result));
        } else if(e.getSource() == historyButton) {
        	displayHistoryFrame();
        }
    }
    
    private void displayHistoryFrame() {
        JFrame historyFrame = new JFrame("History");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel historyPanel = new JPanel(new GridLayout(0, 1));

        // Add history data to the panel
        addHistoryToPanel(historyPanel, "Addition History", connector.getAdditionHistory());
        addHistoryToPanel(historyPanel, "Subtraction History", connector.getSubtractionHistory());
        addHistoryToPanel(historyPanel, "Multiplication History", connector.getMultiplicationHistory());
        addHistoryToPanel(historyPanel, "Division History", connector.getDivisionHistory());
        addHistoryToPanel(historyPanel, "Square Root History", connector.getSquareRootHistory());
        addHistoryToPanel(historyPanel, "Square History", connector.getSquareHistory());
        
        JButton deleteButton = new JButton("Delete History");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connector.clearAllTables();
                // Refresh the history frame after deletion
                historyFrame.dispose(); // Close the current frame
                displayHistoryFrame(); // Open a new frame with updated history
            }
        });
        historyPanel.add(deleteButton);

        // Add the history panel to the frame
        JScrollPane scrollPane = new JScrollPane(historyPanel);
        historyFrame.getContentPane().add(scrollPane);

        // Pack and set the frame visible
        historyFrame.pack();
        historyFrame.setLocationRelativeTo(null); // Center the frame on the screen
        historyFrame.setVisible(true);
    }

    // Function to add history data to the panel
    private void addHistoryToPanel(JPanel panel, String title, List<String> history) {
        if (!history.isEmpty()) {
            panel.add(new JLabel(title));
            for (String entry : history) {
                panel.add(new JLabel(entry));
            }
        }
    }
}