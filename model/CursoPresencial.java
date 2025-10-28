package model;
public class CursoPresencial extends Curso {
    private String sala;

    public CursoPresencial(String nome, String codigo, int cargaHoraria, String sala) {
        super(nome, codigo, cargaHoraria);
        this.sala = sala;
    }

    @Override
    public void detalharCurso() {
        System.out.println("Presencial: " + nome + " | Sala:  " + sala + " | CH: " + cargaHoraria);
    }
}