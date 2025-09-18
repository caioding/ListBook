package com.example.listbook.mycontacts

// Estrutura de dados para representar um contato
data class Contact(
    val name: String,
    val isFavorite: Boolean,
    val phone: String = "Sem número"
)

// Lista mutável para armazenar os contatos
val contactList = mutableListOf<Contact>()

/**
 * 1. Adiciona um novo contato à lista.
 * @param name O nome do contato (obrigatório).
 * @param isFavorite Se o contato é favorito (obrigatório).
 * @param phone O telefone do contato (opcional, padrão = "Sem número").
 */
fun addContact(name: String, isFavorite: Boolean, phone: String = "Sem número") {
    contactList.add(Contact(name, isFavorite, phone))
    println("Contato '$name' adicionado.")
}

/**
 * 2. Retorna uma lista com os nomes dos contatos favoritos em ordem alfabética.
 * Função de expressão única.
 */
fun listFavorites(): List<String> = contactList
    .filter { it.isFavorite }
    .map { it.name }
    .sorted()

/**
 * 3. Busca contatos na lista pelo nome.
 * @param name O termo a ser buscado no nome dos contatos.
 * @return Uma lista de contatos que correspondem ao critério de busca.
 */
fun findContactByName(name: String): List<Contact> {
    return contactList.filter { it.name.contains(name, ignoreCase = true) }
}

/**
 * 4. Envia uma mensagem.
 * @param message A mensagem a ser enviada (obrigatório).
 * @param sender O remetente da mensagem (opcional, padrão = "Sistema").
 */
fun sendMessage(message: String, sender: String = "Sistema") {
    println("De: $sender")
    println("Mensagem: \"$message\"")
}

/**
 * Função auxiliar para exibir uma lista de contatos de forma organizada.
 */
fun showContacts(contacts: List<Contact>, title: String) {
    println("--- $title ---")
    if (contacts.isEmpty()) {
        println("Nenhum contato para exibir.")
    } else {
        contacts.forEach {
            println("Nome: ${it.name} | Favorito: ${if (it.isFavorite) "Sim" else "Não"} | Telefone: ${it.phone}")
        }
    }
    println() // Adiciona uma linha em branco para melhor separação
}

fun main() {
    println("--- Agenda Inteligente Kotlin ---\n")

    // Adicionando contatos
    addContact(name = "Maria", isFavorite = true, phone = "1111-1111")
    addContact(name = "João", isFavorite = false)
    addContact(name = "Ana", isFavorite = true, phone = "3333-3333")
    addContact(name = "Carlos", isFavorite = false, phone = "4444-4444")
    addContact(name = "Amanda", isFavorite = true)
    println()

    // Mostrando todos os contatos
    showContacts(contactList, "Todos os Contatos")

    // Listando favoritos
    val favorites = listFavorites()
    println("--- Contatos Favoritos (Ordem Alfabética) ---")
    favorites.forEach { println(it) }
    println()

    // Buscando contato por nome
    val searchResult = findContactByName("an")
    showContacts(searchResult, "Resultado da Busca por 'an'")

    // Enviando uma mensagem
    println("--- Envio de Mensagem ---")
    sendMessage("Olá, equipe! Reunião amanhã às 10h.")
    sendMessage("Lembrete: comprar pão.", sender = "Ana")
}
