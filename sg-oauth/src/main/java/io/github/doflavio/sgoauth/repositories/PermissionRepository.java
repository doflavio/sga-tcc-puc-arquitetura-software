package io.github.doflavio.sgoauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgoauth.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {}