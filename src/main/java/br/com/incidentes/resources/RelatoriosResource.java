package br.com.incidentes.resources;

import br.com.incidentes.services.RelatorioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("relatorios")
public class RelatoriosResource {

    private RelatorioService relatorioService;


    @GetMapping("/por-datas")
    public void obterRelatorio(
            @RequestParam("dataInicio")
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataInicio,
            @RequestParam("dataFim")
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataFim) {
        relatorioService.obterRelatorioPorDatas(dataInicio, dataFim);
    }


}
