package io.github.doflavio.sgoauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgoauth.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}