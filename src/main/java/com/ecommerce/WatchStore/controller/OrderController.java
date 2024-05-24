package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/order")
public class OrderController {
    @GetMapping("")
    public ResponseData<?> getAllOrder() {
        return new ResponseData<>(HttpStatus.OK.value(), "success", "data");
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneOrder(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "OrderID: ", 1);
    }

    @PostMapping("")
    public ResponseData<?> createOrder( @Valid @RequestBody OrderDTO orderDTO) {
        return new ResponseData<>(HttpStatus.OK.value(), "Order added successfully! ", orderDTO.getUserId());
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneOrder(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Order deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneOrder(@Valid @RequestBody OrderDTO orderDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Order updated successfully!");
    }
}
