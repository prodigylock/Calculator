import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;
import java.text.DecimalFormat;

public class App {

    private static double a;
    private static double b;
    private static int mode = 0;
    private static int decimalCountA;
    private static int decimalCountB;

    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("JFrame Test");
        /*
        TODO:
        =automatic resize to window size
        =add everything to panel.
        =add scientific expansion buttons
        =dynamic button placement using coordinates
            =coordinate calculation method
        =add decimal limit to stop rounding errors
        =function buttons reneable after another one is pressed.
        */



        //button layout and padding
        int spacing = 5;
        int paddingSides = 0;

        int buttonsSizeX = 50;
        int buttonsSizeY = 50;

        //dynamic Jframe size  
        int height =(2*paddingSides)+(5*(spacing+buttonsSizeX+7));;
        int width = (2*paddingSides)+(4*(spacing+buttonsSizeX+4)-spacing);

        //Get rid of zero if its not used.
        DecimalFormat df = new DecimalFormat("###.####");

        //Number output and display. bounds are set dynamically based on sizes
        JLabel l = new JLabel("");
        l.setBounds(paddingSides, paddingSides, 3 * (spacing + buttonsSizeX) - spacing, buttonsSizeY);
        f.add(l);

        //side button(function line) spacing from left side calculation
        int space2 = paddingSides + (3 * (buttonsSizeX + spacing));

