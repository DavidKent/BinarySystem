/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binarysystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * @author David Dolyniuk
 */
public class BaseHandler {
    public List<Node> m_NodeList = new ArrayList<>();
    public Node m_SelectedNode = null;
    
    private BaseGUI m_parent;
    private JPanel m_grid;
    
    public BaseHandler(BaseGUI b) {
        m_parent = b;
        m_grid = m_parent.getGrid();
    }
    
    private void gridPaint() {
        for(int i = 0; i < m_NodeList.size(); i++) {
            List<Node> connections = m_NodeList.get(i).m_NodeConnections;
            if(connections.size() > 0) {
                for(int j = 0; j < connections.size(); j++) {
                    Graphics g = m_grid.getGraphics();
                    g.setColor(Color.red);
                    m_grid.getGraphics().drawLine(m_NodeList.get(i), i, i, i);
                }
            }
        }
        m_grid.repaint();
        m_grid.revalidate();
    }
    
    //-----EVENTS------
    public void grid_onMouseClick(MouseEvent e) {
        Node n = new Node(m_parent, new Point(e.getX(), e.getY()));
        m_grid.add(n);
        m_NodeList.add(n);
        gridPaint();
    }
    
    public void grid_onMouseMove(MouseEvent e) {
        if(m_SelectedNode != null && m_SelectedNode.isMoving()) {
            m_SelectedNode.setLocation(new Point(e.getX(), e.getY()));
            gridPaint();
        }
    }
    
    
}
