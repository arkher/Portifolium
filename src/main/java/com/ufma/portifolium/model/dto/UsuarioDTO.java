package com.ufma.portifolium.model.dto;

import java.time.LocalDate;

import javax.persistence.Convert;

import com.ufma.portifolium.model.entities.TipoUsuario;
import com.ufma.portifolium.model.entities.Usuario;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    TipoUsuario tipoUsuario;
    String codigoAcesso;
    String senha;
    String descricao;
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate criadoEm;
    Long id;
    String nome;

    public UsuarioDTO(Usuario usuario, Long id, String nome){
        this.tipoUsuario = usuario.getTipoUsuario();
        this.codigoAcesso = usuario.getCodigoAcesso();
        this.senha = usuario.getSenha();
        this.descricao = usuario.getDescricao();
        this.criadoEm = usuario.getCriadoEm();
        this.id = id;
        this.nome = nome;
    }
}
