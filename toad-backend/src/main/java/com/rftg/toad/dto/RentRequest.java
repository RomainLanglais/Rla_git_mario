package com.rftg.toad.dto;

public class RentRequest {
    private Integer filmId;
    private Integer customerId;
    private Integer staffId;

    public RentRequest() {
    }

    public RentRequest(Integer filmId, Integer customerId, Integer staffId) {
        this.filmId = filmId;
        this.customerId = customerId;
        this.staffId = staffId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
