package BasedeDatosCrud.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.repository.OrganizacionRepository;
import heroku.huelladecarbono.service.OrganizacionService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CreateOrganizacion {

        @Mock
        private OrganizacionRepository organizacionRepository;

        @InjectMocks
        private OrganizacionService organizacionService;

        @Test
        public void crearOrganizacion() {
            Organizacion organizacion = new Organizacion();
            organizacion.setRazonSocial("S.A");

            when(organizacionRepository.save(ArgumentMatchers.any(Organizacion.class))).thenReturn(organizacion);

            Organizacion organizacionCreada = organizacionService.crearOrganizacion(organizacion);
         organizacionCreada.getRazonSocial().contentEquals(organizacion.getRazonSocial());
            verify(organizacionRepository).save(organizacion);
        }


}
