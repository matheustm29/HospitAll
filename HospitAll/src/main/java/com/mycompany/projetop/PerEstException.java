//VICTOR EHITI ITIMURA TAMAY 2485561


package com.mycompany.projetop;

import javax.swing.JOptionPane;

public class PerEstException extends Exception{
	
	public void impPerEstException(){
                JOptionPane.showMessageDialog(
                        null,
                        "O PERIODO INSERIDO DEVE SER ENTRE 1 A 10",
                        "ERRO",
                        1
                );
            
		
	}
}