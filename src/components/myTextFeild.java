
package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author roqayahzaki
 */
public class myTextFeild extends JTextField {

    private final Animator animator;
    private boolean animateHinText=true;
    private float location;
    private boolean show;
    private String labelText="Email";
    private Color lineColor = new Color(102,145,36);
    
    private boolean mouseOver = false;
    
    public myTextFeild() {
        
        
        
        
        
        
        
        this.setBorder(new EmptyBorder(20, 3, 10, 3));
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false; 
                repaint();
            }
        });
        
        addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                showing(true);
            }

            @Override
            public void focusGained(FocusEvent e) {
                showing(false); 
            }
            
        });
        
        
       TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
                animateHinText = getText().equals("");
            }

            @Override
            public void timingEvent(float fraction) {
                location = fraction;
                repaint();
            }
        
    };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    
    private void showing(boolean action){
        if(animator.isRunning()){
            animator.stop();
        }
        else{
            location = 1;
        }
        animator.setStartFraction(1f - location);
        show = action;
        location = 1f - location;
        animator.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = this.getWidth();
        int height = this.getHeight();
        
        if(mouseOver){
            g2.setColor(lineColor);
        }
        else{
            g2.setColor(new Color(150, 150, 150));
        }
        
        g2.fillRect(2, height - 1 , width - 4 , 1);
        createHintText(g2);
        createLineStyle(g2);
        g2.dispose();
        
        
        
    }
    
    private void createHintText(Graphics2D g2){
        Insets in = getInsets();
        g2.setColor(new Color(101,116,81));
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(labelText, g2);
        double height = this.getHeight() - in.top - in.bottom;
        double texty = (height - r2.getHeight()) / 2;
        double size;
        
        if(animateHinText){
            if(show){
                size = (18 * (1-location));
            }
            else{
                size = 18 * location;
            }
        }else{
            size = 18;
        }
        g2.drawString(labelText, in.right, (int) (in.top + texty + ft.getAscent() - size));
    }
    
    private void createLineStyle(Graphics2D g2){
        if(this.isFocusOwner()){
            double width = this.getWidth() - 4;
            int height = this.getHeight();
            g2.setColor(lineColor);
            double size;
            
            if(show){
                size = width * (1 - location);
            }
            else{
                size = width * location;
            }
            double x = (width - size) / 2;
            g2.fillRect((int)(x + 2), height - 2, (int) size, 2);
        }
    }

    @Override
    public void setText(String t) {
        if (!getText().equals(t)){
            showing(t.equals(""));
        }
        super.setText(t); 
    }
    
    
    
    
}

