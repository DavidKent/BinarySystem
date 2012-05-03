/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binarysystem;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;


/**
 * @author David Dolyniuk
 */
public class Node extends JButton {
    public static final int SIZE = 15;
    
    public List<Node> m_NodeConnections = new ArrayList<>();
    
    private BaseGUI m_parent;
    private Point m_location;
    private boolean m_selected = false;
    private boolean m_moving = false;
    private Node m_self = this;
    private boolean m_state = false;
    private BaseHandler m_base;
    
    public Node(BaseGUI parent, Point location) {
        m_parent = parent;
        m_location = location;
        m_base = m_parent.m_BaseHandler;
        init();
    }
    
    private void init() {
        setLocation(m_location);
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                if(!e.isShiftDown()) {
                    return;
                }
                if(m_parent.isConnector()) {
                    if(m_base.m_SelectedNode == null) {
                        setMSelected();
                        m_base.m_SelectedNode = m_self;
                    }
                    else {
                        setRSelected();
                        m_base.m_SelectedNode.addNodeConnection(m_self);
                        m_base.m_SelectedNode = m_self;
                    }
                    return;
                }
                m_moving = !m_moving;
                m_base.m_SelectedNode = m_self;
                if(m_parent.isInitiator()) System.out.println("is Initiator");
            }
        });
    }
    
    public void addNodeConnection(Node n) {
        m_NodeConnections.add(n);
    }
    
    public void removeNodeConnection(Node n) {
        m_NodeConnections.remove(n);
    }
    
    public void setState(boolean state) {
        m_state = state;
    }
    
    public boolean isMoving() {
        return m_moving;
    }
    
    public void setMSelected() {
        m_selected = true;
        Border compound = BorderFactory.createLineBorder(Color.yellow);
        this.setBorder(compound);
    }
    
    public void setRSelected() {
        m_selected = true;
        Border compound = BorderFactory.createLineBorder(Color.red);
        this.setBorder(compound);
    }
    
    @Override
    public void setLocation(Point loc) {
        setBounds(loc.x - SIZE / 2, loc.y - SIZE / 2, SIZE, SIZE);
    }
}
