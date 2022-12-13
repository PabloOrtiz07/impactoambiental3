package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;

@Component
public class Calendario {


    public static LocalDate  crearFecha(int mes, int anio) {
        return LocalDate.of(anio,mes,1);
    }

   public static Calendar sinFecha() {
        return null;
    }

    public static Calendar crearAnio(int anio) {
        Calendar anioC = Calendar.getInstance();
        anioC.add(Calendar.YEAR, anio);
        return anioC;
    }

    public static LocalDate fechaActual() {
        return LocalDate.now();
    }

    public static int mesesEntreMedio(LocalDate inicioCalculo, LocalDate finCalculo, LocalDate inicioRecorrido, LocalDate finRecorrido) {
        LocalDate inicio;
        LocalDate fin;

        if(finRecorrido == null) {
            finRecorrido = fechaActual();
        }
        //TODO revisar
        if(inicioCalculo.isBefore(finRecorrido) && finCalculo.isAfter(inicioRecorrido)) {
            if(inicioCalculo.isBefore(finRecorrido)) {
                inicio = inicioCalculo;
            } else {
                inicio = inicioRecorrido;
            }

            if(finCalculo.isAfter(finRecorrido)) {
                fin = finCalculo;
            } else {
                fin = finRecorrido;
            }

            int diferenciaAnios = fin.getYear() - inicio.getYear();
            return diferenciaAnios * 12 + (fin.getMonthValue() - inicio.getMonthValue());
        } else {
            return 0;
        }
    }

    public Calendario() {
    }
}
