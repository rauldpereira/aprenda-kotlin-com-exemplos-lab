enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class User( val nome: String, var email: String, var nivel: Nivel)

data class ConteudoEducacional(val nome: String, val nivelNecessario: Nivel, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<User>()
    
    fun matricular(vararg users: User) {
        for (user in users){
            if(!inscritos.contains(user)){
                if (conteudos.all { it.nivelNecessario <= user.nivel}){
                	inscritos.add(user)
                	println("A matricula de ${user.nome} na formação ${nome} foi concluida com sucesso!")
            	} else {
                   println("Infelizmente ${user.nome} não atende aos Requisitos Necessários para se matricular na formação: ${nome}.")
                } 
            } else {
                println("A matricula de ${user.nome} na formação ${nome} já existe!")
            }
        }
    }
}

fun main() {
    
    val user1 = User("Raul", "rauldiaspereira2@gmail.com", Nivel.AVANCADO)
    val user2 = User("Leandro", "leandro@gmail.com", Nivel.BASICO)
    
    val conteudoEducacional1 = ConteudoEducacional("Introdução ao Kotlin", Nivel.BASICO)
    val conteudoEducacional2 = ConteudoEducacional("Introdução a banco de dados", Nivel.AVANCADO, 100)
    
    val formacao = Formacao("Análise e Desenvolvimento de Sistemas", listOf(conteudoEducacional1, conteudoEducacional2))
    
    formacao.matricular(user1, user2)
    
    println("")
    
    println("Inscritos na formação ${formacao.nome}: ")
    formacao.inscritos.forEach { user ->
        println("	${user.nome} - ${user.email} - ${user.nivel}")
    }
    
    println("")
    
    formacao.matricular(user1)
}
