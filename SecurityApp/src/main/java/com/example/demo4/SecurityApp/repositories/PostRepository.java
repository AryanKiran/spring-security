package com.example.demo4.SecurityApp.repositories;
import com.example.demo4.SecurityApp.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
