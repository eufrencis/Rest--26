package com.projeto.trocaoleo.service;

import com.projeto.trocaoleo.entity.usuario.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // Lê os valores definidos no application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration}")
    private Long expiration;

    // Converte a chave String do properties em uma chave criptográfica segura para o algoritmo HMAC-SHA
    private Key getSigninKey (){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Gera o token JWT para o usuário autenticado
    public String gerarToken(UsuarioEntity usuario) {
        Date dataAtual = new Date();
        Date dataExpiracao = new Date(dataAtual.getTime() + expiration);

        return Jwts.builder()
                .setIssuer("API Troca de Oleo")             // Nome da aplicação emissora
                .setSubject(usuario.getLogin())            // Identificador único (login)
                .setIssuedAt(dataAtual)                    // Data de criação
                .setExpiration(dataExpiracao)              // Data limite de validade
                .signWith(getSigninKey(), SignatureAlgorithm.HS256) // Assinatura com a chave secreta
                .compact();
    }

    // Valida o token recebido no cabeçalho Authorization
    public String validarToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); // Retorna o login do usuário se o token for válido
        } catch (Exception e) {
            return ""; // Retorna vazio se o token for inválido, alterado ou expirado
        }
    }

}
