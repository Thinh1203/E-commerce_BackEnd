package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/orderDetail")
public class OrderDetailController {
    @GetMapping("/{id}")
    public ResponseData<?> getOrderDetail(@Valid @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "getOrderDetail with id: ", id);
    }

    @GetMapping("/order/{orderId}")
    public ResponseData<?> getOneOrder(@Valid @PathVariable("orderId") Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "getOrderDetail with orderId: ", id);
    }

    @PostMapping("")
    public ResponseData<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Create order detail",
                String.format("NewOrderDetail with price = %.0f, quantity = %d, totalMoney = %.0f ",
                        orderDetailDTO.getPrice(), orderDetailDTO.getQuantity(), orderDetailDTO.getTotalMoney()));
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneOrder(@Valid @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "OrderDetail deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Order updated successfully!",
                String.format("UpdateOrderDetail with price = %.0f, quantity = %d, totalMoney = %.0f ",
                        orderDetailDTO.getPrice(), orderDetailDTO.getQuantity(), orderDetailDTO.getTotalMoney()));
    }
}
