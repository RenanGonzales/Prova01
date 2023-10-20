package TaskMaster.service;

import TaskMaster.entity.Tarefa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TarefaService {
    private List<Tarefa> tarefas;

    public TarefaService() {
        tarefas = new ArrayList<>();
    }

    public Tarefa criarTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
        return tarefa;
    }

    public List<Tarefa> buscarTarefas(){  // Crie um endpoint que permita aos usuários listar todas as tarefas cadastradas
        return tarefas;
    }

    public Tarefa buscarTarefa(Long codigo) throws Exception{ // Crie um endpoint que permita aos usuários consultar os detalhes de uma tarefa específica
        Optional<Tarefa> tarefa = tarefas.stream().filter(t -> t.getCodigo() == codigo).findFirst();
        if (tarefa.isPresent()){
            return tarefa.get();
        }else{
            throw new Exception("Tarefa nao encontrada.");
        }
    }

    public void atualizarStatusTarefa(Long codigo, String novoStatus) throws Exception{ // Crie um endpoint que permita aos usuários atualizar apenas o status de uma tarefa existente.
        Optional<Tarefa> tarefaOptional = tarefas.stream().filter(t -> t.getCodigo() == codigo).findFirst();
        if (tarefaOptional.isPresent()){
            Tarefa tarefa = tarefaOptional.get();
            tarefa.setStatus(novoStatus);
        } else {
            throw new Exception("Tarefa nao encontrada.");
        }
    }

    public void atualizarTarefa(Long codigo, Tarefa novoTarefa) throws Exception{ // Crie um endpoint que permita aos usuários atualizar os detalhes de uma tarefa existente
        Optional<Tarefa> tarefaOptional = tarefas.stream().filter(t -> t.getCodigo() == codigo).findFirst();
        if (tarefaOptional.isPresent()){
            Tarefa tarefaExistente = tarefaOptional.get();
            tarefaExistente.setStatus(novoTarefa.getStatus());
            tarefaExistente.setNome(novoTarefa.getNome());
            tarefaExistente.setDescricao(novoTarefa.getDescricao());
        } else {
            throw new Exception("Tarefa nao encontrada");
        }
    }

    public void removerTarefa(Long codigo) throws Exception{ //Crie um endpoint que permita aos usuários remover uma tarefa de sua lista
        Optional<Tarefa> tarefaOptional = tarefas.stream().filter(t -> t.getCodigo() == codigo).findFirst();
        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaOptional.get();
            tarefas.remove(tarefa);
        } else {
            throw new Exception("Tarefa nao encontrada.");
        }

    }



} //FIM DA CLASSE TarefaService
