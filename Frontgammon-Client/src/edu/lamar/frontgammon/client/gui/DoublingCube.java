/*
 * Die.java
 *
 * This file is part of Frontgammon.
 *
 * Frontgammon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Frontgammon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Frontgammon.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Created on October 1, 2007, 4:03 PM
 */

package edu.lamar.frontgammon.client.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
/**
 *
 * @author  James
 */
public class DoublingCube extends javax.swing.JPanel {
    
    public int value;
    public Color[] col = new Color[2];
    
    /** Creates new form Die */
    public DoublingCube() {
        initComponents();
        value = 0;
        col[0] = new Color(58,118,118);
        col[1] = new Color(255,167,79);
                
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        setMinimumSize(new java.awt.Dimension(30, 30));
        setPreferredSize(new java.awt.Dimension(30, 30));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    @Override public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(value != 0)
        {
            Graphics2D g = (Graphics2D)graphics;
        
            String text = String.valueOf(value);
            g.setFont(Font.decode(Font.SERIF));
            g.drawString(text, this.getBounds().width / 2 - 4, this.getBounds().height / 2 + 4);
        }
    }
    
    public void updateDouble(int val, int newOwner) {
        value = val;
        if(val > 0)
            this.setBackground(col[newOwner]);
        repaint();
    }
    
}
