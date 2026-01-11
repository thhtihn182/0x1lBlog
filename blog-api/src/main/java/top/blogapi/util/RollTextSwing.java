package top.blogapi.util;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

public class RollTextSwing extends JPanel {

    // Config
    private static final String[] SENTENCES = {
            "Hello Java", "Swing Animation", "Text Roll Effect", "Like JavaScript"
    };
    private static final int DELAY_MS = 1000;
    private static final int MAX_RANDOM_CHARS = 5;
    private static final int PAUSE_FRAMES = 2;

    // State
    private String currentText = "";
    private int sentenceIndex = 0;
    private int charIndex = 0;
    private String direction = "forward";
    private int delayCounter = PAUSE_FRAMES;
    private int stepCounter = 1;
    private int frameCount = 0;

    // Colors gradient (RGB)
    private static final Color[] GRADIENT_COLORS = {
            new Color(110, 64, 170),   // Purple
            new Color(150, 61, 179),   // Light purple
            new Color(191, 60, 175),   // Pink-purple
            new Color(228, 65, 157),   // Pink
            new Color(254, 75, 131),   // Light red
            new Color(255, 94, 99),    // Coral
            new Color(255, 120, 71),   // Orange
            new Color(251, 150, 51),   // Yellow-orange
            new Color(226, 183, 47),   // Yellow
            new Color(198, 214, 60),   // Yellow-green
            new Color(175, 240, 91),   // Light green
            new Color(127, 246, 88),   // Green
            new Color(82, 246, 103),   // Mint
            new Color(48, 239, 130),   // Teal
            new Color(29, 223, 163),   // Blue-green
            new Color(26, 199, 194),   // Cyan
            new Color(35, 171, 216),   // Light blue
            new Color(54, 140, 225),   // Blue
            new Color(76, 110, 219),   // Dark blue
            new Color(96, 84, 200)     // Purple-blue
    };

    private Timer timer;
    private Random random = new Random();

    public RollTextSwing() {
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.BLACK);

        // Start animation timer
        timer = new Timer(DELAY_MS, e -> {
            frameCount++;
            animateFrame();
            repaint();
        });
        timer.start();
    }

    private void animateFrame() {
        String currentSentence = SENTENCES[sentenceIndex];

        // Step control - giống if(c.step) trong JS
        if (stepCounter > 0) {
            stepCounter--;
            return; // Skip frame
        }

        stepCounter = 1; // Reset step

        // Main logic - giống else block trong JS
        if (direction.equals("forward")) {
            if (charIndex < currentSentence.length()) {
                // Type character
                currentText += currentSentence.charAt(charIndex);
                charIndex++;
            } else {
                // Finished typing - pause
                if (delayCounter > 0) {
                    delayCounter--;
                } else {
                    direction = "backward";
                    delayCounter = PAUSE_FRAMES;
                }
            }
        } else { // backward
            if (charIndex > 0) {
                // Delete character
                currentText = currentText.substring(0, currentText.length() - 1);
                charIndex--;
            } else {
                // Move to next sentence
                sentenceIndex = (sentenceIndex + 1) % SENTENCES.length;
                direction = "forward";
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw main text
        g2d.setFont(new Font("Monospaced", Font.BOLD, 48));
        g2d.setColor(Color.WHITE);

        String currentSentence = SENTENCES[sentenceIndex];
        int x = 50;
        int y = 100;

        // Draw typed text
        g2d.drawString(currentText, x, y);

        // Draw random characters (gradient effect)
        int randomCount = Math.min(MAX_RANDOM_CHARS,
                currentSentence.length() - charIndex);

        // Measure text width for positioning
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(currentText);

        for (int i = 0; i < randomCount; i++) {
            // Random character
            char randomChar = getRandomAsciiChar();

            // Random gradient color
            Color randomColor = GRADIENT_COLORS[random.nextInt(GRADIENT_COLORS.length)];
            g2d.setColor(randomColor);

            // Draw character
            String charStr = String.valueOf(randomChar);
            g2d.drawString(charStr, x + textWidth + (i * 30), y);
        }

        // Draw info panel
        drawInfoPanel(g2d);
    }

    private void drawInfoPanel(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.setColor(Color.GRAY);

        String info = String.format(
                "Frame: %d | Sentence: %d/%d | Direction: %s | Position: %d/%d",
                frameCount,
                sentenceIndex + 1,
                SENTENCES.length,
                direction,
                charIndex,
                SENTENCES[sentenceIndex].length()
        );

        g2d.drawString(info, 10, getHeight() - 10);

        // Visualize state
        int boxY = 10;
        int boxWidth = 200;

        // Direction indicator
        g2d.setColor(direction.equals("forward") ? Color.GREEN : Color.RED);
        g2d.fillRect(10, boxY, boxWidth, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Direction: " + direction.toUpperCase(), 15, boxY + 15);

        // Delay indicator
        g2d.setColor(Color.BLUE);
        g2d.fillRect(10, boxY + 30, (boxWidth * delayCounter) / PAUSE_FRAMES, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Delay: " + delayCounter + "/" + PAUSE_FRAMES, 15, boxY + 45);
    }

    private char getRandomAsciiChar() {
        // ASCII 33-126 (printable characters)
        return (char) (random.nextInt(94) + 33);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java RollText Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new RollTextSwing());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
