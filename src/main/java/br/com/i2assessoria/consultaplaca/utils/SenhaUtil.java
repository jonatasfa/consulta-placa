package br.com.i2assessoria.consultaplaca.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class SenhaUtil {

    public static String gerarSenha(){
        Random random = new Random();
        return String.format ("%04d", random.nextInt(9999));
    }

    public static String criptografar(String senha){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }
}
