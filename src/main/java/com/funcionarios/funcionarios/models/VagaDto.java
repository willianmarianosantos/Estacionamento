package com.funcionarios.funcionarios.models;

public class VagaDto {
    private Integer numeroVaga;
    private String marcaCarro;
    private String modeloCarro;
    private String placaCarro;
    private String nomeFuncionario;
    private String cpfFuncionario;


    public VagaDto(Integer numeroVaga, String marcaCarro, String modeloCarro, String placaCarro,
                   String nomeFuncionario, String cpfFuncionario) {
        this.numeroVaga = numeroVaga;
        this.marcaCarro = marcaCarro;
        this.modeloCarro = modeloCarro;
        this.placaCarro = placaCarro;
        this.nomeFuncionario = nomeFuncionario;
        this.cpfFuncionario = cpfFuncionario;
    }


    public Integer getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(Integer numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }
}
