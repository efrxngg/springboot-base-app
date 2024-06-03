package com.base.app.dto;

import com.base.app.entity.Task;

import java.io.Serializable;

/**
 * DTO for {@link Task}
 */
public record CreateTaskDto(String description) implements Serializable {
}