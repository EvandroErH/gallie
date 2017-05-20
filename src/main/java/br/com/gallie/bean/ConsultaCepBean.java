/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.bean;

import br.com.gallie.classes.ConsultaCep;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

/**
 *
 * @author operador
 */
@Stateless
public class ConsultaCepBean {

    private static final String URL_CONSULTA_CEP = "http://cep.republicavirtual.com.br/web_cep.php?cep=%s&formato=xml";

    public ConsultaCep consultaCep(final String cep) {
        final ConsultaCep retorno = new ConsultaCep();
        retorno.setCep(cep);
        try {
            final String link = String.format(URL_CONSULTA_CEP, cep);

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setReadTimeout(1000 * 5);

            //parametros para o POST
            connection.setUseCaches(true);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(connection.getInputStream());

            JAXBContext context = JAXBContext.newInstance(ConsultaCep.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            final ConsultaCep consulta = (ConsultaCep) unmarshaller.unmarshal(new ToLowerCaseNamesStreamReaderDelegate(xsr));
            if (consulta != null) {
                retorno.setLogradouro(consulta.getTipoLogradouro() + " " + consulta.getLogradouro());
                retorno.setBairro(consulta.getBairro());
                retorno.setCidade(consulta.getCidade());
                retorno.setUf(consulta.getUf());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public static class ToLowerCaseNamesStreamReaderDelegate extends StreamReaderDelegate {

        public ToLowerCaseNamesStreamReaderDelegate(XMLStreamReader xsr) {
            super(xsr);
        }

        @Override
        public String getAttributeLocalName(int index) {
            return super.getAttributeLocalName(index).toLowerCase().intern();
        }

        @Override
        public String getLocalName() {
            try {
                return super.getLocalName().toLowerCase().intern();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
