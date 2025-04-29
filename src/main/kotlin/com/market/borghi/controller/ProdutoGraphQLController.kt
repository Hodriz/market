package com.market.borghi.controller

import com.market.borghi.classe.Produto
import com.market.borghi.repository.ProdutoRepository
import com.market.borghi.service.ProdutoService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller


@Controller
class ProdutoGraphQLController (private val service: ProdutoService) {

    @QueryMapping
    fun listarProdutos(): List<Produto> {
        return service.listarTodos()
    }

    @QueryMapping
    fun buscarProdutoPorCategoria(@Argument categoria: String):List<Produto>{
        return service.buscarPorCategoria(categoria)
    }

    @MutationMapping
    fun criarProduto(
        @Argument nome: String,
        @Argument preco: Double,
        @Argument categoria: String,
        @Argument marca: String
    ): Produto{
       val produto= Produto(
        nome= nome,
        preco = preco,
        categoria = categoria,
        marca = marca
    )
    return service.salvar(produto)
    }

    // NOVA Mutation: Atualizar Produto
    @MutationMapping
    fun atualizarProduto(
        @Argument id: Long,
        @Argument nome: String?,
        @Argument preco: Double?,
        @Argument categoria: String?,
        @Argument marca: String?
    ): Produto {
        return service.atualizar(
            id = id,
            nome = nome,
            preco = preco,
            categoria = categoria,
            marca = marca
        )
    }

    // NOVA Mutation: Deletar Produto
    @MutationMapping
    fun deletarProduto(@Argument id: Long): Boolean {
        service.deletar(id)
        return true
    }

    // Nova Query: Buscar por ID (útil para testes)
    @QueryMapping
    fun buscarProdutoPorId(@Argument id: Long): Produto? {
        return service.buscarPorId(id)
    }
}