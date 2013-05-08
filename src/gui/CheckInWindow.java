package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import database.DatabaseUpdate;


/**
 *
 * @author 
 */
public class CheckInWindow extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	/**
     * Creates new form CheckInWindow
     */
    public CheckInWindow(JTextArea textArea){
        initComponents();
        this.textArea = textArea;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roomTF = new javax.swing.JTextField();
        cIButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Check In");
        setResizable(false);

        jLabel1.setText("Room");

        roomTF.setColumns(5);
        
        cIButton.setText("Check In");
        cIButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkIn();
			}
		});
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cIButton)
                    .addComponent(roomTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(roomTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cIButton)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

   
    private void checkIn(){
    	if(roomTF.getText().length()==0){
    		JOptionPane.showMessageDialog(null, "Fill room's text field", 
                    "Empty text field", JOptionPane.INFORMATION_MESSAGE);
    		return;
    	}
    	
    	String CHECKINQUERY = 
    			"UPDATE Rental " +
    			"SET checkIn = True " +
    			"WHERE Room_idRoom = '"+roomTF.getText()+"'";
    	
    	
    	DatabaseUpdate checkInUpdate = new DatabaseUpdate(textArea, CHECKINQUERY);
    	if(checkInUpdate.getConnectionState()==true&&
    			checkInUpdate.execute()){
    		textArea.append(CHECKINQUERY+"\n");
    	}else{
    		JOptionPane.showMessageDialog(null, "Check in field", 
                    "Check in error", JOptionPane.ERROR_MESSAGE);
    	}
    	
    }
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton cIButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField roomTF;
    // End of variables declaration                   
}
