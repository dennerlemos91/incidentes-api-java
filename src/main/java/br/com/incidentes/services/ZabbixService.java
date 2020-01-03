package br.com.incidentes.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ZabbixService {

    public void importar(List<MultipartFile> file) {
        String arquivo = "C:\\Users\\Raphael\\Desktop\\arquivo.csv";
        String linha = "";
        String separador = ";";
        BufferedReader conteudoArquivo = null;

        try {
            conteudoArquivo = new BufferedReader(new FileReader(arquivo));
            while ((linha = conteudoArquivo.readLine())!=null){
                String[] dados = linha.split(arquivo);

                //mostrar o conteúdo do array
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
