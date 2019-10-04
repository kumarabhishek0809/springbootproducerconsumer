package com.springbootproducerconsumer.repository;

import com.springbootproducerconsumer.entity.CounterValue;
import com.springbootproducerconsumer.entity.RequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterValueRepository extends JpaRepository<CounterValue, Long> {

}
