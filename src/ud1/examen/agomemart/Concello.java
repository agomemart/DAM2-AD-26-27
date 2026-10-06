package ud1.examen.agomemart;

import java.time.LocalDate;

/**
 * @author Adrián Gómez
 */
public class Concello {
    LocalDate dataLocal;
    LocalDate dataUTC;
    int icoEstadoCeo;
    int icoVento;
    int idConcello;
    String nomeConcello;
    double sensacionTermica;
    double temperatura;

    public Concello(LocalDate dataLocal, LocalDate dataUTC, int icoEstadoCeo, int icoVento, int idConcello,
            String nomeConcello, double sensacionTermica, double temperatura) {
        this.dataLocal = dataLocal;
        this.dataUTC = dataUTC;
        this.icoEstadoCeo = icoEstadoCeo;
        this.icoVento = icoVento;
        this.idConcello = idConcello;
        this.nomeConcello = nomeConcello;
        this.sensacionTermica = sensacionTermica;
        this.temperatura = temperatura;
    }

    public Concello() {
    }

    public LocalDate getDataLocal() {
        return dataLocal;
    }

    public void setDataLocal(LocalDate dataLocal) {
        this.dataLocal = dataLocal;
    }

    public LocalDate getDataUTC() {
        return dataUTC;
    }

    public void setDataUTC(LocalDate dataUTC) {
        this.dataUTC = dataUTC;
    }

    public int getIcoEstadoCeo() {
        return icoEstadoCeo;
    }

    public void setIcoEstadoCeo(int icoEstadoCeo) {
        this.icoEstadoCeo = icoEstadoCeo;
    }

    public int getIcoVento() {
        return icoVento;
    }

    public void setIcoVento(int icoVento) {
        this.icoVento = icoVento;
    }

    public int getIdConcello() {
        return idConcello;
    }

    public void setIdConcello(int idConcello) {
        this.idConcello = idConcello;
    }

    public String getNomeConcello() {
        return nomeConcello;
    }

    public void setNomeConcello(String nomeConcello) {
        this.nomeConcello = nomeConcello;
    }

    public double getSensacionTermica() {
        return sensacionTermica;
    }

    public void setSensacionTermica(double sensacionTermica) {
        this.sensacionTermica = sensacionTermica;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

}
