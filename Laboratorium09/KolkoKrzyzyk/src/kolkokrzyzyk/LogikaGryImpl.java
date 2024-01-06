package kolkokrzyzyk;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ola
 */


public class LogikaGryImpl implements LogikaGry, Serializable{
    
    private String startGry = "X";
    private int xLicznik = 0;
    private int oLicznik = 0;
    boolean spr;
    
    private JButton[] przyciski;
    private JLabel jLbLGraczX;
    private JLabel jLblGraczO;
    
    private NewJFrame newJFrameInstance;


    public LogikaGryImpl(JButton[] przyciski, JLabel jLbLGraczX, JLabel jLblGraczO, NewJFrame newJFrameInstance) {
        this.przyciski = przyciski;
        this.jLbLGraczX = jLbLGraczX;
        this.jLblGraczO = jLblGraczO;
        this.newJFrameInstance = newJFrameInstance;
}
    
    
    private void wybierzGracza(){
        
          if(startGry.equalsIgnoreCase("X")){
              startGry = "O";
          }else{
              startGry = "X";
          }

    }
   
    private void wynikGry(){
       jLbLGraczX.setText(String.valueOf(xLicznik));
       jLblGraczO.setText(String.valueOf(oLicznik));

    }
    
    
    public void wykonajRuch(int indeks) {
        JButton przycisk = przyciski[indeks - 1];  
        System.out.println("Clicked button: " + indeks);


        przycisk.setText(startGry);

        if (startGry.equalsIgnoreCase("X")) {
            spr = false;
        } else {
            spr = true;
        }

        wybierzGracza();
        wygrany();
    }


     private void wygrany(){
        String b1 = getButton(1).getText();
        String b2 = getButton(2).getText();
        String b3 = getButton(3).getText();
        String b4 = getButton(4).getText();
        String b5 = getButton(5).getText();
        String b6 = getButton(6).getText();
        String b7 = getButton(7).getText();
        String b8 = getButton(8).getText();
        String b9 = getButton(9).getText();

        //poziomo
        if(b1 == ("X") && b2 == ("X") && b3 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(1).setBackground(Color.GREEN);
            getButton(2).setBackground(Color.GREEN);
            getButton(3).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }
        
        //poziomo
        if(b4 == ("X") && b5 == ("X") && b6 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(5).setBackground(Color.GREEN);
            getButton(6).setBackground(Color.GREEN);
            getButton(4).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }
        
        //poziomo
        if(b7 == ("X") && b8 == ("X") && b9 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(7).setBackground(Color.GREEN);
            getButton(8).setBackground(Color.GREEN);
            getButton(9).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }
        
        
        //pionowo    
        if(b1== ("X") && b5== ("X") && b8 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(1).setBackground(Color.GREEN);
            getButton(5).setBackground(Color.GREEN);
            getButton(8).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }
        
        
        //pionowo   
        if(b2 == ("X") && b6== ("X") && b9 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(2).setBackground(Color.GREEN);
            getButton(6).setBackground(Color.GREEN);
            getButton(9).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }
        
        //pionowo   
        if(b3 == ("X") && b4 == ("X") && b7 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(3).setBackground(Color.GREEN);
            getButton(4).setBackground(Color.GREEN);
            getButton(7).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }
        
        
        //skos 3,6,8
           if(b3 == ("X") && b6 == ("X") && b8 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(3).setBackground(Color.GREEN);
            getButton(6).setBackground(Color.GREEN);
            getButton(8).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }   
           
        //skos 1,6,7
        if(b1 == ("X") && b6 == ("X") && b7 == ("X")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz X wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(1).setBackground(Color.GREEN);
            getButton(6).setBackground(Color.GREEN);
            getButton(7).setBackground(Color.GREEN);
            
            xLicznik++;
            wynikGry();
        }
        
        
        //poziomo
        if(b1 == ("O") && b2 == ("O") && b3 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(1).setBackground(Color.GREEN);
            getButton(2).setBackground(Color.GREEN);
            getButton(3).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
        
        
        //poziomo
        if(b4 == ("O") && b5 == ("O") && b6 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(4).setBackground(Color.GREEN);
            getButton(5).setBackground(Color.GREEN);
            getButton(6).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
            
         //poziomo   
        if(b7 == ("O") && b8 == ("O") && b9 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(7).setBackground(Color.GREEN);
            getButton(8).setBackground(Color.GREEN);
            getButton(9).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
        
        
        //pionowo   
        if(b1== ("O") && b8 == ("O") && b5 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(1).setBackground(Color.GREEN);
            getButton(8).setBackground(Color.GREEN);
            getButton(5).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
        
        //pionowo   
        if(b2 == ("O") && b6 == ("O") && b9 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(2).setBackground(Color.GREEN);
            getButton(6).setBackground(Color.GREEN);
            getButton(9).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
        
        
        //pionowo    
        if(b3 == ("O") && b4 == ("O") && b7 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(3).setBackground(Color.GREEN);
            getButton(4).setBackground(Color.GREEN);
            getButton(7).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
        
        
        //skos   
        if(b3 == ("O") && b6 == ("O") && b8 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(3).setBackground(Color.GREEN);
            getButton(6).setBackground(Color.GREEN);
            getButton(8).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
        
        
        
        //skos   
        if(b1 == ("O") && b6 == ("O") && b7 == ("O")){
            JOptionPane.showMessageDialog(newJFrameInstance, "Gracz O wygrał!", "Kolko i krzyzyk", 
                                            JOptionPane.INFORMATION_MESSAGE);
            getButton(1).setBackground(Color.GREEN);
            getButton(5).setBackground(Color.GREEN);
            getButton(7).setBackground(Color.GREEN);
            
            oLicznik++;
            wynikGry();
        }
     }
    

      
   public JButton getButton(int index) {
    if (index >= 1 && index <= 9) {
        return przyciski[index - 1];
    } else {
        throw new IllegalArgumentException("Invalid button index: " + index);
    }
}


}
