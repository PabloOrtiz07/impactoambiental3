package BasedeDatosCrud.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.repository.OrganizacionRepository;
import heroku.huelladecarbono.service.OrganizacionService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UpdateOrganizacion {

        @Mock
        private OrganizacionRepository organizacionRepository;

        @InjectMocks
        private OrganizacionService organizacionService;

        @Test
        public void modificarOrganizacion() {
            Organizacion organizacion= new Organizacion();
            organizacion.setId(8);
            organizacion.setRazonSocial("SA");

            Organizacion nuevaOrganizacion = new Organizacion();
            organizacion.setRazonSocial("Nuevo SA");


            given(organizacionRepository.findById(organizacion.getId())).willReturn(Optional.of(organizacion));
            organizacionService.modificarOrganizacion(organizacion.getId(), nuevaOrganizacion);

            verify(organizacionRepository).save(organizacion);
           verify(organizacionRepository).findById(organizacion.getId());
        }


}
