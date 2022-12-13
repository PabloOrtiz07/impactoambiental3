package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Getter @Setter
@Entity
@Table(name = "miembro")
public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String tipoDoc;
    @Column
    private Integer numDoc;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Area> areas = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Recorrido> recorridos;
    @Column
    private String mail;
    @Column
    private String telefono;
    @Column
    private Double huellaCarbono;
    @Column
    private Double impactoIndividual;
    @Column
    private Boolean estaActivo;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HuellaCarbono> huellasCarbono = new ArrayList<>();

    public Miembro(String nom, String ape, String tipoDocu, int numeroDoc, List<Area> listaAreas,
                   List<Recorrido> recorridos) {
        this.nombre = nom;
        this.apellido = ape;
        this.tipoDoc = tipoDocu;
        this.numDoc = numeroDoc;
        this.areas = listaAreas;

        areas.forEach(area -> area.addMiembro(this));

        AtomicReference<Double> pesoTotal = new AtomicReference<>(Double.valueOf(0));
        recorridos.forEach( recorrido -> pesoTotal.updateAndGet(v -> v + recorrido.getPeso()));
        if(pesoTotal.get() != 1) {
            throw new RuntimeException("Peso inválido en los recorrridos");
        }
        this.recorridos = recorridos;
        RepositorioMiembros.getRepositorio().agregarMiembro(this);
    }

    public Miembro(Integer id, String nombre, String apellido, String mail, String telefono,Boolean estaActivo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.estaActivo = estaActivo;
    }

    public Miembro( Area area, String nombre, String apellido, String mail, String telefono,Boolean estaActivo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.estaActivo = estaActivo;
        this.areas = new ArrayList<>();
        this.areas.add(area);
    }

    public Miembro() {}

    public Miembro(Integer id, String nombre, String apellido, String tipoDoc, Integer numDoc, List<Area> areas, List<Recorrido> recorridos, String mail, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.areas = areas;
        this.recorridos = recorridos;
        this.mail = mail;
        this.telefono = telefono;
    }

    public Miembro(String nombre, String apellido, String tipoDoc, Integer numDoc, String mail, String telefono, Boolean estaActivo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.mail = mail;
        this.telefono = telefono;
        this.estaActivo = estaActivo;
    }

    @Override
    public String toString() {
        return "Miembro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tipoDoc='" + tipoDoc + '\'' +
                ", numDoc=" + numDoc +
                ", mail='" + mail + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estaActivo=" + estaActivo +
                '}';
    }

    public Integer getID() {return this.id;}
    public void solicitarSerParte(Area area) {
        area.solicitarSerParte(this);
    }
    public void recibirAceptacion(Area area) {
        this.areas.add(area);
    }

    public void setHC(Double valor){
        this.huellaCarbono = valor;
    }

    public void addRecorrido(Recorrido recorrido){
        recorridos.add(recorrido);
    }

    public void addRecorrido(ArrayList<Recorrido> recorridos){
        recorridos.addAll(recorridos);
    }

    public void setImpacto(Double valor){
        this.impactoIndividual = valor;
    }

    public void agregarHuella(HuellaCarbono huella){
        this.huellasCarbono.add(huella);
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public List<Organizacion> getOrganizaciones(){
        List organizaciones = areas.stream()
                .map(area -> area.getOrganizacion())
                .collect(Collectors.toList());
        return new ArrayList<>(new HashSet<>(organizaciones));

    }

    public void eliminarArea(Integer areaId) {
        Area areaF = areas.stream()
                .filter(area -> area.getId().equals(areaId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Area no encontrada"));
        areas.remove(areaF);
    }

    public void eliminarRecorrido(Recorrido recorrido) {
        recorridos.remove(recorrido);
    }

    public Boolean esDeUnaOrganizacion(Integer organizacionId) {
        return areas.stream()
                .anyMatch(area -> area.getOrganizacion().getId().equals(organizacionId));
    }

    public Boolean estaEnEspera(Integer organizacionId) {
        return areas.stream().anyMatch(area -> area.getOrganizacion().getId().equals(organizacionId) && area.getMiembrosEnEspera().stream().anyMatch(miembro -> miembro.getId().equals(this.id)));
    }

    private Integer getId() {
        return id;
    }

}
