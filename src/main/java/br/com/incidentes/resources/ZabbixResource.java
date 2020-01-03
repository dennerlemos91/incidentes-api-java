package br.com.incidentes.resources;

import br.com.incidentes.services.ZabbixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/upload")
public class ZabbixResource {

    @Autowired
    private ZabbixService zabbixService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void uploadArquivo(@RequestParam List<MultipartFile> file){
        zabbixService.importar(file);
    }

}
