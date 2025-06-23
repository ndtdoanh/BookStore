package com.ndtdoanh.file.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ndtdoanh.file.entity.FileMgmt;

@Repository
public interface FileMgmtRepository extends MongoRepository<FileMgmt, String> {}
