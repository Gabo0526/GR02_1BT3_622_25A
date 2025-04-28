package com.ma.gr02_1bt3_622_25a;

import com.ma.gr02_1bt3_622_25a.model.dao.BloqueCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.CasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.UsuarioDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.autenticar("jairo.quispe@epn.edu.ec", "12345david", "Estudiante");

        System.out.println(usuario);

        CasilleroDAO casilleroDAO = new CasilleroDAO();

        List<Casillero> casilleros = casilleroDAO.findAll();

        for (Casillero casillero : casilleros) {
            System.out.println(casillero);
        }

        // Obtener casilleros por bloque:
        BloqueCasilleroDAO bloqueCasilleroDAO = new BloqueCasilleroDAO();
        BloqueCasillero bloqueCasillero = bloqueCasilleroDAO.find(1);
        System.out.println(bloqueCasillero);

        List<Casillero> casillerosBloque1 = casilleroDAO.obtenerPorBloque(bloqueCasillero);

        for (Casillero casillero : casillerosBloque1) {
            System.out.println(casillero);
        }
    }
}
