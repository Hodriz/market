package com.market.borghi.service

import com.market.borghi.classe.Produto
import com.market.borghi.repository.ProdutoRepository
import org.springframework.stereotype.Service

@Service
class ProdutoService (
    private val repository: ProdutoRepository
){

    fun salvar(produto: Produto): Produto {
        return repository.save(produto)
    }

    fun listarTodos(): List<Produto>{
        return repository.findAll()
    }

    fun buscarPorCategoria(categoria: String): List<Produto> {
        return repository.findByCategoria(categoria)
    }

    fun atualizar(
        id: Long,
        nome: String?,
        preco: Double?,
        categoria: String?,
        marca: String?
    ): Produto {
        val produtoExistente = repository.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado com ID: $id") }

        // Aplica apenas as atualizações dos campos não nulos
        nome?.let { produtoExistente.nome = it }
        preco?.let { produtoExistente.preco = it }
        categoria?.let { produtoExistente.categoria = it }
        marca?.let { produtoExistente.marca = it }

        return repository.save(produtoExistente)
    }

    fun deletar(id: Long) {
        if (!repository.existsById(id)) {
            throw RuntimeException("Produto não encontrado com ID: $id")
        }
        repository.deleteById(id)
    }

    fun buscarPorId(id: Long): Produto? {
        return repository.findById(id).orElse(null)
    }
}