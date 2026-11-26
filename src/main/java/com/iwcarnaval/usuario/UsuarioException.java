package com.iwcarnaval.usuario;

import com.iwcarnaval.common.BusinessException;

/** Error de negocio relacionado con las cuentas de usuario. */
public class UsuarioException extends BusinessException {

    public UsuarioException(String message) {
        super(message);
    }

    /** Ya existe una cuenta con ese nombre de usuario. */
    public static UsuarioException yaExiste(String username) {
        return new UsuarioException("Ya existe un usuario con el nombre «" + username + "»");
    }

    /** La contraseña no cumple la longitud mínima. */
    public static UsuarioException contrasenaCorta(int minimo) {
        return new UsuarioException("La contraseña debe tener al menos " + minimo + " caracteres");
    }

    /** Un administrador intenta desactivar su propia cuenta. */
    public static UsuarioException desactivacionPropia() {
        return new UsuarioException("No puedes desactivar tu propia cuenta");
    }

    /** Se intenta desactivar la cuenta de un administrador. */
    public static UsuarioException desactivacionDeAdministrador(String username) {
        return new UsuarioException("La cuenta de administrador «" + username + "» no puede desactivarse");
    }
}
