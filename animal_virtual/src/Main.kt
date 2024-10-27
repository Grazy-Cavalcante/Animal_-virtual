fun main() {
    println("Bem-vindo ao Simulador de Animal de Estimação Virtual!")
    println("Digite o nome do seu animal de estimação:")

    // Captura o nome do pet
    val nomePet = readLine() ?: "Eleonoro"
    val pet = VirtualPet(nomePet)

    while (true) {
        println("\nEscolha uma ação:")
        println("1. Alimentar ${pet.nome}")
        println("2. Brincar com ${pet.nome}")
        println("3. Descansar ${pet.nome}")
        println("4. Verificar o status de ${pet.nome}")
        println("5. Sair")

        // Captura a escolha do usuário
        val escolha = readLine()?.toIntOrNull() ?: continue

        when (escolha) {
            1 -> pet.alimentar()
            2 -> pet.brincar()
            3 -> {
                println("Por quantas horas ${pet.nome} deve descansar?")
                val horasDescanso = readLine()?.toIntOrNull() ?: 1
                pet.descansar(horasDescanso)
            }
            4 -> pet.verificarStatus()
            5 -> {
                println("Saindo do Simulador de Animal de Estimação Virtual. Até mais!")
                return
            }
            else -> println("Escolha inválida. Tente novamente.")
        }

        // Passagem do tempo após cada ação
        pet.passarTempo()

        // Verifica as condições de vitória ou derrota
        if (pet.idade >= 50) {
            println("Parabéns! ${pet.nome} atingiu a idade de 50. Você venceu!")
            break
        } else if (pet.nivelDeFome >= 100 || pet.nivelCansaço >= 100 || pet.nivelFelicidade <= 0) {
            println("Infelizmente, ${pet.nome} não resistiu. O jogo terminou.")
            break
        }
    }
}

class VirtualPet(val nome: String) {
    var nivelDeFome = 50
    var nivelFelicidade = 50
    var nivelCansaço = 0
    var idade = 0
    var vontadeBanheiro = 0
    var nivelSujeira = 0

    fun alimentar() {
        nivelDeFome = maxOf(0, nivelDeFome - 10) // Garante que fome não fique negativa
        vontadeBanheiro += 10
        println("$nome foi alimentado. O nível de fome diminuiu, mas a vontade de ir ao banheiro aumentou.")
    }

    fun brincar() {
        nivelFelicidade = minOf(100, nivelFelicidade + 10) // Garante que felicidade não passe de 100
        nivelCansaço = minOf(100, nivelCansaço + 10)
        nivelSujeira += 10
        println("$nome está brincando, ficando mais feliz, mas também mais cansado e sujo.")
    }

    fun descansar(tempo: Int) {
        nivelCansaço = maxOf(0, nivelCansaço - (tempo * 10)) // Reduz o cansaço com base no tempo de descanso
        println("$nome descansou por $tempo horas. O nível de cansaço diminuiu.")
    }

    fun verificarStatus() {
        println("Status atual de $nome:")
        println("Nível de fome: $nivelDeFome")
        println("Nível de felicidade: $nivelFelicidade")
        println("Nível de cansaço: $nivelCansaço")
        println("Idade: $idade")
        println("Vontade de ir ao banheiro: $vontadeBanheiro")
        println("Nível de sujeira: $nivelSujeira")
    }

    fun passarTempo() {
        nivelDeFome = minOf(100, nivelDeFome + 5)
        nivelCansaço = minOf(100, nivelCansaço + 10)
        nivelFelicidade = maxOf(0, nivelFelicidade - 30)
        idade += 1

        println("$nome está ficando mais faminto, cansado, e menos feliz com o passar do tempo.")
    }
}
