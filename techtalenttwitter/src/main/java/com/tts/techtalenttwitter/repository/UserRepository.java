package com.tts.techtalenttwitter.repository;

import com.tts.techtalenttwitter.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tts.techtalenttwitter.repository.RoleRepository;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    User findByUsername(String username);

}
