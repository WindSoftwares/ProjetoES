package com.windsoft.se.project;

import com.windsoft.se.project.usuario.Usuario;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by sixbd on 14/02/2018.
 */

public class UsuarioUnitTest {

    private Usuario userTest;

    @Before
    public void initiate() {
        userTest = new Usuario("Default");
    }

    @Test
    public void testUser() {
        assertEquals("Default", userTest.getNome());

        userTest.setNome("NewName");
        assertEquals("NewName", userTest.getNome());
    }
}
