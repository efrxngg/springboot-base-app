package com.base.app;

import com.base.app.dto.*;
import com.base.app.entity.Customer;
import com.base.app.entity.CustomerTasks;
import com.base.app.entity.Task;
import com.base.app.repository.CustomerRepository;
import com.base.app.repository.CustomerTasksRepository;
import com.base.app.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppRestController {

    private final CustomerRepository customerRepository;
    private final TaskRepository taskRepository;
    private final CustomerTasksRepository customerTasksRepository;

    @GetMapping("customerTasks")
    public List<CustomerTasks> tasks() {
        return customerTasksRepository.findAll();
    }

    @PostMapping("tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskDto taskDto) {
        return ResponseEntity.ok().body(persistTask(taskDto));
    }

    @PostMapping("customer")
    public ResponseEntity<CustomerDto> createTask(@RequestBody CreateCustomerDto customerDto) {
        return ResponseEntity.ok().body(persistCustomer(customerDto));
    }

    @PostMapping("assign-tasks")
    public ResponseEntity<CustomerTasks> assignTasks(@RequestBody AssignTasksDto assignTasksDto) {
        System.out.println("assignTasksDto = " + assignTasksDto);
        return ResponseEntity.ok().body(persistAssignTasks(assignTasksDto));
    }

    @Transactional
    protected CustomerTasks persistAssignTasks(AssignTasksDto assignTasksDto) {
        return customerTasksRepository.save(
                CustomerTasks.builder()
                        .id(new CustomerTasks.CustomerTasksId(
                                assignTasksDto.customerId(),
                                assignTasksDto.taskId()
                        ))
                        .build());
    }


    @Transactional
    public TaskDto persistTask(CreateTaskDto taskDto) {
        Task task = Task.builder().description(taskDto.description()).build();
        Task save = taskRepository.save(task);
        return new TaskDto(save.getId(), save.getDescription());
    }

    @Transactional
    public CustomerDto persistCustomer(CreateCustomerDto customerDto) {
        Customer customer = Customer.builder().name(customerDto.name()).build();
        Customer save = customerRepository.save(customer);
        return new CustomerDto(save.getId(), save.getName());
    }

//    @Transactional
//    public AssignTasksDto persistAssignTasks(AssignTasksDto assignTasksDto) {
//        Customer customer = customerRepository.findById(assignTasksDto.customerId()).orElseThrow();
//        Task task = taskRepository.findById(assignTasksDto.taskId()).orElseThrow();
//        Set<Customer> customers = task.getCustomers();
//        customers.add(customer);
//        task.setCustomers(customers);
//        taskRepository.save(task);
//        return assignTasksDto;
//    }


}
