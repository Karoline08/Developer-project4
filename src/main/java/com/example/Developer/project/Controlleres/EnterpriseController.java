package com.example.Developer.project.Controlleres;


import com.example.Developer.project.Entities.Enterprise;
import com.example.Developer.project.Entities.objectResponse;
import com.example.Developer.project.Services.EnterpriseServices.ServicesEnterpriseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnterpriseController {
    @Autowired
    private ServicesEnterpriseInterface enterpriseService;

    @GetMapping("/enterprises")
    public ResponseEntity<List<Enterprise>> getEnterprises() {
        return new ResponseEntity<>(enterpriseService.getEnterprises(), HttpStatus.OK);
    }

    @GetMapping("/enterprise")
    public ResponseEntity<Object> getEnterprise(@RequestParam String id) {
        try {
            Enterprise enterprise1 = enterpriseService.getEnterprise(id);
            return new ResponseEntity<>(enterprise1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/enterprise/{id}")
    public ResponseEntity<Object> getEnterprisePath(@PathVariable String id) {
        try {
            Enterprise enterprise1 = enterpriseService.getEnterprise(id);
            return new ResponseEntity<>(enterprise1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<String> postEnterprise(@RequestBody Enterprise enterprise_parametro) {
        try {
            String mensaje = enterpriseService.setEnterprise(enterprise_parametro);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/enterprise/{id}")
    public ResponseEntity<objectResponse> putEnterprise(@RequestBody Enterprise enterprise_update, @PathVariable String id) {
        try {
            Enterprise enterprise_bd = enterpriseService.updateEnterpriseAll(enterprise_update, id);
            return new ResponseEntity<>(new objectResponse("Registro de actualizacion exitoso", enterprise_bd), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new objectResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/enterprise/{id}")
    public ResponseEntity<objectResponse> patchEnterprise(@RequestBody Enterprise enterprise_update, @PathVariable String id ){

        try {
            Enterprise enterprise_bd = enterpriseService.updateEnterprise(enterprise_update, id);
            return new ResponseEntity<>(new objectResponse("Actualización Exitosa",enterprise_bd),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new objectResponse(e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/enterprise/{id}")
    public ResponseEntity<objectResponse> deleteEnterprise(@PathVariable String id) {

        try {
            String mensaje = enterpriseService.deleteEnterprise(id);

            return new ResponseEntity<>(new objectResponse(mensaje, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new objectResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}