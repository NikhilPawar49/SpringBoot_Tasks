package com.example.SpringBatch.config;

import com.example.SpringBatch.entity.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Employee> employeeReader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeReader")
                .resource(new ClassPathResource("employees.csv"))
                .delimited()
                .names("name", "age", "salary")
                .linesToSkip(1)
                .targetType(Employee.class)
                .build();
    }

    @Bean
    public ItemProcessor<Employee, Employee> employeeProcessor() {
        return employee -> {
            employee.setName(employee.getName().toUpperCase());
            return employee;
        };
    }

    @Bean
    public JdbcBatchItemWriter<Employee> employeeWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .dataSource(dataSource)
                .sql("INSERT INTO employee (name, age, salary) VALUES (:name, :age, :salary)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      JdbcBatchItemWriter<Employee> writer) {

        return new StepBuilder("step1", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager)
                .reader(employeeReader())
                .processor(employeeProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    Job importEmployeeJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importEmployeeJob", jobRepository)
                .start(step1)
                .build();
    }
}
