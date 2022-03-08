import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;
import java.text.DecimalFormat;  

public class App {

    private static double a;
    private static double b;
    private static int mode=0;

    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("JFrame Test");
        int height = 400;
        int width = 310;

        int paddingTop = 100;
        int paddingSides = 25;
        int spacing = 10;
        int buttonsSizeX = 50;
        int buttonsSizeY = 50;

        DecimalFormat df = new DecimalFormat("###.############");

        // paddingTop+=(spacing+buttonsSizeY);

        JLabel l = new JLabel("");
        l.setBounds(paddingSides,paddingSides,3*(spacing+buttonsSizeX)-spacing,buttonsSizeY);
        f.add(l);

        JLabel l2 = new JLabel("");
        l2.setBounds(50,75,500,30);
        f.add(l2);

        

        int space2 = paddingSides+(3*(buttonsSizeX+spacing));

        
        JButton buttonComma = new JButton("+");
        buttonComma.setBounds(paddingSides+(0*(buttonsSizeX+spacing)),paddingTop+(3*(buttonsSizeY+spacing)),buttonsSizeX,buttonsSizeY);
        buttonComma.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                buttonComma.setEnabled(false);
                

                

            }   
        });
        f.add(buttonComma);


        

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton((j+(3*i)+1)+"");
                button.setBounds(paddingSides+(j*(buttonsSizeX+spacing)), paddingTop+(i*(buttonsSizeY+spacing)), buttonsSizeX, buttonsSizeY);
                button.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){  
                        if (mode==0) {
                            a*=10;
                            a+=Integer.parseInt(button.getText());
                            l.setText(df.format(a)+"");
                        } else if (mode==1){
                            b*=10;
                            b+=Integer.parseInt(button.getText());
                            l.setText(df.format(a)+"+"+df.format(b));
                        } else if (mode==2) {
                            b*=10;
                            b+=Integer.parseInt(button.getText());
                            l.setText(df.format(a)+"-"+df.format(b));
                        } else if (mode==3) {
                            b*=10;
                            b+=Integer.parseInt(button.getText());
                            l.setText(df.format(a)+"*"+df.format(b));
                        } else if (mode==4) {
                            b*=10;
                            b+=Integer.parseInt(button.getText());
                            l.setText(df.format(a)+"/"+df.format(b));
                        }
                    }               
                });
                
                f.add(button);
            }
        }

        JButton button = new JButton("0");
        button.setBounds(paddingSides+(1*(buttonsSizeX+spacing)), paddingTop+(3*(buttonsSizeY+spacing)), buttonsSizeX, buttonsSizeY);
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                if (mode==0) {
                    a*=10;
                    l.setText(df.format(a)+"");
                } else {
                    b*=10;
                    l.setText(df.format(a)+"+"+df.format(b));
                }
            }               
        });
        f.add(button);




        //Funkciju pogas
        JButton buttonPlus = new JButton("+");
        buttonPlus.setBounds(space2,paddingTop+(0*(buttonsSizeY+spacing)),buttonsSizeX,buttonsSizeY);
        buttonPlus.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                buttonPlus.setEnabled(false);
                mode = 1;
                l.setText(df.format(a)+"+"+df.format(b));
                

            }   
        });
        f.add(buttonPlus);

        
        JButton buttonMinus = new JButton("-");
        buttonMinus.setBounds(space2,paddingTop+(1*(buttonsSizeY+spacing)),buttonsSizeX,buttonsSizeY);
        buttonMinus.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                buttonMinus.setEnabled(false);
                mode = 2;
                l.setText(df.format(a)+"-"+df.format(b));
            }   
        });
        f.add(buttonMinus);


        JButton buttonTimes = new JButton("*");
        buttonTimes.setBounds(space2,paddingTop+(2*(buttonsSizeY+spacing)),buttonsSizeX,buttonsSizeY);
        buttonTimes.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                buttonTimes.setEnabled(false);
                mode = 3;
                l.setText(df.format(a)+"*"+df.format(b));
            }   
        });
        f.add(buttonTimes);

        JButton buttonDivide = new JButton("/");
        buttonDivide.setBounds(space2,paddingTop+(3*(buttonsSizeY+spacing)),buttonsSizeX,buttonsSizeY);
        buttonDivide.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                buttonDivide.setEnabled(false);
                mode = 4;
                l.setText(df.format(a)+"/"+df.format(b));
            }   
        });
        f.add(buttonDivide);

        JButton buttonClear = new JButton("C");
        buttonClear.setBounds(paddingSides+(3*(buttonsSizeX+spacing)), paddingTop+(-1*(buttonsSizeY+spacing)), buttonsSizeX, buttonsSizeY);
        buttonClear.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){   
                if (mode!=0) {
                    b=0;
                    l.setText(df.format(a)+"");

                } else {
                    a = 0;
                    
                    l.setText("");
                }
                mode = 0;
                buttonPlus.setEnabled(true);
                buttonMinus.setEnabled(true);
                buttonTimes.setEnabled(true);
                buttonDivide.setEnabled(true);
            }               
        });
        f.add(buttonClear);

        JButton buttonEquals = new JButton("=");
        buttonEquals.setBounds(paddingSides+(2*(buttonsSizeX+spacing)), paddingTop+(3*(buttonsSizeY+spacing)), buttonsSizeX, buttonsSizeY);
        buttonEquals.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){   
                if (!(buttonPlus.isEnabled())) {
                    
                }
                switch (mode) {
                    case 1:
                        a=(a+b);
                        l.setText(df.format(a)+"");
                        
                        break;
                    case 2:
                        a=(a-b);
                        l.setText(df.format(a)+"");
                        break;
                    case 3:
                        a=(a*b);
                        l.setText(df.format(a)+"");
                        break;
                    case 4:
                        a=((Double.valueOf(a))/b);
                        l.setText(df.format(a)+"");
                        break;
                }
                b = 0;
                mode = 0;
                buttonPlus.setEnabled(true);
                buttonMinus.setEnabled(true);
                buttonTimes.setEnabled(true);
                buttonDivide.setEnabled(true);
            }               
        });
        f.add(buttonEquals);

        



        f.setSize(width,height);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
        
    

    

    
}
