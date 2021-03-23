package com.example.listadedesejos.models

import java.io.Serializable

var serial = 1
class Desejo : Serializable {
  var id: Int
  var descricao: String
  var valor: Float

  constructor(descricao: String, valor: Float) {
    this.id = serial++
    this.descricao = descricao
    this.valor = valor
  }

  override fun toString(): String {
    return "${this.descricao} - R$ ${this.valor}"
  }
}