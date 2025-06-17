
package Model;

public class Estagiario extends Humano {
    private String curso;
    private String instituicaoEnsino;
    private int periodo;

    public Estagiario() {
        this.periodo = 0;
        this.instituicaoEnsino = "";
        this.curso = "";
    }

    public int calcular(){  //sobrescrita
	return 2023 - getIdade();
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public void setPeriodo(int periodo) throws PerEstException{
	if(1<=periodo && 10>=periodo){
		this.periodo = periodo;
	}else{        
		throw new PerEstException();
    	}
    }

    public String getCurso() {
        return this.curso;
    }

    public String getInstituicaoEnsino() {
        return this.instituicaoEnsino;
    }

    public int getPeriodo() {
        return this.periodo;
    }

}