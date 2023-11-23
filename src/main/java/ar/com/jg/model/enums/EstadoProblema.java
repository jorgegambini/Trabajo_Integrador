package ar.com.jg.model.enums;

public enum EstadoProblema {

    Pendiente(0),
    Procesado(1),
    Resuelto(2),
    Cancelado(3);

    private final int idEstadoProblema;

    private EstadoProblema(int idEstadoProblema) {

        this.idEstadoProblema = idEstadoProblema;

    }

    public int getIdEstadoProblema() {

        return idEstadoProblema;

    }

}
