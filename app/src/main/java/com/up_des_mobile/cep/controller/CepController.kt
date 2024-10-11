package com.up_des_mobile.cep.controller

import com.up_des_mobile.cep.model.Endereco
import com.up_des_mobile.cep.model.ViaCepService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepController(private val service: ViaCepService) {

    fun buscarEndereco(cep: String, onSuccess: (Endereco) -> Unit, onError: () -> Unit) {
        service.buscarEndereco(cep).enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                response.body()?.let {
                    if (it.erro == true) {
                        onError()
                    } else {
                        onSuccess(it)
                    }
                } ?: onError()
            }

            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                onError()
            }
        })
    }
}
