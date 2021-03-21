package com.diego.bizarelo.taskmanagement.database

import com.diego.bizarelo.taskmanagement.model.Task

object Repository {
    private val tasks = mutableListOf(
            Task("Lavar louça", "Uma pilha de louça enorme", "em casa", false),
            Task("Tirar Lixo", "Uma pilha de louça enorme", "em casa", false),
            Task("Limpar o chão", "Uma pilha de louça enorme", "em casa", false),
            Task("Fazer AT Fundamentos", "Uma pilha de louça enorme", "em casa", false),
            Task("Fazer AT D. Interfaces", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
            Task("Fazer Inscrição processos", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
            Task("Responder Email Infnet", "Uma pilha de louça enorme", "em casa", false),
    )

    fun getAll() = tasks
}