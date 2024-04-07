package com.felipesouls.dscommerce.controllers;

import com.felipesouls.dscommerce.dto.OrderDTO;
import com.felipesouls.dscommerce.records.OrderItemRecord;
import com.felipesouls.dscommerce.records.OrderMinRecord;
import com.felipesouls.dscommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<OrderMinRecord>> getAllOrdersPaginated(Pageable pageable) {
        return ResponseEntity.ok(orderService.paginatedAllOrders(pageable));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderDTO> getOrderPerId(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findOrderPerId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<OrderDTO> postNewOrder(@Valid @RequestBody OrderItemRecord items) {
        OrderDTO newOrderDTO = orderService.createAnewOrder(items);
        var uri = fromCurrentRequest().path("/{id}").buildAndExpand(newOrderDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newOrderDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> putOrderPerId(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.updateOrderPerId(id, orderDTO));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderPerId(id);
        return ResponseEntity.noContent().build();
    }
}
