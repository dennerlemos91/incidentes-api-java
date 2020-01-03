package br.com.incidentes.resources;

import br.com.incidentes.services.ZabbixService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("upload")
public class UploadResource {

    private ZabbixService zabbixService;

    @PostMapping("/otrs")
    public void uploadOtrs(@RequestParam MultipartFile arquivo) {
        //TODO: Processesar arquivo otrs
    }

    @PostMapping("/zabbix")
    public void uploadZabbix(@RequestParam MultipartFile arquivo) throws IOException {
        zabbixService.importar(arquivo);
    }


}
