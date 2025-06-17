//VICTOR EHITI ITIMURA TAMAY 2485561

package com.mycompany.projetop;

import javax.swing.JOptionPane;

public class TipoDoencaException extends Exception{

	public void impTipoDoencaException(){
                JOptionPane.showMessageDialog(
                        null,
                        "\nO TIPO DE DOENCA TEM QUE SER BACTERIA OU VIRUS!!",
                        "ERRO",
                        1
                );
                
		
	}	

}