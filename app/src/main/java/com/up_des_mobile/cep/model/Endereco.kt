// Define uma data class chamada Endereco
data class Endereco(
    // Propriedades que serão preenchidas com os dados retornados pela API

    val cep: String,            // CEP do endereço consultado
    val logradouro: String,     // Nome da rua/avenida (logradouro)
    val complemento: String?,   // Complemento (opcional, pode ser null)
    val bairro: String,         // Bairro do endereço
    val localidade: String,     // Cidade do endereço
    val uf: String,             // Sigla do estado (Unidade Federativa)
    val estado: String,         // Nome completo do estado
    val erro: Boolean? = null   // Campo opcional para indicar erro na consulta (null por padrão)
)
