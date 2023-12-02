package com.felipesouls.dscommerce.dto.pks;

import com.felipesouls.dscommerce.dto.OrderDTO;
import com.felipesouls.dscommerce.dto.ProductDTO;

public class OrderItemPKDTO {

    private OrderDTO orderDTO;
    private ProductDTO productDTO;

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
