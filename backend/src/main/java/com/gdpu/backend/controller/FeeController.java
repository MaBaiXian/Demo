package com.gdpu.backend.controller;

import com.gdpu.backend.dto.Result;
import com.gdpu.backend.entity.Fee;
import com.gdpu.backend.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/fees")
public class FeeController {
    @Autowired
    private FeeService feeService;

    @GetMapping
    public ResponseEntity<List<Fee>> getAllFees() {
        List<Fee> fees = feeService.getAllFees();
        return new ResponseEntity<>(fees, HttpStatus.OK);
    }

    @GetMapping("/{stu_id}")
    public ResponseEntity<Fee> getFeeByStuId(@PathVariable String stu_id) {
        Fee fee = feeService.getFeeByStuId(stu_id);
        if(fee != null) {
            return new ResponseEntity<>(fee,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Result addFee(@RequestBody Fee fee) {
        if (feeService.addFee(fee)) {
            return new Result(20000,"Fee added successfully");
        }
        return new Result(400,"Failed to add fee");
    }

    @PutMapping
    public Result updateFee(@RequestBody Fee fee) {
        if (feeService.updateFee(fee)) {
            return new Result(20000,"Fee updated successfully");
        }
        return new Result(400,"Failed to update fee");
    }

    @DeleteMapping("/deleteById/{stu_id}")
    public Result deleteFeeByStuId(@PathVariable String stu_id) {
        if (feeService.deleteFeeByStuId(stu_id)) {
            return new Result(20000,"Fee deleted successfully");
        }
        return new Result(400,"Failed to delete fee");
    }
}
