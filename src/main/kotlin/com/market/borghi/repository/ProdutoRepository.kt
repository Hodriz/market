package com.market.borghi.repository

import com.market.borghi.classe.Produto
import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoRepository : JpaRepository<Produto,Long> {

    fun findByCategoria(categoria:String) : List<Produto>
}