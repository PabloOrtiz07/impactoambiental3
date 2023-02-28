package APIMedicionesTests;

import heroku.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import heroku.huelladecarbono.service.CargaDeMedicionesService.CargaDeMediciones;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class PruebaLecturaArchivo {
    @Test
    public void testCargarDatosALista() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones1.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(1400.0,listaDatoDeMedicion.get(2).getValor());
    }

    @Test
    public void testCargarDatosALista2() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones2.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(750.0,listaDatoDeMedicion.get(3).getValor());
    }

    @Test
    public void testCargarDatosALista3() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones3.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(1564.0,listaDatoDeMedicion.get(2).getValor());
    }

    @Test
    public void testCargarDatosALista4() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones4.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(750.0,listaDatoDeMedicion.get(2).getValor());
    }

    @Test
    public void testCargarDatosALista5() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones5.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals(1450.0,listaDatoDeMedicion.get(2).getValor());
    }

    @Test
    public void testCargarDatosAListaPrueba() {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones2.xlsx";
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> listaDatoDeMedicion = cargaMediciones.lecturaArchivo(0);
        Assert.assertEquals("Mensual",listaDatoDeMedicion.get(2).getPeriodicidad());
    }
}










