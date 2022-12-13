package BasedeDatosCrud.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.service.OrganizacionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListOrganizaciones {

    @Mock
    private OrganizacionRepository organizacionRepository;

    @InjectMocks
    private OrganizacionService organizacionService;

    @Test
    public void obtenerTodasLasOrganizaciones() {
        List<Organizacion> organizaciones = new ArrayList();
        organizaciones.add(new Organizacion());

        given(organizacionRepository.findAll()).willReturn(organizaciones);

        List<Organizacion> expected = organizacionService.getOrganizaciones();

        assertEquals(expected, organizaciones);
        verify(organizacionRepository).findAll();
    }


}