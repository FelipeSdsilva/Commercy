package com.felipesouls.dscommerce.controllers;

import com.felipesouls.dscommerce.dto.UserDTO;
import com.felipesouls.dscommerce.dto.UserInsertDTO;
import com.felipesouls.dscommerce.dto.UserUpdateDTO;
import com.felipesouls.dscommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUserPaginated(Pageable pageable) {
        return ResponseEntity.ok().body(userService.listUserPagined(pageable));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')")
    public ResponseEntity<UserDTO> getUserPerId(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.retriveUserPerId(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')")
    public ResponseEntity<UserDTO> postNewUser(@Valid @RequestBody UserInsertDTO dto) {
        UserDTO insertDTO = userService.insertNewUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(insertDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(insertDTO);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_OPERATOR')")
    public ResponseEntity<UserDTO> putUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok().body(userService.updateUserPerId(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserPerId(@PathVariable Long id) {
        userService.deleteUserPerId(id);
        return ResponseEntity.noContent().build();
    }
}
