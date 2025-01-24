package com.example.EnglishSkillTrackerCRUD.config;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
@Component
public class JwtEncoder {
    private static final String SECRET_KEY="my-secret-key-es-loSuFICIENTEMENTE-LARGA";//clave secreta para encriptar el token fimra digital las peticiones
    public String generateToken(Map<String,Object> claims){// metodo para generar el token que retorna un string que es el token
        return Jwts.builder()//crea la instancia de jwt
               .claims(claims)//agrega los claims que son los datos que se quieren enviar en el token
               .issuedAt(new Date(System.currentTimeMillis()))//fecha de creacion del token que es la fecha actual
               .expiration(new Date(System.currentTimeMillis()+1000 *60 *60 *10))//fecha de expiracion del token  que es la fecha actual mas 10 horas 
               .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))//firma digital del token con metodo de encriptacion hmacshaKeyFor y la clave secreta en bytes
               .compact();//compacta el token y lo retorna
    }

    public boolean validateToken(String token){//metodo para validar el token que retorna un booleano
        try {
            Jwts.parser()//crea la instancia de jwt
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))//verifica la firma digital del token con el metodo de encriptacion hmacShaKeyFor y la clave secreta en bytes
                .build().//construye el token
                parseSignedClaims(token);//parsea el token
            return true; 
        } catch (Exception e) {
            return false;
            
        }
    }

    public String extractEmail(String token){//metodo para extraer el email del token que retorna un string
        return extractClaim(token, Claims::getSubject);//extrae el claim del token con el metodo getSubject
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {//metodo generico para extraer el claim del token que retorna un tipo generico
        final Claims claims = extractAllClaims(token);//extrae todos los claims del token
        return claimsResolver.apply(claims);//aplica el claim al resolver de claims, el resolver hace la funcion de obtener el claim
    }
    private Claims extractAllClaims(String token) {//metodo para extraer todos los claims del token que retorna un objeto de tipo Claims
        return Jwts.parser()//crea la instancia de jwt
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))//verifica la firma digital del token con el metodo de encriptacion hmacShaKeyFor y la clave secreta en bytes
                .build()//construye el token
                .parseSignedClaims(token)//parsea el token
                .getPayload();//obtiene el payload del token
    }
}
