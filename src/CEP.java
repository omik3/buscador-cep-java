public class CEP {
    protected String endereco;
    protected String cep;
    protected String bairro;

    public CEP(CEPVIACEP cep) {
        this.endereco = cep.getLogradouro();
        this.cep = cep.getCep();
        this.bairro = cep.getBairro();
    }

    @Override
    public String toString() {
        return "Endereço: " + endereco + "\nCEP: " + cep + "\nBairro: " + bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }
}
