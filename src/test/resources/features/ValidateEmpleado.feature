Feature: Validacion de crud para empleados

  @GET
  Scenario: Se consultan todos Empleados existentes
    Given Existen empleados en la aplicacion
    Then  El servicio responde satisfactoriamente

  @POST
  Scenario: Se registra un empleado
    Given Se consulta el servicio de creacion de empleados
    Then  El usuario se creo correctamente

  @GET
  Scenario: Se consulta un empleado especifico
    Given Se consulta el servicio de empleados con un usario especifico
    Then  Se obtiene el empleado


  @DELETE
  Scenario: Se elimina un usuario
    Given Existen empleados en la aplicacion
    When  Se consulta el servicio para eliminar empleados
    Then  Se elimino el usuario

  @UPDATE
  Scenario: Se actualiza un usuario
    Given Existen empleados en la aplicacion
    When  Se acutaliza un empleado especifico
    Then  Se actualizo correctamente