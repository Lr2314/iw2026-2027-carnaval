package com.iwcarnaval.usuario;

import com.iwcarnaval.common.EntidadBase;
import com.iwcarnaval.common.Email;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import static java.util.Objects.requireNonNull;

/**
 * Entidad: cuenta de acceso al sistema.
 *
 * <p>Guarda el <b>hash</b> de la contraseña (BCrypt), nunca la contraseña. Los setters validan
 * y el constructor los reutiliza, de modo que un usuario nunca nace incompleto.</p>
 */
@Entity
@Table(name = "usuarios")
public class Usuario extends EntidadBase {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    private Email email;

    @Column(nullable = false)
    private boolean activo = true;

    /** Constructor para JPA. */
    protected Usuario() {
    }

    /**
     * @param username     nombre de usuario único
     * @param passwordHash contraseña ya codificada con BCrypt
     * @param rol          rol del usuario
     */
    public Usuario(String username, String passwordHash, Rol rol) {
        setUsername(username);
        setPasswordHash(passwordHash);
        setRol(rol);
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        requireNonNull(username, "nombre de usuario");
        if (username.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        this.username = username.trim();
    }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passwordHash) {
        requireNonNull(passwordHash, "contraseña");
        if (passwordHash.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        this.passwordHash = passwordHash;
    }

    public Rol getRol() { return rol; }

    public void setRol(Rol rol) { this.rol = requireNonNull(rol, "rol"); }

    public Email getEmail() { return email; }

    public void setEmail(Email email) { this.email = email; }

    public boolean isActivo() { return activo; }

    /** Desactiva la cuenta: no podrá iniciar sesión. */
    public void desactivar() { this.activo = false; }

    /** Reactiva la cuenta. */
    public void activar() { this.activo = true; }
}
