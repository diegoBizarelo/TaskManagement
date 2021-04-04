package com.diego.bizarelo.taskmanagement.database

import com.diego.bizarelo.taskmanagement.model.Task

object Repository {
    private val tasks = mutableListOf(
            Task("Ver o Clima", "1619781813868",  true),
            Task("Olhar o Céu", "1612781813868",  false),
            Task("Olhar o Mar", "1615581853868",  true),
            Task("Contar Carneirinhos", "1611781813868",  false),
            Task("Secar Gelo", "1619971813868",  false),
            Task("Limpar Carvão", "1649781813868",  false),
            Task("Catar Coquinho", "1612881813868",  false),
            Task("Contar Areia da Praia", "1669712213868",  false),
            Task("Ler nas Entrelinhas", "1659781812868",  false),
            Task("Chutar um Paralelepípedo", "1649781813868",  false),
            Task("Fazer Pontilhismo", "1639181813868",  false),
            Task("Conta milho de uma espiga", "1621781013868",  false),
    )

    fun getAll() = tasks

    fun add(task: Task) {
        tasks.add(task)
    }

    fun get(taskPosition: Int) : Task {
        return tasks[taskPosition]
    }

    fun update (position: Int, taskEdit: Task) {
        val task = get(position)
        task.title = taskEdit.title
        task.time = taskEdit.time
        task.done = taskEdit.done
    }

    fun delete(position: Int) {
        tasks.removeAt(position)
    }
}