package com.projeto.trocaoleo.entity.usuario;

import com.projeto.trocaoleo.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING) // instrui o jpa a salvar a string no banco de dados
    @Column(nullable = false)
    private Perfil perfil;

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.perfil == Perfil.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MECANICO")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_MECANICO"));
    }

    @Override
    public String getPassword(){
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return this.ativo;
    }


}
