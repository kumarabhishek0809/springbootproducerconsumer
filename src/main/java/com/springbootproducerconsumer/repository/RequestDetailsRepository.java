package com.springbootproducerconsumer.repository;

import com.springbootproducerconsumer.entity.RequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long> {

}
