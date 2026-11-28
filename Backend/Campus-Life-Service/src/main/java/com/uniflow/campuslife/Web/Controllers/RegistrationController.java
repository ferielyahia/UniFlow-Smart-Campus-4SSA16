package com.uniflow.campuslife.Web.Controllers;

import com.uniflow.campuslife.Domain.Registration;
import com.uniflow.campuslife.Services.IRegistrationServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("registrations")
@Tag(name = "Registrations", description = "Gestion des inscriptions aux événements")
public class RegistrationController {
    private final IRegistrationServices iRegistrationServices;

    @Operation(summary = "Add : Registration")
    @ApiResponse(responseCode = "200", description = "Registration Added")
    @PostMapping("add/{idEvent}")
    Registration createRegistration(@RequestBody Registration registration, @PathVariable Long idEvent) {
        return iRegistrationServices.addRegistration(registration, idEvent);
    }

    @Operation(summary = "Show : Registration")
    @ApiResponse(responseCode = "200", description = "Registration Listed")
    @GetMapping("{id}")
    Registration afficherRegistration(@PathVariable Long id) {
        return iRegistrationServices.getRegistration(id);
    }

    @Operation(summary = "Update : Registration")
    @ApiResponse(responseCode = "200", description = "Registration Updated")
    @PutMapping("update/{id}")
    Registration modifierRegistration(@PathVariable Long id, @RequestBody Registration registration) {
        return iRegistrationServices.updateRegistration(id, registration);
    }

    @Operation(summary = "Delete : Registration")
    @ApiResponse(responseCode = "200", description = "Registration Deleted")
    @DeleteMapping("{id}")
    void supprimerRegistration(@PathVariable Long id) {
        iRegistrationServices.deleteRegistration(id);
    }

}