        //Comma button added
        JButton buttonComma = new JButton(".");
        //dynamic comma button bound based on key array
        buttonComma.setBounds(paddingSides + (0 * (buttonsSizeX + spacing)),
        buttonsSizeY + (3 * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
        buttonComma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //disables button
                buttonComma.setEnabled(false);
                //if there is an output already gets its decimal places
                decimalCountA = getDecimalLenght(a);
                decimalCountB = getDecimalLenght(b);
            }
        });
        f.add(buttonComma);

        // creates an array of button 1-9
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton((j + (3 * i) + 1) + "");
                //dynamic bounds
                button.setBounds(paddingSides + (j * (buttonsSizeX + spacing)),
                buttonsSizeY + (i * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // get button number
                        int number = Integer.parseInt(button.getText());

                        // Adding number without comma
                        if (buttonComma.isEnabled()) {
                            if (mode == 0) {
                                a *= 10;
                                a += number;
                            } else {
                                b *= 10;
                                b += number;
                            }
                            // Adding number with comma
                        } else {
                            //add number to last working number behind decimal
                            if (mode == 0) {
                                a += (number / Math.pow(10, decimalCountA + 1));
                                decimalCountA++;
                            } else {
                                b += (number / Math.pow(10, decimalCountB + 1));
                                decimalCountB++;
                            }
                        }

                        //decimal formating
                        String a_out = df.format(a);
                        String b_out = df.format(b);

                        //sign list for easy premade output
                        String modeSign[] = { "", "+", "-", "*", "/" };

                        //output creation for no decimal
                        if (!buttonComma.isEnabled()) {
                            a_out = a + "";
                            b_out = b + "";
                        }
                        //output creation for decimal
                        if (mode == 0) {
                            l.setText(a_out + modeSign[mode]);
                        } else {
                            l.setText(a_out + modeSign[mode] + b_out);
                        }
                    }
                });

                f.add(button);
            }
        }

        //zero button
        JButton button = new JButton("0");
        button.setBounds(paddingSides + (1 * (buttonsSizeX + spacing)), buttonsSizeY + (3 * (buttonsSizeY + spacing)),
                buttonsSizeX, buttonsSizeY);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //if no decimal place
                if (buttonComma.isEnabled()) {
                    if (mode == 0) {
                        a *= 10;
                        l.setText(df.format(a) + "");
                    } else {
                        b *= 10;
                        l.setText(df.format(a) + "+" + df.format(b));
                    }
                //if decimal place
                } else {
                    if (mode == 0) {
                        decimalCountA++;
                        l.setText(String.format("%." + decimalCountA + "f", a));
                    } else {
                        decimalCountB++;
                        l.setText(String.format("%." + decimalCountA + "f", a));
                    }
                }
            }
        });
        f.add(button);

    // Function buttons
        //PLus button
        JButton buttonPlus = new JButton("+");
        buttonPlus.setBounds(space2, buttonsSizeY + (0 * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
        buttonPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //button disabled and mode changed. current numbers displayed
                buttonPlus.setEnabled(false);
                mode = 1;
                l.setText(df.format(a) + "+" + df.format(b));
                //reneabled comma for second number
                buttonComma.setEnabled(true);

            }
        });
        f.add(buttonPlus);

        //minus button
        JButton buttonMinus = new JButton("-");
        buttonMinus.setBounds(space2, buttonsSizeY + (1 * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
        buttonMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //button disabled and mode changed. current numbers displayed
                buttonMinus.setEnabled(false);
                mode = 2;
                l.setText(df.format(a) + "-" + df.format(b));
                buttonComma.setEnabled(true);
            }
        });
        f.add(buttonMinus);

        //times button
        JButton buttonTimes = new JButton("*");
        buttonTimes.setBounds(space2, buttonsSizeY + (2 * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
        buttonTimes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //button disabled and mode changed. current numbers displayed
                buttonTimes.setEnabled(false);
                mode = 3;
                l.setText(df.format(a) + "*" + df.format(b));
                buttonComma.setEnabled(true);
            }
        });
        f.add(buttonTimes);

        //divide button
        JButton buttonDivide = new JButton("/");
        buttonDivide.setBounds(space2, buttonsSizeY + (3 * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
        buttonDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //button disabled and mode changed. current numbers displayed
                buttonDivide.setEnabled(false);
                mode = 4;
                l.setText(df.format(a) + "/" + df.format(b));
                buttonComma.setEnabled(true);
            }
        });
        f.add(buttonDivide);
        
        //clear current number and reset mode
        JButton buttonClear = new JButton("C");
        buttonClear.setBounds(paddingSides + (3 * (buttonsSizeX + spacing)),
        buttonsSizeY + (-1 * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mode != 0) {
                    b = 0;
                    l.setText(df.format(a) + "");

                } else {
                    a = 0;

                    l.setText("");
                }
                mode = 0;
                buttonPlus.setEnabled(true);
                buttonMinus.setEnabled(true);
                buttonTimes.setEnabled(true);
                buttonDivide.setEnabled(true);
                buttonComma.setEnabled(true);
            }
        });
        f.add(buttonClear);

        //equals button
        JButton buttonEquals = new JButton("=");
        buttonEquals.setBounds(paddingSides + (2 * (buttonsSizeX + spacing)),
        buttonsSizeY + (3 * (buttonsSizeY + spacing)), buttonsSizeX, buttonsSizeY);
        buttonEquals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //all calculations are done and put into number a
                if (!(buttonPlus.isEnabled())) {

                }
                switch (mode) {
                    case 1:
                    //adding numbers and displaying
                        a = (a + b);
                        l.setText(df.format(a) + "");

                        break;
                    case 2:
                    //subtracting numbers and displaying
                        a = (a - b);
                        l.setText(df.format(a) + "");
                        break;
                    case 3:
                    //multiplying number and displaying
                        a = (a * b);
                        l.setText(df.format(a) + "");
                        break;
                    case 4:
                    //dividing numbers and displaying
                        a = ((Double.valueOf(a)) / b);
                        l.setText(df.format(a) + "");
                        break;
                }
                //reseting number b
                b = 0;

                //setting mode to none
                mode = 0;

                //reneable all buttons
                buttonPlus.setEnabled(true);
                buttonMinus.setEnabled(true);
                buttonTimes.setEnabled(true);
                buttonDivide.setEnabled(true);
                buttonComma.setEnabled(true);
            }
        });
        f.add(buttonEquals);

        //set frame using variables
        f.setSize(width, height);
        f.setLayout(null);
        f.setVisible(true);

        //exit app on closing window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //get decimal places
    public static int getDecimalLenght(double number) {
        DecimalFormat df = new DecimalFormat("###.####");
        String[] DecimalSplit = df.format(number).split("\\.");
        try {
            return DecimalSplit[1].length();
        } catch (Exception e) {
            return 0;
        }

    }

}
