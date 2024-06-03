package com.base.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerTasks {

    @EmbeddedId
    private CustomerTasksId id;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "fk_task_id")
    private Task task;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CustomerTasksId {
        @Column(name = "customer_id")
        private Integer customerId;
        @Column(name = "task_id")
        private Integer taskId;
    }

}
