// Imports necessários para o uso de Jetpack Compose, Retrofit, e componentes do Android
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.* // Importações para layouts
import androidx.compose.material.* // Importações para Material Design
import androidx.compose.runtime.* // Importações para estado e composição
import androidx.compose.ui.Alignment // Importações para alinhamento
import androidx.compose.ui.unit.dp // Importações para espaçamento em dp
import com.up_des_mobile.cep.controller.CepController // Controller para buscar CEP
import com.up_des_mobile.cep.model.* // Modelo de dados de Endereço
import com.up_des_mobile.cep.model.* // Serviço ViaCEP para chamadas à API
import retrofit2.Retrofit // Biblioteca Retrofit para requisições HTTP
import retrofit2.converter.gson.GsonConverterFactory // Conversor Gson para JSON

// MainActivity: Atividade principal do app
class MainActivity : ComponentActivity() {
    // Propriedade do controller para busca de CEP
    private lateinit var cepController: CepController

    // Método onCreate é chamado quando a atividade é iniciada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o Retrofit, configurando o URL base da API ViaCEP
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/") // URL base da API
            .addConverterFactory(GsonConverterFactory.create()) // Converte JSON em objetos Kotlin usando Gson
            .build()

        // Cria o serviço ViaCepService para realizar as requisições de CEP
        val service = retrofit.create(ViaCepService::class.java)

        // Inicializa o CepController passando o serviço ViaCEP para ele
        cepController = CepController(service)

        // Define o conteúdo da interface com Jetpack Compose
        setContent {
            CepApp(cepController) // Chama a função composable CepApp para desenhar a UI
        }
    }
}

@Composable
fun CepApp(cepController: CepController) {
    // Estado para o valor do CEP que será digitado pelo usuário
    var cep by remember { mutableStateOf("") }

    // Estado para armazenar o resultado da busca de endereço (se o CEP for válido)
    var endereco by remember { mutableStateOf<Endereco?>(null) }

    // Estado para armazenar mensagens de erro (por exemplo, CEP inválido)
    var errorMessage by remember { mutableStateOf("") }

    // Define um layout de coluna (os elementos são organizados verticalmente)
    Column(
        modifier = Modifier
            .fillMaxSize() // Preenche a tela toda
            .padding(16.dp), // Adiciona padding de 16dp
        horizontalAlignment = Alignment.CenterHorizontally // Alinha os itens ao centro horizontalmente
    ) {
        // Campo de texto onde o usuário pode digitar o CEP
        TextField(
            value = cep, // Valor atual do campo de texto
            onValueChange = { cep = it }, // Atualiza o estado 'cep' conforme o usuário digita
            label = { Text("Digite o CEP") } // Rótulo que aparece no campo de texto
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espaçamento entre o campo de texto e o botão

        // Botão que dispara a busca do endereço quando clicado
        Button(onClick = {
            if (cep.isNotBlank()) { // Verifica se o campo não está vazio
                // Chama o método buscarEndereco no controller
                cepController.buscarEndereco(cep,
                    { endereco = it; errorMessage = "" }, // Se sucesso, atualiza 'endereco'
                    { errorMessage = "CEP não localizado ou inválido" } // Se erro, exibe mensagem de erro
                )
            }
        }) {
            Text("Buscar") // Texto no botão
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaçamento antes de exibir o resultado

        // Se o endereço foi encontrado, exibe as informações
        endereco?.let {
            Text("CEP: ${it.cep}") // Exibe o CEP
            Text("Logradouro: ${it.logradouro}") // Exibe o logradouro
            Text("Bairro: ${it.bairro}") // Exibe o bairro
            Text("Cidade: ${it.localidade} - ${it.uf}") // Exibe a cidade e estado (UF)
        }

        // Se houver uma mensagem de erro, exibe o texto de erro em vermelho
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colors.error) // Exibe a mensagem de erro
        }
    }
}
