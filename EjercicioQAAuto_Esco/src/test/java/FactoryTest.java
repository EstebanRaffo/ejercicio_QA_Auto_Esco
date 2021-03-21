import org.testng.annotations.Factory;

public class FactoryTest {

    @Factory
    public Object[] MeliFactoryTest(){
        return new Object[]{
                new MercadoLibreTest("Electrodomésticos", "Ventiladores", "Tecnología", "Celulares y Smartphones"),
                new MercadoLibreTest("Hogar y Muebles", "Muebles", "Juguetes y Bebés", "Cotillón")
        };
    }
}


/*
*  Se ejecuta el test desde esta factory donde se ingresan en cada instancia de Test las categorías y
*  subcategorías elegidas. Son 4 tests.
*  Deben escribirse tal como aparecen en el sitio web.
* */
