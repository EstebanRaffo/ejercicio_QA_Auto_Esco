import org.testng.annotations.Factory;

public class FactoryTest {

    @Factory
    public Object[] MeliFactoryTest(){
        return new Object[]{
                new MeliTest("Electrodomésticos", "Ventiladores", "Tecnología", "Celulares y Smartphones"),
                new MeliTest("Hogar y Muebles", "Muebles", "Juguetes y Bebés", "Cotillón")
        };
    }
}


/* Se ejecuta desde esta factory donde se ingresan en cada instancia de Test las categorías y subcategorías elegidas.
*  Deben escribirse tal como aparecen en el sitio web */
