package ContraseñasTest;

import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.model.UserExceptions.EasyPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ContraseñasTest {
    /*
        @Test
        public void testCrearMiembroPasswordFacil() {

            Assertions.assertThrows(EasyPasswordException.class,()-> new Usuario("prueba","password", Arrays.asList(new Rol("ROLE_USER"))));
        }

        @Test
        public void testCrearMiembroPasswordSegura() throws NoSuchAlgorithmException {
            Usuario usuarioPrueba = new Usuario("prueba","Yagni3210+",Arrays.asList(new Rol("ROLE_USER")));
            Assertions.assertEquals(usuarioPrueba.getNombre(),"prueba");
        }

        @Test
        public void testCrearMiembroPasswordNoCumpleRequisitos() {
            Assertions.assertThrows(EasyPasswordException.class,()-> new Usuario("prueba","asdfr4",Arrays.asList(new Rol("ROLE_USER"))));
        }

        @Test
        public void testTiempoEsperaLogueo() throws InterruptedException {
            Usuario usuarioPrueba = new Usuario("prueba","Yagni3210+",Arrays.asList(new Rol("ROLE_USER")));
            usuarioPrueba.validarLogueo("asasasas");
            usuarioPrueba.validarLogueo("asasasas");
            usuarioPrueba.validarLogueo("asasasas");
            double res = Math.pow(2,usuarioPrueba.getCantIntentos());
            Assertions.assertEquals(8,res);
        }*/
}
