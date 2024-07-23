import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form1 extends JFrame{
    private JPanel mainPanel;
    private JButton CLIENTEButton;
    private JButton ADMINISTRADORButton;

    public form1() {
        setTitle("Seleccion");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ADMINISTRADORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form2();
                setVisible(false);
            }
        });
        CLIENTEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form3();
                setVisible(false);
            }
        });
    }
}
