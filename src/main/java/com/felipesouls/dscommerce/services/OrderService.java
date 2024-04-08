package com.felipesouls.dscommerce.services;

import com.felipesouls.dscommerce.dto.OrderDTO;
import com.felipesouls.dscommerce.dto.OrderItemDTO;
import com.felipesouls.dscommerce.entities.Order;
import com.felipesouls.dscommerce.entities.OrderItem;
import com.felipesouls.dscommerce.entities.Product;
import com.felipesouls.dscommerce.entities.User;
import com.felipesouls.dscommerce.entities.enums.OrderStatus;
import com.felipesouls.dscommerce.records.OrderItemRecord;
import com.felipesouls.dscommerce.records.OrderMinRecord;
import com.felipesouls.dscommerce.repositories.OrderItemRepository;
import com.felipesouls.dscommerce.repositories.OrderRepository;
import com.felipesouls.dscommerce.repositories.ProductRepository;
import com.felipesouls.dscommerce.services.exceptions.DatabaseException;
import com.felipesouls.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<OrderMinRecord> paginatedAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(order -> new OrderMinRecord(order.getId(),
                order.getMoment(), order.getStatus(), order.getClient().getName(), order.getTotalOrder()));
    }

    @Transactional(readOnly = true)
    public OrderDTO findOrderPerId(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order: " + id + " not found"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO createAnewOrder(OrderItemRecord record) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User client = userService.authenticate();

        order.setClient(client);

        for (OrderItemDTO itemDto : record.items()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());

            order.getItems().add(item);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO updateOrderPerId(@SuppressWarnings("unused") Long id, @SuppressWarnings("unused") OrderDTO orderDTO) {
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteOrderPerId(Long id) {
        if (!orderRepository.existsById(id))
            throw new ResourceNotFoundException("Order: " + id + " not found!");
        try {
            orderRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrate violation!");
        }
    }
}
