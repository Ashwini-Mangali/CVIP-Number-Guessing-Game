import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {
    private int numberToGuess;
    private int attempts;

    private JTextField guessTextField;
    private JLabel feedbackLabel;
    private JButton guessButton;
    private JTextArea feedbackArea;
    private JButton newGameButton;

    public NumberGuessingGame() {

        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        guessTextField = new JTextField(10);
        feedbackLabel = new JLabel("Enter a guess and press Guess");
        guessButton = new JButton("Guess");
        feedbackArea = new JTextArea();
        newGameButton = new JButton("New Game");

        setLayout(new BorderLayout());

        // Panel for user input and feedback label
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(guessTextField);
        inputPanel.add(guessButton);
        inputPanel.add(newGameButton);

        JScrollPane feedbackScrollPane = new JScrollPane(feedbackArea);
        feedbackScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(inputPanel, BorderLayout.NORTH);
        add(feedbackLabel, BorderLayout.CENTER);
        add(feedbackScrollPane, BorderLayout.SOUTH);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        startNewGame();
    }

    private void startNewGame() {
        numberToGuess = generateRandomNumber();
        attempts = 0;
        feedbackArea.setText("");
        feedbackLabel.setText("Enter a guess and press Guess");
        guessTextField.setText("");
        guessButton.setEnabled(true);
    }

    private int generateRandomNumber() {

        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            attempts++;

            if (userGuess < numberToGuess) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                feedbackLabel.setText("Too high! Try again.");
            } else {

                feedbackLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                guessButton.setEnabled(false); // Disable the Guess button after correct guess
            }

            feedbackArea.append("Attempt " + attempts + ": Your guess - " + userGuess +
                    " | Feedback: " + feedbackLabel.getText() + "\n");

        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
