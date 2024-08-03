import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form5 extends JFrame {
    private JPanel mainPanel;
    private JButton AGREGARPRODUCTOButton;
    private JButton ELIMINARPRODUCTOButton;
    private JButton BUSCARPRODUCTOButton;
    private JButton REGRESARButton;
    private JButton ACTUALIZARSTOCKButton;

    public form5() {
        setTitle("Menu Administrador");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 400));
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
        ELIMINARPRODUCTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form7();
                setVisible(false);
            }
        });
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form2();
                setVisible(false);
            }
        });
        BUSCARPRODUCTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form8();
                setVisible(false);
            }
        });
        ACTUALIZARSTOCKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new form14();
                setVisible(false);
            }
        });
    }
}
