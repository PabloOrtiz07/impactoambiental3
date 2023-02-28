package BasedeDatosCrud.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.repository.OrganizacionRepository;
import heroku.huelladecarbono.service.OrganizacionService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteOrganizacion {

        @Mock
        private OrganizacionRepository organizacionRepository;

        @InjectMocks
        private OrganizacionService organizacionService;

        @Test
        public void borrarOrganizacion(){
            Organizacion organizacion = new Organizacion();
            organizacion.setRazonSocial("SA");
            organizacion.setId(1);

            when(organizacionRepository.findById(organizacion.getId())).thenReturn(Optional.of(organizacion));

            organizacionService.deleteOrganizacion(organizacion.getId());
            verify(organizacionRepository).findById(organizacion.getId());
        }

        @Test(expected = RuntimeException.class)
        public void should_throw_exception_when_user_doesnt_exist() {
            Organizacion organizacion = new Organizacion();
            organizacion.setId(2);
            organizacion.setRazonSocial("SA");

            given(organizacionRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));
            organizacionService.deleteOrganizacion(organizacion.getId());
        }
    }



