import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int framewidth = 360;
        int frameheight = 640;

        JFrame frame = new JFrame("Dodge Lord");
        // frame.setVisible(true);
        frame.setSize(framewidth, frameheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Dodge_Lord Dodge_Lord = new Dodge_Lord();
        frame.add(Dodge_Lord);
        frame.pack();
        Dodge_Lord.requestFocus();
        frame.setVisible(true);




       
    }
}
