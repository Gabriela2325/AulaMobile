// Importa as classes necessárias da biblioteca Retrofit
import com.up_des_mobile.cep.model.Endereco
import retrofit2.Call // Representa uma chamada HTTP que pode ser executada
import retrofit2.http.GET // Anotação usada para definir o tipo de requisição HTTP (GET)
import retrofit2.http.Path // Anotação para definir um parâmetro de caminho na URL

// Define a interface ViaCepService, que será usada para realizar chamadas à API ViaCEP

interface ViaCepService {

    // A anotação @GET define que este método fará uma requisição GET para o endpoint "ws/{cep}/json/"
    // {cep} é um placeholder, que será substituído pelo valor real passado como argumento.
    // O método retorna um objeto Call<Endereco>, que será preenchido com a resposta da API.

    @GET("ws/{cep}/json/")
    fun buscarEndereco(@Path("cep") cep: String): Call<Endereco>

    // O parâmetro @Path("cep") substitui o placeholder {cep} na URL com o valor do CEP fornecido.
    // Exemplo: Se cep = "01001000", a URL será "ws/01001000/json/"
    // O método retorna um Call<Endereco>, que é uma chamada assíncrona e pode ser executada
    // para obter os dados do endereço.
}
