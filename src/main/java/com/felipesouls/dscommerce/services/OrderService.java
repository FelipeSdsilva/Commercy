package com.felipesouls.dscommerce.services;

import com.felipesouls.dscommerce.dto.OrderDTO;
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

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<OrderDTO> paginatedAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(order -> new OrderDTO(order, order.getItems()));
    }

    @Transactional(readOnly = true)
    public OrderDTO findOrderPerId(Long id) {
        return new OrderDTO(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order: " + id + " not found")));
    }

    @SuppressWarnings("unused")
    @Transactional
    public OrderDTO createAnewOrder(OrderDTO orderDTO) {
        return null;
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
