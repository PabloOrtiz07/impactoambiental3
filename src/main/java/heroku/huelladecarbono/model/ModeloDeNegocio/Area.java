package heroku.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.Setter;
import heroku.huelladecarbono.service.CargaDeMedicionesService.CargaDeMediciones;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table (name="area")
public class Area {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private  List<Miembro> miembros = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private  List<Miembro> miembrosEnEspera = new ArrayList<>();
    @ManyToOne
    @JoinColumn (name="organizacion_id",referencedColumnName = "id")
    private Organizacion organizacion;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private  List<ListaDeDatosDeMedicion> mediciones = new ArrayList<>();

    //Hay que replicar esto en todos los arraylist para el many to many
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HuellaCarbono> huellasCarbono = new ArrayList<>();
    @Column
    private Double huellaCarbono;
    @Column
    private Double hcPromedio;
    @Column
    private boolean estaActivo;


    public Area() {
    }

    public Area(String nombre, Organizacion organizacion, List<ListaDeDatosDeMedicion> mediciones) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.mediciones = mediciones;
        this.estaActivo = true;
    }

    public Area(String nombre, Organizacion organizacion) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.estaActivo = true;
        organizacion.setArea(this);
    }

    public void agregarHuella(HuellaCarbono huella){
        this.huellasCarbono.add(huella);
    }

    public void cargarMediciones(String DireccionExcel) {
        String filePath = DireccionExcel;
        CargaDeMediciones cargaMediciones = new CargaDeMediciones();
        cargaMediciones.useExistingWorkbook(filePath);
        ListaDeDatosDeMedicion nuevosDatosDeMedicion = null;
        nuevosDatosDeMedicion.setDatosDeMedicion(cargaMediciones.lecturaArchivo(0));
        mediciones.add(nuevosDatosDeMedicion);
    }

    public void addMedicion(ListaDeDatosDeMedicion mediciones){
        this.mediciones.add(mediciones);
    }

    public void addMiembro(Miembro miembro){
        miembros.add(miembro);
    }

    public void rechazarMiembro(Miembro miembro) {
        miembrosEnEspera.remove(miembro);
    }

    public void aceptarMiembro(Miembro miembro) throws Exception {
        Miembro unMiembro = miembrosEnEspera.get(miembrosEnEspera.indexOf(miembro));
        if (unMiembro != null) {
            miembrosEnEspera.remove(unMiembro);
            miembros.add(unMiembro);
        } else {
            throw new Exception("El miembro no estaba en espera");
        }
    }

    public void solicitarSerParte(Miembro miembro) {
        this.miembrosEnEspera.add(miembro);
    }
    public List<Miembro> getMiembrosEnEspera() {
        return miembrosEnEspera;
    }
    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void nullearOrg(){this.organizacion = null;}

    public boolean getEstaActivo() {
        return this.estaActivo;
    }

    public void desvincularMiembro(Integer miembroId) {
       Miembro miembroF = miembros.stream().filter(m -> m.getID().equals(miembroId)).findFirst().orElse(null);
       miembros.remove(miembroF);
    }

    public void cargarDatosDeActividad() {
        this.mediciones.add(new ListaDeDatosDeMedicion());
    }

    public Double getHCMediciones() {
        return mediciones.size() * Math.random();
    }

    public void limpiarMediciones() {
        mediciones.clear();
    }
}