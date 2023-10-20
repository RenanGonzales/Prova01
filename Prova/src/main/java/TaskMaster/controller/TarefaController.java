package TaskMaster.controller;

import TaskMaster.entity.Tarefa;
import TaskMaster.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    TarefaService tarefaService;
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping("/add") // Crie um endpoint que permita aos usuários adicionar uma nova tarefa à sua lista.
    public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa){
       try {
           tarefa = tarefaService.criarTarefa(tarefa);
           return new ResponseEntity<>("Tarefa criada com sucesso!", HttpStatus.CREATED);
       } catch (Exception ex){
           return new ResponseEntity<>("Erro na criacao da tarefa.", HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("todos") // Crie um endpoint que permita aos usuários listar todas as tarefas cadastradas.
    public ResponseEntity<List<Tarefa>> buscarTarefas(){
        try {
            List<Tarefa> lista = tarefaService.buscarTarefas();
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity("Erro na requisicao", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}") // Crie um endpoint que permita aos usuários consultar os detalhes de uma tarefa específica
    public ResponseEntity<?> buscarTarefa(@PathVariable("codigo") Long codigo){
        try {
            Tarefa tarefa = tarefaService.buscarTarefa(codigo);
            return new ResponseEntity(tarefa, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PatchMapping("{codigo}") // Crie um endpoint que permita aos usuários atualizar apenas o status de uma tarefa existente.
    public ResponseEntity<?> atualizarStatusTarefa(@PathVariable("codigo") Long codigo, @RequestBody Map<String, String> statusAtualizado) {
        try {
            String novoStatus = statusAtualizado.get("status");
            tarefaService.atualizarStatusTarefa(codigo, novoStatus);
            return new ResponseEntity<>("Status da tarefa atualizado!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{codigo}") // Crie um endpoint que permita aos usuários atualizar os detalhes de uma tarefa existente
    public ResponseEntity<?> atualizarTarefa(@PathVariable("codigo") Long codigo, @RequestBody Tarefa novaTarefa) {
        try {
            tarefaService.atualizarTarefa(codigo, novaTarefa);
            return new ResponseEntity<>("Tarefa atualizada com sucesso!", HttpStatus.OK);
        } catch ( Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{codigo}") // Remover Tarefa: Crie um endpoint que permita aos usuários remover uma tarefa de sua lista.
    public ResponseEntity removerTarefa(@PathVariable("codigo") Long codigo) {
        try {
            tarefaService.removerTarefa(codigo);
            return new ResponseEntity("Tarefa removida com sucesso!", HttpStatus.OK);
        } catch ( Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

} // FIM DA CLASSE TarefaController
