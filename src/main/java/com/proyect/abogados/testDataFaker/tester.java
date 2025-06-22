package com.proyect.abogados.testDataFaker;

import com.proyect.abogados.model.Cliente;
import com.proyect.abogados.model.Abogado;
import com.proyect.abogados.service.ClienteService;
import com.proyect.abogados.service.AbogadoService;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class tester implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AbogadoService abogadoService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Generar y guardar 5 clientes falsos
        for (int i = 0; i < 5; i++) {
            Cliente cliente = new Cliente();
            cliente.setId(faker.idNumber().valid());
            cliente.setRut(faker.idNumber().valid());
            cliente.setPNombre(faker.name().firstName());
            cliente.setSNombre(faker.name().firstName());
            cliente.setPApellido(faker.name().lastName());
            cliente.setSApellido(faker.name().lastName());
            cliente.setDocumento(faker.idNumber().valid());
            cliente.setDireccion(faker.address().fullAddress());
            cliente.setTelefono(faker.phoneNumber().cellPhone());
            cliente.setEmail(faker.internet().emailAddress());

            clienteService.crearCliente(cliente); // Guarda en la base de datos
        }

        // Generar y guardar 5 abogados falsos
        for (int i = 0; i < 5; i++) {
            Abogado abogado = new Abogado();
            abogado.setId(faker.idNumber().valid());
            abogado.setRut(faker.idNumber().valid());
            abogado.setPNombre(faker.name().firstName());
            abogado.setSNombre(faker.name().firstName());
            abogado.setPApellido(faker.name().lastName());
            abogado.setSApellido(faker.name().lastName());
            abogado.setDocumento(faker.idNumber().valid());
            abogado.setEspecialidad(faker.job().field());
            abogado.setUniversidad(faker.university().name());
            abogado.setLicencia(faker.idNumber().valid());
            abogado.setAniosExperiencia(faker.number().numberBetween(1, 40));

            abogadoService.crearAbogado(abogado); // Guarda en la base de datos
        }
    }
}