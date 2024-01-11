import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> fromScale;
    private JComboBox<String> toScale;
    private ImageIcon image=new ImageIcon("Temp.png");

    public TemperatureConverter(int i) {
        // Set up the frame
        setTitle("Temperature Converter");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setIconImage(image.getImage());
        setLocationRelativeTo(null);

        // Create components
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(123, 50, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(i, i, i, i);

        inputField = createStyledTextField(10);
        fromScale = createStyledComboBox(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        toScale = createStyledComboBox(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        JButton convertButton = createStyledButton("Convert");
        JButton resetButton = createStyledButton("Reset");

        // Set heading labels with custom font and color
        JLabel TempLabel = createStyledLabel("Enter Temperature:", 24, Font.BOLD);
        JLabel fromLabel = createStyledLabel("From:", 24, Font.BOLD);
        JLabel toLabel = createStyledLabel("To:", 24, Font.BOLD);
        resultLabel = createStyledLabel("RESULT: ", 24, Font.BOLD);

        // Add components to the panel
        addComponentToPanel(panel, TempLabel, gbc, 0, 0);
        addComponentToPanel(panel, inputField, gbc, 1, 0);
        addComponentToPanel(panel, fromLabel, gbc, 0, 1);
        addComponentToPanel(panel, fromScale, gbc, 1, 1);
        addComponentToPanel(panel, toLabel, gbc, 0, 2);
        addComponentToPanel(panel, toScale, gbc, 1, 2);
        gbc.gridwidth = 2;
        addComponentToPanel(panel, convertButton, gbc, 0, 3);
        addComponentToPanel(panel, resetButton, gbc, 0, 4);
        

        // Add action listeners
        convertButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String inputText=inputField.getText().trim();
                if(inputText.isEmpty())
                {
                    showErrorMessage("Please provide a valid input");
                }
                else
                {
                    try 
                    {
                        double temperature = Double.parseDouble(inputField.getText());
                        String from = fromScale.getSelectedItem().toString();
                        String to = toScale.getSelectedItem().toString();
                        double result = convertTemperature(temperature, from, to);
                        gbc.gridy=5;
                        addComponentToPanel(panel, resultLabel, gbc, 0, 5);
                        resultLabel.setText("RESULT: " + result);
                    } 
                    catch (IllegalArgumentException ex) 
                    {
                        showErrorMessage("Invalid input, Please enter a valid temperature.");
                    }
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                resultLabel.setText("RESULT: ");
                // Remove the resultLabel component from the panel
                panel.remove(resultLabel);
                // Repaint the panel to reflect the changes
                panel.revalidate();
                panel.repaint();
            }
        });

        // Add the panel to the frame
        add(panel);
    }

    private JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setBackground(new Color(255, 255, 255));
        textField.setForeground(new Color(0, 0, 0));
        textField.setFont(new Font("Cambria", Font.PLAIN, 24));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        //textField.setPreferredSize(new Dimension(150, 40));
        return textField;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBackground(new Color(255, 255, 255));
        comboBox.setForeground(new Color(0, 0, 0));
        comboBox.setFont(new Font("Cambria", Font.PLAIN, 24));
        comboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        return comboBox;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setFont(new Font("Cambria",Font.PLAIN,24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 174, 66));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private JLabel createStyledLabel(String text, int fontSize, int fontStyle) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Cambria", fontStyle, fontSize));
        return label;
    }

    private void addComponentToPanel(JPanel panel, JComponent component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private double convertTemperature(double temperature, String from, String to) {
        if (from.equals("Celsius") && to.equals("Fahrenheit")) 
        {
            return (temperature * 9 / 5) + 32;
        } 
        else if (from.equals("Celsius") && to.equals("Kelvin")) 
        {
            return temperature + 273.15;
        } 
        else if (from.equals("Fahrenheit") && to.equals("Celsius")) 
        {
            return (temperature - 32) * 5 / 9;
        } 
        else if (from.equals("Fahrenheit") && to.equals("Kelvin")) 
        {
            return (temperature - 32) * 5 / 9 + 273.15;
        }
        else if (from.equals("Kelvin") && to.equals("Celsius"))
        {
            return temperature - 273.15;
        }
        else if (from.equals("Kelvin") && to.equals("Fahrenheit")) 
        {
            return (temperature - 273.15) * 9 / 5 + 32;
        } 
        else 
        {
            return temperature;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverter(10));
    }
}
