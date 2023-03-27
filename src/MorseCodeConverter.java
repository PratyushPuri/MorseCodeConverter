
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MorseCodeConverter implements ActionListener {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel inputLabel;
    private final JTextField inputField;
    private final JButton toMorseButton;
    private final JButton toEnglishButton;
    private final JLabel outputLabel;
    private final JTextArea outputArea;

    
    
    private static final String[] CODES = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
        "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
        ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----"
    };

    private static final char[] CHARS = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z',
        '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };

    public static String getCode(char c) {
        for (int i = 0; i < CHARS.length; i++) {
            if (CHARS[i] == Character.toUpperCase(c)) {
                return CODES[i];
            }
        }
        return null;
    }

    public static char getChar(String code) {
        for (int i = 0; i < CODES.length; i++) {
            if (CODES[i].equals(code)) {
                return CHARS[i];
            }
        }
        return ' ';
    }


    public MorseCodeConverter() {
        frame = new JFrame("Morse Code Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputLabel = new JLabel("Enter your message:");
        inputField = new JTextField();

        toMorseButton = new JButton("Translate to Morse Code");
        toMorseButton.addActionListener(this);

        toEnglishButton = new JButton("Translate to English");
        toEnglishButton.addActionListener(this);

        outputLabel = new JLabel("Translated text:");
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(toMorseButton);
        panel.add(toEnglishButton);
        panel.add(outputLabel);
        panel.add(outputArea);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String inputText = inputField.getText().toUpperCase();

        if (command.equals("Translate to Morse Code")) {
            String morseCode = toMorseCode(inputText);
            outputArea.setText(morseCode);
        } else if (command.equals("Translate to English")) {
            String english = toEnglish(inputText);
            outputArea.setText(english);
        }
    }

    private static String toMorseCode(String message) {
        StringBuilder morseCode = new StringBuilder();
        for (char c : message.toCharArray()) {
            String code = MorseCodeConverter.getCode(c);
            if (code == null) {
                morseCode.append(" ");
            } else {
                morseCode.append(code).append(" ");
            }
        }
        return morseCode.toString();
    }

    private static String toEnglish(String morseCode) {
        StringBuilder english = new StringBuilder();
        String[] words = morseCode.split(" / ");
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                char c = MorseCodeConverter.getChar(letter);
                english.append(c);
            }
            english.append(" ");
        }
        return english.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MorseCodeConverter::new);
    }
}

