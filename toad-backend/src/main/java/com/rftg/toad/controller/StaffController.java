package com.rftg.toad.controller;

import com.rftg.toad.model.Staff;
import com.rftg.toad.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Integer id) {
        return staffService.getStaffById(id);
    }

    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        return staffService.saveStaff(staff);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Integer id, @RequestBody Staff staff) {
        staff.setStaffId(id);
        return staffService.saveStaff(staff);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Integer id) {
        staffService.deleteStaff(id);
    }
}
