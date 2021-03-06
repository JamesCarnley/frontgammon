/*
 * GameBoard.java
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
 * Created on September 22, 2007, 9:01 PM
 */

package edu.lamar.frontgammon.client.gui;

import edu.lamar.frontgammon.client.*;
import edu.lamar.frontgammon.game.*;
import edu.lamar.frontgammon.protocol.*;
import java.io.IOException;
import java.awt.Color;
/**
 *
 * @author  Nitesh & James
 */
public class GameGUI extends javax.swing.JFrame {    
    
    public Client cli;
    public MessageBox msgBox;
    public GameState gs;
    public askGUI aGUI;
    
    /** Creates new form GameBoard */
    public GameGUI() {
        initComponents();                  
        this.die1.setBackground(new Color(58,118,118));
        this.die2.setBackground(new Color(255,167,79));
        
        this.doubleBut.setEnabled(false);
        this.rolldiceBut.setEnabled(false);
        this.undoBut.setEnabled(false);
        this.confirmBut.setEnabled(false);
        
        this.gs = new GameState(true);
        
        aGUI = new askGUI();
        aGUI.setVisible(false);
    }

    public void setGameState() {        
        
        this.boardAndIntelligence1.updateGameState(gs);
        this.cli.setBackgammonPanel(boardAndIntelligence1);        
        this.boardAndIntelligence1.cli = cli;
        this.boardAndIntelligence1.msgBox = msgBox;
        this.rolldiceBut.setEnabled(true);
        this.setVisible(true);
        this.updateAll();
        //this.boardAndIntelligence1.allowMovement(false);
        //This is first player request to start
        msgBox.showMessage(gs.getPlayer(cli.playerID),"Game has Started "+gs.getOpponent(cli.playerID)+" is the opponent.\nPlease Click Roll Dice Button. \n Whoever gets higher dice value, will make the first move.");        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
   
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        player1Name = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pip1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        resignBut = new javax.swing.JButton();
        undoBut = new javax.swing.JButton();
        quitBut = new javax.swing.JButton();
        rolldiceBut = new javax.swing.JButton();
        doubleBut = new javax.swing.JButton();
        confirmBut = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        die2 = new edu.lamar.frontgammon.client.gui.Die();
        die1 = new edu.lamar.frontgammon.client.gui.Die();
        doublingCube1 = new edu.lamar.frontgammon.client.gui.DoublingCube();
        jPanel2 = new javax.swing.JPanel();
        player2Name = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pip2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        boardAndIntelligence1 = new edu.lamar.frontgammon.client.gui.BoardAndIntelligence();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Frontgammon (John vs Doe)");
        setMinimumSize(new java.awt.Dimension(500, 300));
        jLabel7.setText("Status of game. Etc.");
        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Green"));
        player1Name.setText("Player1");

        jLabel2.setText("Pips:");

        pip1.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player1Name)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(pip1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(player1Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pip1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        resignBut.setText("Resign");
        resignBut.setEnabled(false);

        undoBut.setText("Undo");

        quitBut.setText("Quit");
        quitBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitButMouseClicked(evt);
            }
        });

        rolldiceBut.setText("Roll Dice");
        rolldiceBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getDie(evt);
            }
        });

        doubleBut.setText("Double");
        doubleBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doubleButMouseClicked(evt);
            }
        });

        confirmBut.setText("Confirm");
        confirmBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButMouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(die1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doublingCube1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(die2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(doublingCube1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(die1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(die2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(doubleBut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rolldiceBut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(undoBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(confirmBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(quitBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resignBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doubleBut)
                    .addComponent(undoBut)
                    .addComponent(resignBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rolldiceBut)
                    .addComponent(confirmBut)
                    .addComponent(quitBut)))
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Orange"));
        player2Name.setText("Player2");

        jLabel5.setText("Pips:");

        pip2.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player2Name)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(pip2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(player2Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pip2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 204));
        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 61, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardAndIntelligence1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(boardAndIntelligence1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitButMouseClicked
// TODO add your handling code here:
         //this.doubleBut.setEnabled(false);
            

            Object[] parm = new Object[3];
            parm[0] = cli.gameID;
            parm[1] = cli.playerID;
            Message msg = new Message(Command.Quit,parm);
            try {
                this.cli.sendToServer(msg);
            } catch (IOException ex) {
                msgBox.showMessage(gs.getPlayer(cli.playerID),"Error: Cannot send quit action to server");        
            }
        System.exit(0);
            //this.setVisible(false);
    }//GEN-LAST:event_quitButMouseClicked

    private void confirmButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButMouseClicked
// TODO add your handling code here:
        //if(this.confirmBut.isEnabled())
        //{
            this.confirmBut.setEnabled(false);
            this.undoBut.setEnabled(false);
             Object[] parm = new Object[3];
            parm[0] = cli.gameID;
            parm[1] = cli.playerID;
            Message msg = new Message(Command.Confirm,parm);
            try {
                this.cli.sendToServer(msg);
            } catch (IOException ex) {
                msgBox.showMessage(gs.getPlayer(cli.playerID),"Error: Cannot send Confirm action to server");        
            }
        //}
    }//GEN-LAST:event_confirmButMouseClicked

    private void doubleButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doubleButMouseClicked
      // if(doubleBut.isEnabled())
      // {
            this.doubleBut.setEnabled(false);
            

            Object[] parm = new Object[3];
            parm[0] = cli.gameID;
            parm[1] = cli.playerID;
            Message msg = new Message(Command.DoubleCube,parm);
            try {
                this.cli.sendToServer(msg);
            } catch (IOException ex) {
                msgBox.showMessage(gs.getPlayer(cli.playerID),"Error: Cannot send double dice action to server");        
            }
      // }
    }//GEN-LAST:event_doubleButMouseClicked

    private void getDie(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getDie
        if(this.rolldiceBut.isEnabled())
        {        
            try {
                
                this.doubleBut.setEnabled(false);
                this.rolldiceBut.setEnabled(false);
                Object[] parm = new Object[3];
                parm[0] = cli.gameID;
                parm[1] = cli.playerID;
                Message msg = new Message(Command.RollDice,parm);

                this.cli.sendToServer(msg);

                if(!this.gs.notFirstMove)
                {
                     //this.boardAndIntelligence1.allowMovement(false);
                    msgBox.showMessage(gs.getPlayer(cli.playerID),"Wait for the opponent to roll the dice...");

                }
                else
                    this.boardAndIntelligence1.allowMovement(true);

            } catch (IOException ex) {
                msgBox.showMessage(gs.getPlayer(cli.playerID),"Error: Cannot send roll dice action to server");        
            }
        }
    }//GEN-LAST:event_getDie
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.lamar.frontgammon.client.gui.BoardAndIntelligence boardAndIntelligence1;
    private javax.swing.JButton confirmBut;
    private edu.lamar.frontgammon.client.gui.Die die1;
    private edu.lamar.frontgammon.client.gui.Die die2;
    private javax.swing.JButton doubleBut;
    private edu.lamar.frontgammon.client.gui.DoublingCube doublingCube1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel pip1;
    private javax.swing.JLabel pip2;
    private javax.swing.JLabel player1Name;
    private javax.swing.JLabel player2Name;
    private javax.swing.JButton quitBut;
    private javax.swing.JButton resignBut;
    private javax.swing.JButton rolldiceBut;
    private javax.swing.JButton undoBut;
    // End of variables declaration//GEN-END:variables
    
    
    public void updateDoubleDice() {
        this.doublingCube1.updateDouble(gs.getDoubleDice(),gs.lastDoubleOwnerID);
        this.doubleBut.setEnabled(false);       
        
    } 
    
    public void updateRest() {
        this.pip1.setText(String.valueOf(gs.pipCount1));
        this.pip2.setText(String.valueOf(gs.pipCount2));        
    }
    
     public void updateDice() {
        
        this.die1.val = this.gs.d1;
        this.die1.repaint();
        this.die2.val = this.gs.d2;
        this.die2.repaint();
        
        if(this.die1.val != 0 && this.die2.val != 0 && this.gs.notFirstMove)
        {
            if(this.gs.turnID == 0)
            {
                this.die1.setBackground(new Color(58,118,118));
                this.die2.setBackground(new Color(58,118,118));
            }
            else
            {
                this.die1.setBackground(new Color(255,167,79));
                this.die2.setBackground(new Color(255,167,79));
            }

            if(this.gs.turnID == this.cli.playerID)      
                this.boardAndIntelligence1.allowMovement(true);
           // else
           //     this.boardAndIntelligence1.allowMovement(false);

        }
        
    }
     
    public void updateAll() {
         updateDoubleDice();
         updateDice();
         updateRest();
         if(cli.playerID==0)
             this.setTitle("Frontgammon ( " + gs.player1 + " Vs " + gs.player2 + " )" + " :: " +gs.player1);
         else if(cli.playerID==1)
             this.setTitle("Frontgammon ( " + gs.player1 + " Vs " + gs.player2 + " )" + " :: " +gs.player2);
         this.player1Name.setText(gs.player1);
         this.player2Name.setText(gs.player2);         
         this.boardAndIntelligence1.repaint();         
    }
    
    public void refresh() {
         updateDoubleDice();
         updateDice();
         updateRest();
         this.boardAndIntelligence1.repaint();
    }
    
    public void moveFinished() {
        if(gs.turnID == cli.playerID)
        {
            this.confirmBut.setEnabled(true);
            //this.boardAndIntelligence1.allowMovement(false);
        }
    }
    
    public void updateTurn() {
        if(gs.turnID == cli.playerID)         
         {
             this.doubleBut.setEnabled(true);
             this.rolldiceBut.setEnabled(true); 
             //this.boardAndIntelligence1.allowMovement(false);
         }
         else
         {
             this.doubleBut.setEnabled(false);
             this.rolldiceBut.setEnabled(false);
             //this.boardAndIntelligence1.allowMovement(false);        
         }
    }
    
    
     public void informResultofDiceRoll() {
         if(gs.turnID == cli.playerID)
         {           
            this.boardAndIntelligence1.allowMovement(true);            
            msgBox.showMessage(gs.getPlayer(cli.playerID),"You won the dice roll. \n Please move checker to start game");     
             if(this.cli.playerID == 0)
             {
                this.die1.setBackground(new Color(58,118,118));
                this.die2.setBackground(new Color(58,118,118));
             }
             else
             {
                this.die1.setBackground(new Color(255,167,79));
                this.die2.setBackground(new Color(255,167,79));
             }
            
         }
         else
         {
             //this.boardAndIntelligence1.allowMovement(false);            
             if(this.cli.playerID == 0)
             {
                this.die1.setBackground(new Color(255,167,79));
                this.die2.setBackground(new Color(255,167,79));
                
             }
             else
             {
                this.die1.setBackground(new Color(58,118,118));
                this.die2.setBackground(new Color(58,118,118));
             }
            msgBox.showMessage(gs.getPlayer(cli.playerID),"You lost the dice roll. \nPlease wait for the "+gs.getOpponent(cli.playerID)+" to make move");         
         
         }
         this.boardAndIntelligence1.repaint();
     }
     
     public void reRollDice() {
         updateDice();
         this.rolldiceBut.setEnabled(true);
         msgBox.showMessage(gs.getPlayer(cli.playerID),"There is a tie. Please click roll dice button again");        
     }
     
     public GameState getGameState() {
         return this.gs;
     }
     
     public void askDouble() 
     {
         aGUI.setVisible(true);
         while(aGUI.isVisible());
         if(aGUI.askResult)
         {
            Object[] parm = new Object[3];
            parm[0] = cli.gameID;
            parm[1] = cli.playerID;
            Message msg = new Message(Command.DoubleCube,parm);
            try {
                this.cli.sendToServer(msg);
            } catch (IOException ex) {
                msgBox.showMessage("Error","Cannot send double dice action to server");        
            }
            
         }
         else
            {
               Object[] parm = new Object[3];
                parm[0] = cli.gameID;
                parm[1] = cli.playerID;
                Message msg = new Message(Command.Rejected,parm);
            try {
                this.cli.sendToServer(msg);
            } catch (IOException ex) {
                msgBox.showMessage(gs.getPlayer(cli.playerID),"Error: Cannot send reject action to server");        
            } 
            }
     }
     public void reportwin()
     {
        // winWait ww = new winWait();
         //ww.setVisible(true);
         msgBox.showMessage(gs.getPlayer(cli.playerID),"You won the game because your opponent "+gs.getOpponent(cli.playerID)+" has quit the game");
        this.setVisible(false);
         
     }
     
     public void reportWinonReject()
     {
         msgBox.showMessage(gs.getPlayer(cli.playerID),"You won the game becuase your opponent "+gs.getOpponent(cli.playerID)+" has rejected the double cube");
         //System.exit(0);
         
     }
     
    public void gameStateChanged() {
        
    }
}
