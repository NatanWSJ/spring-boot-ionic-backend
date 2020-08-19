package com.natanjesus.cursomc.repository;

import com.natanjesus.cursomc.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.natanjesus.cursomc.domain.Produto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {

    //Equivalente a query abaixo;
    //Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
    @Transactional(readOnly=true)
    @Query(" SELECT DISTINCT produto FROM Produto produto" +
            "    INNER JOIN produto.categorias categoria" +
            " WHERE produto.nome LIKE %:nome% " +
            "    AND categoria IN :categorias")
    Page<Produto> search(
            @Param("nome") String nome,
            @Param("categorias")List<Categoria> categorias,
            Pageable pageRequest
    );

}
