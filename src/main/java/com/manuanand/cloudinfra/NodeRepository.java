package com.manuanand.cloudinfra;

import org.springframework.data.repository.CrudRepository;

import com.manuanand.cloudinfra.Node;

// This will be AUTO IMPLEMENTED by Spring into a Bean called nodeRepository
// CRUD refers Create, Read, Update, Delete

public interface NodeRepository extends CrudRepository<Node, Integer> {

}
