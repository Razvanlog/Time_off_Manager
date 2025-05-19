package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Objects;

public class CreateTimeOffRequest {

    @NotNull
    private Long userId;

    @NotNull
    @FutureOrPresent
    private LocalDate start;

    @NotNull
    @FutureOrPresent
    private LocalDate end;

    @NotBlank
    private String leaveType;

    private String description;

    @NotNull
    @Positive
    private Integer requestedDays;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRequestedDays() {
        return requestedDays;
    }

    public void setRequestedDays(Integer requestedDays) {
        this.requestedDays = requestedDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTimeOffRequest that = (CreateTimeOffRequest) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(leaveType, that.leaveType) &&
                Objects.equals(description, that.description) &&
                Objects.equals(requestedDays, that.requestedDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, start, end, leaveType, description, requestedDays);
    }
}