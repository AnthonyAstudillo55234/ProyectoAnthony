import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form5 extends JFrame {
    private JPanel mainPanel;
    private JButton AGREGARPRODUCTOButton;
    private JButton ELIMINARPRODUCTOButton;

    public form5() {
        setTitle("Menu Administrador");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        AGREGARPRODUCTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form6();
                setVisible(false);
            }
        });
    }
}
