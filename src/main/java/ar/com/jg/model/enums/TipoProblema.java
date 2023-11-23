package ar.com.jg.model.enums;

public enum TipoProblema {

    Basico(0),
    Intermedio(1),
    Complejo(2);

    private final int idTipoProblema;

    private TipoProblema(int idTipoProblema) {

        this.idTipoProblema = idTipoProblema;

    }

    public int getIdTipoProblema() {

        return idTipoProblema;

    }

}
