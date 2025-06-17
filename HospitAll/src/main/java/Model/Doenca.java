
package Model;

public class Doenca {
     
    private int id;
    private String tipo;
    private String sint;
    private String restr;

    public Doenca(){ 
	tipo = "";
	sint="";
	restr="";
     }


    public Doenca(String tipo, String sint, String restr) { //sobrecarga
        this.tipo = tipo;
        this.sint = sint;
        this.restr = restr;
    }

    public void setTipo(String tipo) throws TipoDoencaException{
        if(tipo.equalsIgnoreCase("bacteria") || tipo.equalsIgnoreCase("virus")){
		this.tipo = tipo;	
	} else {
		throw new TipoDoencaException();
	}

    }

    public void setSint(String sint) {
        this.sint = sint;
    }

    public void setRestr(String restr) {
        this.restr = restr;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getSint() {
        return this.sint;
    }

    public String getRestr() {
        return this.restr;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}