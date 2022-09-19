package steps;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.Empleado;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import org.apache.hc.core5.http.HttpStatus;
import org.json.JSONObject;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class ValidateEmpleado {

    private Actor mango;

    private Empleado empleado;

    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;
    public Response response;

    public ValidateEmpleado (){
        this.empleado = new Empleado();
    }

    @Before
    public void configureBaseUrl() {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("http://dummy.restapiexample.com/api/v1/");
        mango = Actor.named("Mango supervisor").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }
    @Given("Existen empleados en la aplicacion")
    public void existenEmpleadosEnLaAplicacion() {
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(theRestApiBaseUrl+"employees");
    }
    @Then("El servicio responde satisfactoriamente")
    public void elServicioRespondeSatisfactoriamente() {
        mango.should(seeThatResponse("All empleados returned",response -> response.statusCode(200)));
    }

    @Given("Se consulta el servicio de creacion de empleados")
    public void seConsultaElServicioDeCreacionDeEmpleados() {

        empleado.setId(26);
        empleado.setEmployee_name("CR7");
        empleado.setEmployee_age(36);
        empleado.setEmployee_salary(5.0000000);
        empleado.setProfile_image("");

        JSONObject obj = new JSONObject(empleado);
        System.out.println(obj);
        System.out.println(obj.toString());
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().body(obj.toString()).post(theRestApiBaseUrl+"create");
    }

    @Then("El usuario se creo correctamente")
    public void elUsuarioSeCreoCorrectamente() {
        mango.should(
                seeThatResponse( "User create correct",
                        response -> response.statusCode(200))
        );
    }

    @Given("Se consulta el servicio de empleados con un usario especifico")
    public void seConsultaElServicioDeEmpleadosConUnUsarioEspecifico() {
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(theRestApiBaseUrl+"employee/1");
    }

    @Then("Se obtiene el empleado")
    public void seObtieneElEmpleado() {
        mango.should(
                seeThatResponse( "User details should be correct",
                        response -> response.statusCode(200)
                                .body("data.employee_name", equalTo("Tiger Nixon"))
                                .body("data.id", equalTo(1))
                )
        );
    }

    @When("Se consulta el servicio para eliminar empleados")
    public void seConsultaElServicioParaEliminarEmpleados() {
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(theRestApiBaseUrl+"delete/19");

    }

    @Then("Se elimino el usuario")
    public void seEliminoElUsuario() {
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(theRestApiBaseUrl+"employee/18");
    }

    @When("Se acutaliza un empleado especifico")
    public void seAcutalizaUnEmpleadoEspecifico() {

        empleado.setId(25);
        empleado.setEmployee_name("Messi");
        empleado.setEmployee_age(35);
        empleado.setEmployee_salary(4.0000000);
        empleado.setProfile_image("");

        JSONObject obj = new JSONObject(empleado);
        System.out.println(obj);
        System.out.println(obj.toString());
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().body(obj.toString()).post(theRestApiBaseUrl+"update/24");

    }

    @Then("Se actualizo correctamente")
    public void seActualizoCorrectamente() {
        mango.should(
                seeThatResponse( "User details should be correct",
                        response -> response.statusCode(200)
                                .body("data.employee_name", equalTo("Messi"))
                                .body("data.id", equalTo(24))
                )
        );
    }
}
