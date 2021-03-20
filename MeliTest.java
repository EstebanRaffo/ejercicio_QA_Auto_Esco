import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MeliTest {

    String URL = "https://www.mercadolibre.com.ar";
    public WebDriver driver;
    public WebDriverWait wait;

    String categoria = "";
    String categoria_con_submenu = "";
    String subcategoria = "";
    String subcategoria_de_categoria_con_submenu = "";

    public MeliTest(String categoria_elegida, String subcategoria_elegida, String categoria_con_submenu_elegida, String subcategoria_de_categoria_con_submenu_elegida){
        categoria = categoria_elegida;
        subcategoria = subcategoria_elegida;
        categoria_con_submenu = categoria_con_submenu_elegida;
        subcategoria_de_categoria_con_submenu = subcategoria_de_categoria_con_submenu_elegida;
    }

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(URL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 15);

        // Aceptar Cookies
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("cookieDisclaimerButton"))));
        WebElement cookies_button = driver.findElement(By.id("cookieDisclaimerButton"));
        cookies_button.click();
    }

    @Test
    public void navigationCategoryTest() throws InterruptedException {

        // Desplegar menú
        WebElement categories_menu = driver.findElement(By.linkText("Categorías"));
        categories_menu.click();

        Thread.sleep(3000);
        WebElement menu_down = driver.findElement(By.className("nav-menu-chevron-down"));

        // Seleccionar categoría
        WebElement category = menu_down.findElement(By.xpath("//li/a[contains(text(), '"+categoria+"')]"));
        System.out.println("categoría: " + category.getText());
        category.click();

        // Seleccionar subcategoría
        WebElement subcategory = driver.findElement(By.xpath("//div/h3[contains(text(), '"+subcategoria.toUpperCase()+"')]"));
        System.out.println("subcategoría: " + subcategory.getText());
        subcategory.click();

        // Validaciones
        WebElement subcategory_title = driver.findElement(By.tagName("h1"));
        System.out.println(subcategory_title.getText());
        Assert.assertTrue(subcategory_title.getText().contains(subcategoria));

        WebElement results_text = driver.findElement(By.className("ui-search-search-result"));
        System.out.println(results_text.getText());
        Assert.assertTrue(results_text.getText().contains("resultados"));
    }

    @Test
    public void navigationCategoryWithSubmenuTest(){

        // Desplegar menú
        WebElement categories_menu = driver.findElement(By.linkText("Categorías"));
        categories_menu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nav-menu-chevron-down")));
        WebElement menu_down = driver.findElement(By.className("nav-menu-chevron-down"));

        // Seleccionar categoría
        WebElement category = menu_down.findElement(By.xpath("//li/a[contains(text(), '"+ categoria_con_submenu +"')]"));
        System.out.println("categoría: " + category.getText());
        category.click();

        // Seleccionar subcategoría en submenú de Categoría (dentro de Tecnología ó Juguetes y Bebés)
        WebElement subcategory = driver.findElement(By.linkText(subcategoria_de_categoria_con_submenu));
        System.out.println("subcategoría: " + subcategory.getText());
        subcategory.click();

        // Validaciones
        WebElement subcategory_title = driver.findElement(By.tagName("h1"));
        System.out.println(subcategory_title.getText());
        Assert.assertTrue(subcategory_title.getText().contains(subcategoria_de_categoria_con_submenu));

        WebElement results_text = driver.findElement(By.className("ui-search-search-result"));
        System.out.println(results_text.getText());
        Assert.assertTrue(results_text.getText().contains("resultados"));
    }
}
