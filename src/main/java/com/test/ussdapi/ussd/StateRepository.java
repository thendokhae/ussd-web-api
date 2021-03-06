package com.test.ussdapi.ussd;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StateRepository extends CrudRepository<State, Long> {

    State findStateByStateKey(String key);
}
