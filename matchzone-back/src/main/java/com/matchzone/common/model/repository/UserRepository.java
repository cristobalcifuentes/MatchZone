package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Comprueba si existe un usuario con el email dado.
     */
    boolean existsByEmail(String email);

    /**
     * Busca un usuario por su email.
     */
    Optional<User> findByEmail(String email);

    /**
     * Devuelve la cantidad de usuarios que comparten el mismo usernameVisible.
     * Se utiliza para generar un sufijo único para usernameInternal.
     */
    int countByUsernameVisible(String usernameVisible);

    /**
     * Busca un usuario por su usernameInternal.
     */
    Optional<User> findByUsernameInternal(String usernameInternal);

    /**
     * Comprueba si un usernameInternal ya existe.
     * Se usa en generateUsernameInternal() para evitar colisiones.
     */
    boolean existsByUsernameInternal(String usernameInternal);
}