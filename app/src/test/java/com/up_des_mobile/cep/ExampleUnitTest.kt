// Importa a anotação @Test para marcar métodos de teste
import org.junit.Test

// Importa as funções de asserção, como assertEquals
import org.junit.Assert.*

/**
 * Exemplo de teste unitário local, que será executado na máquina de desenvolvimento (host).
 *
 * Veja a documentação de testes em [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    // Anota o método como um teste de unidade usando a anotação @Test.
    @Test
    fun addition_isCorrect() {
        // Usa a função assertEquals para verificar se o resultado da adição 2 + 2 é igual a 4.
        // O primeiro parâmetro é o valor esperado (4), e o segundo é o valor obtido (2 + 2).
        assertEquals(4, 2 + 2)
    }
}
