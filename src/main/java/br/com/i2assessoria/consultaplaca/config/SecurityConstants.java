package br.com.i2assessoria.consultaplaca.config;

public class SecurityConstants {
    static final String SECRET = "nenem";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SING_UP_URL = "/users/sing-up";
    static final long EXPIRATION_TIME = 86400000L;
}
