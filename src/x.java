import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class x extends JPanel {

    private int[] expenses;
    private int numMonths;

    public x(int[] expenses, int numMonths) {
        this.expenses = expenses;
        this.numMonths = numMonths;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        // Draw x-axis
        g.drawLine(50, 250, 50 + (numMonths * 100), 250);
        for (int i = 1; i <= numMonths; i++) {
            g.drawLine(50 + (i * 100), 250, 50 + (i * 100), 260);
            g.drawString("Month " + i, 50 + (i * 100) - 25, 275);
        }

        // Draw y-axis
        g.drawLine(50, 50, 50, 250);
        for (int i = 0; i <= 10; i++) {
            g.drawLine(50, 250 - (i * 20), 60, 250 - (i * 20));
            g.drawString("$" + i * 1000, 15, 250 - (i * 20));
        }

        // Plot expenses
        g.setColor(Color.RED);
        for (int i = 0; i < numMonths; i++) {
            int x = 50 + (i * 100) + 50;
            int y = 250 - (expenses[i] / 100);
            g.fillOval(x - 5, y - 5, 10, 10);
            if (i > 0) {
                int prevX = 50 + ((i - 1) * 100) + 50;
                int prevY = 250 - (expenses[i - 1] / 100);
                g.drawLine(prevX, prevY, x, y);
            }
        }
    }

    void xy() {
        JFrame frame = new JFrame("Monthly Expenses Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(50 + (numMonths * 100), 300);
        x panel = new x(expenses, numMonths);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        int a[] = { 1, 2, 3, 4, 5 };
        new x(a, 5).xy();
    }
}
