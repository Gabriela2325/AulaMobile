// Importa classes necessárias para os testes instrumentados
import androidx.test.platform.app.InstrumentationRegistry // Usado para obter informações sobre o contexto da aplicação em testes
import androidx.test.ext.junit.runners.AndroidJUnit4 // Fornece suporte ao uso do JUnit4 com testes Android

import org.junit.Test // Anotação que marca um método como um teste
import org.junit.runner.RunWith // Permite definir qual "runner" (executador) o JUnit deve usar

import org.junit.Assert.* // Importa funções de asserção como assertEquals, para verificar se resultados são os esperados

/**
 * Teste instrumentado, que será executado em um dispositivo Android.
 *
 * Veja a documentação de testes em [testing documentation](http://d.android.com/tools/testing).
 */
// A anotação @RunWith define que esse teste será executado usando o runner AndroidJUnit4,
// que é específico para testes Android.
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    // A anotação @Test indica que o método a seguir é um teste de unidade.
    @Test
    fun useAppContext() {
        // Obtém o contexto da aplicação que está sendo testada.
        // O InstrumentationRegistry fornece acesso ao "instrumento" de teste,
        // e targetContext obtém o contexto do alvo (aplicativo sendo testado).
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Verifica se o nome do pacote da aplicação é "com.up_des_mobile.cep".
        // O assertEquals compara o valor esperado com o valor real.
        assertEquals("com.up_des_mobile.cep", appContext.packageName)
    }
}
